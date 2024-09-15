package com.study.chapter08;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.*;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BoardCastState {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        env.setParallelism(1);
        DataStreamSource<String> socketStream = env.socketTextStream("localhost", 7777);

        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>((
                GeneratorFunction<Long, String>) aLong -> "" + aLong, Long.MAX_VALUE, RateLimiterStrategy.perSecond(1D/10), Types.STRING);
        DataStreamSource<String> dataGenerator = env.fromSource(generatorSource, WatermarkStrategy.noWatermarks(), "dataGenerator");
        MapStateDescriptor<String, List<String>> broadcastDesc = new MapStateDescriptor<>("state", Types.STRING, Types.LIST(Types.STRING));

        BroadcastStream<String> broadcastStream = dataGenerator.setParallelism(1).broadcast(broadcastDesc);


        socketStream.connect(broadcastStream)
                        .process(new BroadcastProcessFunction<String, String, String>() {
                            @Override
                            public void processElement(String value, BroadcastProcessFunction<String, String, String>.ReadOnlyContext ctx, Collector<String> out) throws Exception {

                                ReadOnlyBroadcastState<String, List<String>> broadcastState = ctx.getBroadcastState(broadcastDesc);
                                List<String> records = broadcastState.get("1");
                                System.out.println("value:" + value + "; 获取的最新的状态:" + Arrays.toString(records.toArray()));
                            }

                            @Override
                            public void processBroadcastElement(String value, BroadcastProcessFunction<String, String, String>.Context ctx, Collector<String> out) throws Exception {
                                System.out.println((System.currentTimeMillis() / 1000) + " 更新广播数据");
                                BroadcastState<String, List<String>> broadcastState = ctx.getBroadcastState(broadcastDesc);
                                if (null == broadcastState.get("1")) {
                                    List<String> records = new ArrayList<>();
                                    broadcastState.put("1", records);
                                }
                                broadcastState.get("1").add(value);

                            }
                        }).print();

        env.execute();
    }

    static class CustomMapFunctionWithState implements MapFunction<String, String>, CheckpointedFunction {
        private ListState<Long> state;

        @Override
        public String map(String value) throws Exception {
            Iterator<Long> it = state.get().iterator();
            while (it.hasNext()) {
                if (it.next().equals(Long.parseLong(value))) {
                    return "偶数";
                }
            }
            return "奇数";
        }

        /**
         * 本地变量持久化：将本地变量拷贝到算子状态中，开启checkpoint的时候才会调用
         */
        @Override
        public void snapshotState(FunctionSnapshotContext context) throws Exception {
            System.out.println("snapshot...");
            state.clear();
        }

        /**
         * 程序启动和恢复时，从状态中把数据添加到本地变量，每个子任务调用一次
         */
        @Override
        public void initializeState(FunctionInitializationContext context) throws Exception {
            System.out.println("initialize state ...");
            state = context.getOperatorStateStore().getListState(new ListStateDescriptor<Long>("state", Types.LONG));
            for (long i = 1; i <= 10; i ++) {
                if (i % 2 == 0) {
                    state.add(i);
                }
            }
        }
    }
}
