package com.study.chapter06.water;

import com.study.common.entity.WaterSensor;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.time.Duration;

public class DelayDataProcess {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<WaterSensor> socketStream = env.socketTextStream("localhost", 7777)
                .map(new WaterStrategyExample.MapFunctionImpl());
        // 适合无序流， 设置固定延迟时间
        WatermarkStrategy<WaterSensor> unOrderStrategy = WatermarkStrategy.<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(5L))
                .withTimestampAssigner((SerializableTimestampAssigner<WaterSensor>) (element, recordTimestamp) -> element.getTs());

        OutputTag<WaterSensor> lateTag = new OutputTag<>("late-data", Types.POJO(WaterSensor.class));

        SingleOutputStreamOperator<String> mainStream = socketStream.assignTimestampsAndWatermarks(unOrderStrategy)
                .keyBy((KeySelector<WaterSensor, String>) value -> value.getId())
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                // 窗口延迟关闭， 只能运用到基于事件时间的窗口函数上
                .allowedLateness(Time.seconds(3))
                .sideOutputLateData(lateTag)
                .process(new ProcessWindowFunction<WaterSensor, String, String, TimeWindow>() {
                    @Override
                    public void process(String s, ProcessWindowFunction<WaterSensor, String, String, TimeWindow>.Context context, Iterable<WaterSensor> elements, Collector<String> out) throws Exception {
                        System.out.println("key : " + s + "; start time : " + context.window().getStart() + " end time : " + context.window().getEnd());
                        StringBuilder sb = new StringBuilder();
                        for (WaterSensor element : elements) {
                            sb.append(element.getId());
                        }
                        out.collect(sb.toString());
                    }
                });

        // 使用侧输出流处理延迟数据
        mainStream.getSideOutput(lateTag).print();
        // 主流处理
        mainStream.print();

    }

    static class MapFunctionImpl implements MapFunction<String, WaterSensor> {

        @Override
        public WaterSensor map(String value) throws Exception {
            String[] fields = value.split(",");
            return new WaterSensor(fields[0], Long.parseLong(fields[1]), Integer.parseInt(fields[2]));
        }
    }

}
