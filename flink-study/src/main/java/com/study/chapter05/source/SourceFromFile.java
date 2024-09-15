package com.study.chapter05.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SourceFromFile {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 目录可以是hdfs目录
        FileSource<String> fileSource = FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path(""))
                .build();
        DataStreamSource<String> source = env.fromSource(fileSource, WatermarkStrategy.noWatermarks(), "fileSource");
        source.print();
        env.execute();
    }
}
