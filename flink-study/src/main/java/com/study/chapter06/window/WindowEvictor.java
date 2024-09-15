package com.study.chapter06.window;

import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.evictors.Evictor;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.runtime.operators.windowing.TimestampedValue;

public class WindowEvictor {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> source = env.fromElements(1, 2, 4);

        AllWindowedStream<Integer, GlobalWindow> trigger = source.windowAll(GlobalWindows.create())
                .evictor(new Evictor<Integer, GlobalWindow>() {
                    @Override
                    public void evictBefore(Iterable<TimestampedValue<Integer>> elements, int size, GlobalWindow window, EvictorContext evictorContext) {

                    }

                    @Override
                    public void evictAfter(Iterable<TimestampedValue<Integer>> elements, int size, GlobalWindow window, EvictorContext evictorContext) {

                    }
                });
    }
}
