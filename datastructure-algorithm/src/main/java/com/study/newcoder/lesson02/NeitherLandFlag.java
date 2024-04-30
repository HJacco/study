package com.study.newcoder.lesson02;

import java.util.Arrays;


public class NeitherLandFlag {
    /**
     * 给定一个数组和一个目标数字， 将数组处理成左边全是小于目标数字， 右边全是大于目标数字
     */
    public void move(int[] ary, int target) {
        if (ary == null || ary.length < 2) {
            return;
        }
        int left = -1;
        for (int i = 0; i < ary.length; i ++) {
            if (ary[i] <= target) {
                left ++;
                int t = ary[left];
                ary[left] = ary[i];
                ary[i] = t;
            }
        }
    }

    /**
     * 给定一个数组和一个目标数字， 将数组处理成左边全是小于目标数字， 右边全是大于目标数字, 中间全是等于目标数字
     */
    public void move2(int[] ary, int target) {
        if (ary == null || ary.length < 2) {
            return;
        }
        int left = -1;
        int right = ary.length;
        for (int i = 0; i < ary.length && i < right; ) {
            if (ary[i] < target) {
                left ++;
                int t = ary[left];
                ary[left] = ary[i];
                ary[i] = t;
                i ++;
            } else if (ary[i] > target) {
                right --;
                int t = ary[right];
                ary[right] = ary[i];
                ary[i] = t;
            } else {
                i ++;
            }
        }
    }

    public void pp(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        int[] a = {5, 1, 3, 4, 2, 6};
        new NeitherLandFlag().move(a, 3);
        System.out.println(Arrays.toString(a));

        int[] b = {5, 1, 3, 4, 2, 6, 4};
        new NeitherLandFlag().move2(b, 4);
        System.out.println(Arrays.toString(b));
        int i = 1;
        new NeitherLandFlag().pp(++ i);
        System.out.println("after:" + i);
    }
}
