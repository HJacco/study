package com.study.chapter05.sink;

import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.MemorySize;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;
import java.time.ZoneId;

public class SinkExample {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE);
        env.setParallelism(2);

        DataStreamSource<Integer> source = env.fromElements(1, 2, 23);

        // 输出数据到文件
        FileSink<Integer> fileSink = FileSink
                .<Integer>forRowFormat(
                        new Path("/Users/huangchongjie/Download/flink"),
                        new SimpleStringEncoder<>("UTF-8"))
                .withOutputFileConfig(
                        OutputFileConfig.builder()
                                .withPartPrefix("part")
                                .withPartSuffix(".log")
                                .build()
                )
                // 目录分桶：按照每小时一个目录
                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyyMMddHH", ZoneId.systemDefault()))
                .withRollingPolicy(
                        DefaultRollingPolicy.builder()
                                .withRolloverInterval(Duration.ofMinutes(1))
                                .withMaxPartSize(new MemorySize(1024 * 1024))
                                .build()
                )
                .build();
        // 输出到kafka
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
                .setBootstrapServers("")
                .setRecordSerializer(
                        KafkaRecordSerializationSchema.
                                <String>builder()
                                .setTopic("topic")
                                .setValueSerializationSchema(
                                        new SimpleStringSchema())
                                .build()
                )
                .setDeliveryGuarantee(DeliveryGuarantee.EXACTLY_ONCE)
                // 精准一次投递，需要设置事务id
                .setTransactionalIdPrefix("study-")
                // 精准一次投递，需要设置事务超时时间
                .setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, String.valueOf(10 * 60 * 1000))
                .build();

        source.sinkTo(fileSink);

        // 自定义输出
        source.addSink(new SinkFunction<Integer>() {
            @Override
            public void invoke(Integer value, Context context) throws Exception {
                SinkFunction.super.invoke(value, context);
            }
        });

        env.execute();
    }
}
