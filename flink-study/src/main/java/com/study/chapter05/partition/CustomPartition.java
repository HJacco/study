package com.study.chapter05.partition;

import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CustomPartition {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromElements(1, 2, 3, 4, 5);

        source.partitionCustom(
                new CustomPartitioner(),
                (KeySelector<Integer, Integer>) value -> value)
                .print();

        env.execute();
    }

    static class CustomPartitioner implements Partitioner<Integer> {

        @Override
        public int partition(Integer key, int numPartitions) {
            return key % numPartitions;
        }
    }
}
