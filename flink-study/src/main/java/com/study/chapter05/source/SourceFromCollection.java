package com.study.chapter05.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

public class SourceFromCollection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        List<Integer> data = Arrays.asList(1, 22, 3);
        DataStreamSource<Integer> source = env.fromCollection(data);
        source.print();
        env.execute();
    }
}
