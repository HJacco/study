package com.study.chapter05.partition;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PartitionOperator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromElements(1, 2, 3, 4, 5);

        // 随机分区
        source.shuffle().print();

        // 轮询分区
        source.rebalance().print();

        // 重缩放分区,底层基于轮询
        source.rescale().print();

        // 广播，数据会被发送到所有分区
        source.broadcast().print();

        // 全局分区，会将输入数据全部发送到下游第一个子任务中；相当于并行度变成1
        source.global().print();

        env.execute();
    }
}
