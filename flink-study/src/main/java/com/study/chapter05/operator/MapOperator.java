package com.study.chapter05.operator;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class MapOperator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> datasource = env.fromCollection(Arrays.asList(1, 2, 3));
        datasource.filter(i -> i % 2 == 1).map(i -> i * 2).print();

        env.execute();
    }
}
