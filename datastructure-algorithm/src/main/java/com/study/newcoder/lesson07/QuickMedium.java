package com.study.newcoder.lesson07;

import java.util.PriorityQueue;

public class QuickMedium {
    public static int getMediumNum(int[] ary) {
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else {
                return -1;
            }
        });
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else {
                return 1;
            }
        });

        for (int i = 0;  i < ary.length; i ++) {
            if (bigHeap.isEmpty() || ary[i] <= bigHeap.peek()) {
                bigHeap.add(ary[i]);
            } else {
                smallHeap.add(ary[i]);
            }
            if (bigHeap.size() == smallHeap.size() + 2) {
                smallHeap.add(bigHeap.poll());
            }
            if (smallHeap.size() == bigHeap.size() + 2) {
                bigHeap.add(smallHeap.poll());
            }

        }
        // 偶数个数字
        if (((bigHeap.size() + smallHeap.size()) & 1) == 0) {
            return (bigHeap.peek() + smallHeap.peek()) / 2;
        }
        return bigHeap.size() > smallHeap.size() ? bigHeap.peek() : smallHeap.peek();
    }
}
