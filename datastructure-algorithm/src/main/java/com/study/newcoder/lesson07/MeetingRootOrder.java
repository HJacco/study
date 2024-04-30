package com.study.newcoder.lesson07;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRootOrder {
    static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int orderNum(Program[] programs, int start) {
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int result = 0;
        for (int i = 0 ; i < programs.length; i ++) {
            if (start <= programs[i].start) {
                result ++;
                start = programs[i].end;
            }
        }
        return result;
    }
}
