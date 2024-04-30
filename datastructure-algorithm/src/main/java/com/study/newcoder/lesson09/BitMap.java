package com.study.newcoder.lesson09;

public class BitMap {
    public static void main(String[] args) {
        int[] bitAry = new int[10]; // 表示32bit * 10 -> 320个bit

        // bitAry[0] 代表 0 - 31的bit位
        // bitAry[1] 代表 32 - 63的bit位

        // 接下来要取178个bit状态
        int i = 178;

        int numIndex = 178 / 32; // 定位出178位所在的数字
        int bitIndex = numIndex % 32; // 定位出位

        // 获取178位的状态
        int s = (bitAry[numIndex] >> bitIndex) & 1;

        // 把178位设置为1
        bitAry[numIndex] = bitAry[numIndex] | (1 << bitIndex);

        // 把178位设置为0
        bitAry[numIndex] = bitAry[numIndex] & (~(1 << bitIndex));

    }
}
