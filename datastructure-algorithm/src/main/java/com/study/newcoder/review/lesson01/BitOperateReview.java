package com.study.newcoder.review.lesson01;

import java.util.Arrays;

public class BitOperateReview {
    /**
     * 查找数组中出现奇数次的一个数
     */
    public static int findOneNumber(int[] ary) {
        if (null == ary || ary.length == 0) {
            return -1;
        }

        if (ary.length == 2) {
            throw new IllegalArgumentException("ary can not be two length");
        }

        if (ary.length == 1) {
            return ary[0];
        }

        int result = 0;
        for (int i = 0; i < ary.length; i ++) {
            result ^= ary[i];
        }
        return result;
    }

    /**
     * 数组中只有两个数出现奇数次，其余都是出现偶数次，找出两个数字
     */
    public static int[] findTwoNumber(int[] ary) {
        if (null == ary || ary.length == 0) {
            return new int[]{-1, -1};
        }

        if (ary.length == 2) {
            return ary;
        }

        int notOr = 0;
        for (int i = 0; i < ary.length; i ++) {
            notOr = notOr ^ ary[i];
        }
        // 找出亦或结果中位为1的数字
        int oneBit = notOr & (~notOr + 1);
        int one = 0;
        for (int i = 0; i < ary.length; i ++) {
            if ((ary[i] & oneBit) == 1) {
                one = one ^ ary[i];
            }
        }

        int two = notOr ^ one;
        return new int[]{one, two};
    }

    public static void main(String[] args) {
        int[] a = {1,3,4,6,7,4,3,1};
        System.out.println(Arrays.toString(findTwoNumber(a)));
    }
}
