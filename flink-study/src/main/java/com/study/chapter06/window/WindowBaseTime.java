package com.study.chapter06.window;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowBaseTime {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketStream = env.socketTextStream("localhost", 7777);

        // 按键开窗，滚动窗口， 基于处理时间
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)));

        // 按键开窗， 滑动窗口 窗口大小是10s， 滑动步长5s
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        // 会话窗口, withGap是会话超时时间， withDynamicGap方法可以动态设置超时时间
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(ProcessingTimeSessionWindows.withGap(Time.seconds(10)));

        // 按键开窗，滚动窗口， 基于事件时间
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(TumblingEventTimeWindows.of(Time.seconds(5)));

        // 按键开窗， 滑动窗口 窗口大小是10s， 滑动步长5s 基于事件时间
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        // 会话窗口, 基于事件时间 withGap是会话超时时间， withDynamicGap方法可以动态设置超时时间
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(EventTimeSessionWindows.withGap(Time.seconds(10)));

    }
}
