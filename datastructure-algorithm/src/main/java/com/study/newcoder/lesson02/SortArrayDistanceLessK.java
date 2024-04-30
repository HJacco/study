package com.study.newcoder.lesson02;

import java.util.PriorityQueue;

public class SortArrayDistanceLessK {
    public void sort(int[] ary, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(ary.length, k); index ++) {
            heap.add(ary[index]);
        }
        int i = 0;
        for (; index < ary.length; i ++, index ++) {
            heap.add(ary[index]);
            ary[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            ary[i ++] = heap.poll();
        }
    }
}
