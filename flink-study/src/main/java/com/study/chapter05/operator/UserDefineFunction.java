package com.study.chapter05.operator;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class UserDefineFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.fromElements(1,2,3,4,5).map(new MapRichFunctionImpl()).print();
        env.execute();
    }

    /**
     * open, close 生命周期方法，一个并行子任务只会被调用一次
     */
    static class MapRichFunctionImpl extends RichMapFunction<Integer, String> {
        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            System.out.println("当前open的map任务id:" + getRuntimeContext().getIndexOfThisSubtask());
        }

        @Override
        public void close() throws Exception {
            super.close();
            System.out.println("当前close的map任务id:" + getRuntimeContext().getIndexOfThisSubtask());

        }

        @Override
        public String map(Integer value) throws Exception {
            return "number:" + value;
        }
    }
}
