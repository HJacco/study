package com.study.chapter05.union;

import com.study.common.entity.WaterSensor;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamUnion {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> source1 = env.fromElements(1, 2, 3, 5);
        DataStreamSource<Integer> source2 = env.fromElements(4, 6);
//        source1.union(source2).print();

        // 流连接  connect, connect不限制流的数据类型
//        connect(env);

        connectKeyedBy(env);
        env.execute();
    }

    static void connect(StreamExecutionEnvironment env) {
        DataStreamSource<Integer> source1 = env.fromElements(1, 2, 3, 5);

        DataStreamSource<WaterSensor> source2 = env.fromElements(
                new WaterSensor("1", 1000L, 1),
                new WaterSensor("2", 1001L, 2),
                new WaterSensor("3", 1002L, 3),
                new WaterSensor("4", 1003L, 4),
                new WaterSensor("5", 1004L, 5),
                new WaterSensor("6", 1005L, 6),
                new WaterSensor("7", 1006L, 7)
        );
        ConnectedStreams<Integer, WaterSensor> connectStream = source1.connect(source2);

        SingleOutputStreamOperator<String> mapStream = connectStream.map(new CoMapFunction<Integer, WaterSensor, String>() {
            @Override
            public String map1(Integer value) throws Exception {
                return value.toString();
            }

            @Override
            public String map2(WaterSensor value) throws Exception {
                return value.getId();
            }
        });
        mapStream.print();
    }

    static void connectKeyedBy(StreamExecutionEnvironment env) {
        DataStreamSource<Integer> source1 = env.fromElements(1, 2, 3, 5);

        DataStreamSource<WaterSensor> source2 = env.fromElements(
                new WaterSensor("1", 1000L, 1),
                new WaterSensor("2", 1001L, 2),
                new WaterSensor("3", 1002L, 3),
                new WaterSensor("4", 1003L, 4),
                new WaterSensor("5", 1004L, 5),
                new WaterSensor("6", 1005L, 6),
                new WaterSensor("7", 1006L, 7)
        );
        ConnectedStreams<Integer, WaterSensor> connectStream = source1.connect(source2);

        // 会把key相同的数据分到一个分区里
        ConnectedStreams<Integer, WaterSensor> keyedStream = connectStream.keyBy(
                (KeySelector<Integer, Integer>) value -> value,
                (KeySelector<WaterSensor, Integer>) value -> Integer.parseInt(value.getId())
        );
        
        keyedStream.process(new CoProcessFunction<Integer, WaterSensor, String>() {
            Map<Integer, List<String>> s1Cache = new HashMap<>();
            Map<Integer, List<WaterSensor>> s2Cache = new HashMap<>();
            @Override
            public void processElement1(Integer value, CoProcessFunction<Integer, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                if (s1Cache.containsKey(value)) {
                    s1Cache.get(value).add(value.toString());
                }
                if (!s1Cache.containsKey(value)) {
                    List<String> vs = new ArrayList<>();
                    vs.add(value.toString());
                    s1Cache.put(value, vs);
                }
                if (s2Cache.containsKey(value)) {
                    for (WaterSensor v : s2Cache.get(value)) {
                        out.collect(v.toString());
                    }
                }
            }

            @Override
            public void processElement2(WaterSensor value, CoProcessFunction<Integer, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                if (s2Cache.containsKey(Integer.parseInt(value.getId()))) {
                    s1Cache.get(Integer.parseInt(value.getId())).add(value.toString());
                }
                if (!s2Cache.containsKey(Integer.parseInt(value.getId()))) {
                    List<WaterSensor> vs = new ArrayList<>();
                    vs.add(value);
                    s2Cache.put(Integer.parseInt(value.getId()), vs);
                }
                if (s1Cache.containsKey(Integer.parseInt(value.getId()))) {
                    for (String v : s1Cache.get(Integer.parseInt(value.getId()))) {
                        out.collect(v);
                    }
                }
            }
        }).print();

    }
}
