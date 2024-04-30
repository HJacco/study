package com.study.newcoder.review.lesson09;

public class BitMap {

    public static void main(String[] args) {
        long[] bitMapArray = new long[10]; // 10 * 64 个bit 位

        // 设置310位为1
        // 首先找到310位所在的index;
        int index = 310 / 64;
        // 找到第310位所在的位的位置
        int bitIndex = 310 % 64;
        // 设置bitIndex 位为1
        bitMapArray[index] = (1L << bitIndex) | bitMapArray[index];

        // 获取310位的状态
        long s = (bitMapArray[index] >> bitIndex) & 1;

        // 设置310位的状态位0
        bitMapArray[index] = bitMapArray[index] & (~((1L << bitIndex)));

    }

}
