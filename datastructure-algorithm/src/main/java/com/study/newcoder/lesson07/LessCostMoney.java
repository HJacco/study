package com.study.newcoder.lesson07;

import java.util.PriorityQueue;

public class LessCostMoney {

    public static int lessCost(int[] ary) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < ary.length; i ++) {
            queue.add(ary[i]);
        }

        int sum = 0;
        int current = 0;
        while (queue.size() > 1) {
            current = queue.poll() + queue.poll();
            sum += current;
            queue.add(current);
        }
        return sum;
    }
}
