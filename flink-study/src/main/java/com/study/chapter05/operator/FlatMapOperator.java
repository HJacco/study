package com.study.chapter05.operator;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * flatMap操作又称为扁平映射，主要是将数据流中的整体（一般是集合类型）拆分成一个一个的个体使用。
 * 消费一个元素，可以产生0到多个元素。
 * flatMap可以认为是“扁平化”（flatten）和“映射”（map）两步操作的结合，
 * 也就是先按照某种规则对数据进行打散拆分，再对拆分后的元素做转换处理
 */
public class FlatMapOperator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> datasource = env.fromCollection(Arrays.asList(1, 2, 3));
        SingleOutputStreamOperator<String> flatMapOperator = datasource.flatMap(new FlatMapFunction<Integer, String>() {
            @Override
            public void flatMap(Integer value, Collector<String> out) throws Exception {
                out.collect("number:" + value);
            }
        });
        flatMapOperator.print();

        env.execute();
    }
}
