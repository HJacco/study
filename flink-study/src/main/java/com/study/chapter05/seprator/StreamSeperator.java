package com.study.chapter05.seprator;

import com.study.common.entity.WaterSensor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

public class StreamSeperator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 最简单的分流可以通过对同一个流多次调用filter实现，但是比较冗余
        DataStreamSource<Integer> source = env.fromElements(1,2,3,4,5,6);

        source.filter(i -> i % 2  == 0).print();
        source.filter(i -> i % 2 == 1).print();

        sideOutputStream(env);

        env.execute();
    }

    static void sideOutputStream(StreamExecutionEnvironment env) {
        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("1", 1000L, 1),
                new WaterSensor("2", 1001L, 2),
                new WaterSensor("3", 1002L, 3),
                new WaterSensor("4", 1003L, 4),
                new WaterSensor("5", 1004L, 5),
                new WaterSensor("6", 1005L, 6),
                new WaterSensor("7", 1006L, 7)
        );
        OutputTag<WaterSensor> tag1 = new OutputTag<>("less3", Types.POJO(WaterSensor.class));
        OutputTag<WaterSensor> tag2 = new OutputTag<>("less4", Types.POJO(WaterSensor.class));
        OutputTag<WaterSensor> tag3 = new OutputTag<>("less6", Types.POJO(WaterSensor.class));

        SingleOutputStreamOperator<WaterSensor> process = source.process(new ProcessFunction<WaterSensor, WaterSensor>() {
            @Override
            public void processElement(WaterSensor value, ProcessFunction<WaterSensor, WaterSensor>.Context ctx, Collector<WaterSensor> out) throws Exception {
                if (Integer.parseInt(value.getId()) <= 3) {
                    ctx.output(tag1, value);
                } else if (Integer.parseInt(value.getId()) <= 4) {
                    ctx.output(tag2, value);
                } else if (Integer.parseInt(value.getId()) <= 6) {
                    ctx.output(tag3, value);
                } else {
                    out.collect(value);
                }
            }
        });

        SideOutputDataStream<WaterSensor> tag1Stream = process.getSideOutput(tag1);
        SideOutputDataStream<WaterSensor> tag2Stream = process.getSideOutput(tag2);
        SideOutputDataStream<WaterSensor> tag3Stream = process.getSideOutput(tag3);

        tag1Stream.print();
        tag2Stream.print();
        tag3Stream.print();

        process.print();
    }
}
