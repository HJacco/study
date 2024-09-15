package com.study.chapter06.water;

import com.study.common.entity.WaterSensor;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

public class WaterStrategyExample {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<WaterSensor> socketStream = env.socketTextStream("localhost", 7777)
                .map(new MapFunctionImpl());

        // 适合有序流， 时间戳单调递增
        WatermarkStrategy<WaterSensor> watermarkStrategy = WatermarkStrategy.<WaterSensor>forMonotonousTimestamps()
                .withTimestampAssigner((SerializableTimestampAssigner<WaterSensor>) (element, recordTimestamp) -> element.getTs());

        // 适合无序流， 设置固定延迟时间
        WatermarkStrategy<WaterSensor> unOrderStrategy = WatermarkStrategy.<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(5L))
                .withTimestampAssigner((SerializableTimestampAssigner<WaterSensor>) (element, recordTimestamp) -> element.getTs());

        // 周期性生成水位线
        WatermarkStrategy<WaterSensor> periodStrategy = new CustomWatermarkStrategy();

        socketStream.assignTimestampsAndWatermarks(watermarkStrategy)
                .keyBy((KeySelector<WaterSensor, String>) value -> value.getId())
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
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
                }).print();

    }

    static class MapFunctionImpl implements MapFunction<String, WaterSensor> {

        @Override
        public WaterSensor map(String value) throws Exception {
            String[] fields = value.split(",");
            return new WaterSensor(fields[0], Long.parseLong(fields[1]), Integer.parseInt(fields[2]));
        }
    }

    static class CustomWatermarkStrategy implements WatermarkStrategy<WaterSensor> {

        @Override
        public WatermarkGenerator<WaterSensor> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
            return new CustomBoundedOutOfOrdernessGenerator();
        }

        @Override
        public TimestampAssigner<WaterSensor> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
            return (SerializableTimestampAssigner<WaterSensor>) (element, recordTimestamp) -> element.getTs();
        }
    }

    static class CustomBoundedOutOfOrdernessGenerator implements WatermarkGenerator<WaterSensor> {
        private Long delayTime = 5000L;
        private Long maxTs = - Long.MAX_VALUE + delayTime + 1L;

        /**
         * 每来一条数据，就调用一次
         */
        @Override
        public void onEvent(WaterSensor event, long eventTimestamp, WatermarkOutput output) {
            maxTs = Math.max(maxTs, event.getTs());
        }

        @Override
        public void onPeriodicEmit(WatermarkOutput output) {
            // 发射水位线，默认200ms调用一次， 系统框架周期性调用，通过env.getConfig().setAutoWatermarkInterval(400L)
            output.emitWatermark(new Watermark(maxTs - delayTime - 1));
        }
    }

}
