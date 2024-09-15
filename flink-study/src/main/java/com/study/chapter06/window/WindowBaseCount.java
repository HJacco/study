package com.study.chapter06.window;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;

public class WindowBaseCount {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketStream = env.socketTextStream("localhost", 7777);

        // 滚动计数窗口，窗口内数据达到10就触发计算
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .countWindow(10);

        // 滑动计数窗口，窗口内数据达到10就触发计算， 滑动步长3
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .countWindow(10, 3);
        // 全局窗口，默认不会触发计算
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(GlobalWindows.create());

    }
}
