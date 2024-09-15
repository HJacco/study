package com.study.chapter05.operator;

import com.study.common.entity.WaterSensor;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class AggregationKeyedByOperator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("1", 2L, 9),
                new WaterSensor("3", 4L, 8),
                new WaterSensor("5", 5L, 10),
                new WaterSensor("6", 6L, 12)
                );
        // keyedBy算子是对数据做分区
        KeyedStream<WaterSensor, String> keyedStream = source.keyBy(new KeySelector<WaterSensor, String>() {
            @Override
            public String getKey(WaterSensor value) throws Exception {
                return "key:" + (Integer.parseInt(value.getId()) % 2);
            }
        });
        keyedStream.print();

        // 在vc字段上对分区数据做求和操作
        keyedStream.sum("vc").print();

        // vc字段最小值
        keyedStream.min("vc").print();

        keyedStream.minBy("vc").print();

        keyedStream.reduce((ReduceFunction<WaterSensor>) (value1, value2) -> {
            if (value1 == null) {
                return value2;
            }
            if (value2 == null) {
                return value1;
            }
            if (value1.getVc() > value2.getVc()) {
                return value1;
            }
            return value2;
        }).print();
        env.execute();
    }
}
