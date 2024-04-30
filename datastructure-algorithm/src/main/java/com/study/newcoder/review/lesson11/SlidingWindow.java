package com.study.newcoder.review.lesson11;

import java.util.Arrays;
import java.util.LinkedList;

public class SlidingWindow {
    /**
     * 滑动窗口
     * @param ary 数组
     * @param w 窗口大小
     */
    public int[] slide(int[] ary, int w) {
        if (null == ary || ary.length < 0) {
            return new int[]{};
        }

        int[] result = new int[ary.length - w + 1];
        int index = 0;
        LinkedList<Integer> doublyQueue = new LinkedList<>();
        for(int i = 0; i < ary.length; i ++) {
            while (!doublyQueue.isEmpty() && ary[doublyQueue.peekLast()] < ary[i]) {
                doublyQueue.pollLast();
            }
            doublyQueue.addLast(i);
            if ((i - w) == doublyQueue.peekFirst()) {
                doublyQueue.pollFirst();
            }
            if (i >= w - 1) {
                result[index ++] = ary[doublyQueue.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ary = new int[]{4,3,5,4,3,3,6,7};
        System.out.println(Arrays.toString(new SlidingWindow().slide(ary, 3)));
    }
}
