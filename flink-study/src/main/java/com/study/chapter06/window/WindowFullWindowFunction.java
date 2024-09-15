package com.study.chapter06.window;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;

public class WindowFullWindowFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<String> socketStream = env.socketTextStream("localhost", 7777);

        // 全窗口计算会先收集窗口内的数据并在内部缓存，等窗口需要输出结果的时候再取出数据进行计算
        socketStream.keyBy((KeySelector<String, String>) value -> value.split(",")[0])
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new ProcessWindowFunction<String, String, String, TimeWindow>() {
                    @Override
                    public void process(String s, ProcessWindowFunction<String, String, String, TimeWindow>.Context context, Iterable<String> elements, Collector<String> out) throws Exception {
                        System.out.println("key:" + s + "; window start time:" + context.window().getStart() + "; window end time :" + context.window().getEnd());
                        Iterator<String> it = elements.iterator();
                        StringBuilder tmp = new StringBuilder();
                        while (it.hasNext()) {
                            tmp.append(it.next());
                        }
                        out.collect(tmp.toString());
                    }
                }).print();
        env.execute();
    }
}
