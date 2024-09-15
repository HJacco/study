package com.study.chapter06.window;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowAggregation {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<String> socketStream = env.socketTextStream("localhost", 7777);

        // 按键开窗，滚动窗口， 基于处理时间, reduce 增量聚合，每次接到数据，会将窗口内的前一次计算结果跟当前数据聚合
//        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
//                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .reduce((ReduceFunction<String>) (value1, value2) -> value1 + ";" + value2).print();

        // 基于AggregationFunction, 更灵活，不限定输入输出以及聚合类型
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .aggregate(new AggregateFunction<String, String, String>() {
                    /**
                     * 为聚合操作创建一个初始状态，每个聚合任务只会调用一次
                     */
                    @Override
                    public String createAccumulator() {
                        return "";
                    }

                    /**
                     * 将输入元素添加到累加器
                     */
                    @Override
                    public String add(String value, String accumulator) {
                        return accumulator + " " + value.split(",")[1];
                    }

                    @Override
                    public String getResult(String accumulator) {
                        return accumulator;
                    }

                    @Override
                    public String merge(String a, String b) {
                        System.out.println("call merge method!");
                        return a + " " + b;
                    }
                }).print();

        env.execute();
    }
}
