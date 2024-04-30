package com.study.newcoder.lesson12;

public class Power {
    public static boolean is2Power(int a) {
        // 取最右边的1，看是否和原数相等
        return (a & (~a + 1)) == a;
    }

    public static boolean is4Power(int b) {
        // 4的幂一定是 ....101010101上
        return (b & (b - 1)) == 0 && (b & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        System.out.println(is2Power(16));
        System.out.println(is4Power(16));
    }
}
