package com.study.chapter02;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class WordCountImplementWithStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FileSource<String> source = FileSource.forRecordStreamFormat(
                new TextLineInputFormat(),
                new Path("/Users/huangchongjie/project/baidu/ods/etl/dcec/idm/idm_graph_merge_v29.sh"))
                .build();
        DataStreamSource<String> fileSource = env.fromSource(source, WatermarkStrategy.noWatermarks(), "fileSource");

        SingleOutputStreamOperator<Tuple2<String, Long>> sum = fileSource.flatMap((FlatMapFunction<String, Tuple2<String, Long>>) (value, out) -> {
            String[] words = value.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).keyBy(data -> data.f0).sum(1);
        sum.print();
        env.execute();
    }
}
