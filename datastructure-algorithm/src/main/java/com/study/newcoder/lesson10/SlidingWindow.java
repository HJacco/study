package com.study.newcoder.lesson10;

import java.util.LinkedList;

public class SlidingWindow {
    public int width;

    public int[] slide(int[] ary) {
        if (ary == null || ary.length < this.width) {
            return null;
        }
        int[] result = new int[ary.length - this.width + 1];
        int index = 0;
        // 双端队列，存的下标
        LinkedList<Integer> queue = new LinkedList<>();
        // i 代表右边界
        for (int i = 0; i < ary.length; i ++) {
            while (!queue.isEmpty() && ary[queue.peekLast()] <= ary[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 队列里的元素超过左边界，即过期了
            if (queue.peekFirst() == (this.width - i)) {
                queue.pollFirst();
            }
            // 窗口成型
            if (i >= this.width - 1) {
                result[index ++] = ary[queue.peekFirst()];
            }
        }
        return result;
    }
}
