package com.study.chapter02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;

public class WordCountImplementWithDataSetApi {
    public static void main(String[] args) throws Exception {
        // 创建环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> datasource = env.readTextFile("/Users/huangchongjie/project/baidu/ods/etl/dcec/idm/idm_graph_merge_v29.sh");

        FlatMapOperator<String, Tuple2<String, Long>> wordAndOne = datasource.flatMap((FlatMapFunction<String, Tuple2<String, Long>>) (value, out) -> {
            String[] words = value.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        });

        UnsortedGrouping<Tuple2<String, Long>> wordAndOneUG = wordAndOne.groupBy(0);

        AggregateOperator<Tuple2<String, Long>> sum = wordAndOneUG.sum(1);

        sum.print();
    }
}
