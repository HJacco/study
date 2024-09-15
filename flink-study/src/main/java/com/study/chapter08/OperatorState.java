package com.study.chapter08;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Iterator;

public class OperatorState {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.socketTextStream("localhost", 7777)
                        .map(new CustomMapFunctionWithState())
                .print();

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
