package com.study.chapter06.window;

import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;

public class WindowTrigger {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> source = env.fromElements(1, 2, 4);

        AllWindowedStream<Integer, GlobalWindow> trigger = source.windowAll(GlobalWindows.create())
                .trigger(new Trigger<Integer, GlobalWindow>() {
                    @Override
                    public TriggerResult onElement(Integer element, long timestamp, GlobalWindow window, TriggerContext ctx) throws Exception {
                        return null;
                    }

                    @Override
                    public TriggerResult onProcessingTime(long time, GlobalWindow window, TriggerContext ctx) throws Exception {
                        return null;
                    }

                    @Override
                    public TriggerResult onEventTime(long time, GlobalWindow window, TriggerContext ctx) throws Exception {
                        return null;
                    }

                    @Override
                    public void clear(GlobalWindow window, TriggerContext ctx) throws Exception {

                    }
                });
    }
}
