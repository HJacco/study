package com.study.chapter05.source;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class CustomSource {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.addSource(new PeriodGenerateData()).print();
        env.execute();
    }

    static class PeriodGenerateData implements SourceFunction<Integer> {
        private boolean isStop = false;
        @Override
        public void run(SourceContext<Integer> ctx) throws Exception {
            int i = 0;
            while (!isStop) {
                ctx.collect(i ++);
                Thread.sleep(2000L);
            }
        }

        @Override
        public void cancel() {
            isStop = true;
        }
    }
}
