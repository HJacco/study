package com.study.chapter07;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.TimerService;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Duration;

public class KeyedStreamFunctionExample {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sock = env.fromElements("1", "2", "3", "4");

        sock.keyBy((KeySelector<String, Integer>) Integer::parseInt)
                .process(new KeyedProcessFunction<Integer, String, String>() {
                    @Override
                    public void processElement(String value, KeyedProcessFunction<Integer, String, String>.Context ctx, Collector<String> out) throws Exception {
                        Integer key = ctx.getCurrentKey();

                        TimerService timerService = ctx.timerService();

//                        // 基于事件时间的定时任务
//                        long currentEventTime = ctx.timestamp();
//                        timerService.registerEventTimeTimer(5000L);
//                        System.out.println("event key :" + key + "; 当前时间=" + currentEventTime + "; 注册了一个5s的定时器");

                        // 基于处理时间的定时任务
                        long currentTs = timerService.currentProcessingTime();
                        timerService.registerProcessingTimeTimer(currentTs + 5000L);
                        System.out.println("process key :" + key + "; 当前时间=" + currentTs + "; 注册了一个5s的定时器");

                    }

                    @Override
                    public void onTimer(long timestamp, KeyedProcessFunction<Integer, String, String>.OnTimerContext ctx, Collector<String> out) throws Exception {
                        super.onTimer(timestamp, ctx, out);
                        Integer key = ctx.getCurrentKey();
                        System.out.println("key : " + key + "; 定时器触发了， 触发时间L:" + timestamp);
                    }
                }).print();
        env.execute();
    }
}
