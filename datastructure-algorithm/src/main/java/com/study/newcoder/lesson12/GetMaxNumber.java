package com.study.newcoder.lesson12;

public class GetMaxNumber {
    public static int getMax1(int a, int b) {
        int c = a - b;
        int returnA = getSign(c);
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static int getMax2(int a, int b) {
        int c = a - b;

        int aSign = getSign(a);
        int bSign = getSign(b);
        int cSign = getSign(c);

        // a b 符号相同为1, 不同为0
        int sameSign = flip(aSign ^ bSign);
        // a b 符号相同为0， 不同为1
        int diffSign = flip(sameSign);

        // a 和b符号相同时，c是正数返回a
        // a 和b符号不同时，a是正数返回a
        int returnA = sameSign * cSign + diffSign * aSign;
        int returnB = flip(returnA);
        return returnA * a + returnB * b;
    }

    /**
     * a是正数，返回1
     * a是复数，返回0
     */
    public static int getSign(int a) {
        return flip((a >> 31) & 1);
    }
    public static int flip(int a) {
        return a ^ 1;
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
    }
}
