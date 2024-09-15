package com.study.chapter05.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SourceFromDataGen {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>((
                GeneratorFunction<Long, String>) aLong -> "Number:" + aLong, Long.MAX_VALUE, RateLimiterStrategy.perSecond(1D / 10), Types.STRING);
        DataStreamSource<String> dataGenerator = env.fromSource(generatorSource, WatermarkStrategy.noWatermarks(), "dataGenerator");
        dataGenerator
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String value) throws Exception {
                        return (System.currentTimeMillis() / 1000) + " 产生数据：" + value;
                    }
                })
                .print();
        env.execute();
    }
}
