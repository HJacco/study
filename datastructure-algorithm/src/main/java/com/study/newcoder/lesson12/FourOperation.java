package com.study.newcoder.lesson12;

public class FourOperation {

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 异或相当于无进制相加
            sum = a ^ b;
            // 取进位状态
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * a的相反数， 取反 + 1
     */
    public static int negNum(int a) {
        return add(~a,  1);
    }

    public static int minos(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int result = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                result = add(result, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return result;
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int result = 0;
        for (int i = 31; i > -1; i = minos(i, 1)) {
            if ((x >> i) >= y) {
                result |= (1 << x);
                x = minos(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(result) : result;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }
}
