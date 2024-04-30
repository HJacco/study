package com.study.newcoder.lesson11;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {
    static class Employee {
        public int happy;
        public List<Employee> subordinates = new ArrayList<>();

    }

    static class ReturnInfo{
        public int laiMaxHappy;
        public int buMaxHappy;

        public ReturnInfo(int laiMaxHappy, int buMaxHappy) {
            this.laiMaxHappy = laiMaxHappy;
            this.buMaxHappy = buMaxHappy;
        }
    }

    public int getMaxHappy(Employee boss) {
        ReturnInfo result = process(boss);
        return Math.max(result.buMaxHappy, result.laiMaxHappy);
    }

    public static ReturnInfo process(Employee e) {
        if (e.subordinates.isEmpty()) {
            return new ReturnInfo(e.happy, 0);
        }
        int lai = e.happy;
        int bu = 0;
        for (Employee next : e.subordinates) {
            ReturnInfo info = process(next);
            lai = lai + info.buMaxHappy;
            bu = bu + Math.max(info.buMaxHappy, info.laiMaxHappy);
        }
        return new ReturnInfo(lai, bu);
    }
}
