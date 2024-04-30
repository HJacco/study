package com.study.newcoder.lesson01;

public class BitOperate {
    public void findTwoNumber(int[] ary) {
        int eor = 0;
        for (int i = 0; i < ary.length; i ++) {
            eor = eor ^ ary[i];
        }

        // 提前一个数字最右侧的1
        int rightOne = eor & (~eor + 1);
        int _eor = 0;
        for (int i = 0; i < ary.length; i ++) {
            if ((ary[i] & rightOne) == 1) {
                _eor = _eor ^ ary[i];
            }
        }
        System.out.println("two number:" + _eor + ", " + (eor ^ _eor));
    }

    public static void main(String[] args) {
        int[] ary = {1,2, 1, 5,3, 3, 4, 4, 4, 4};
        new BitOperate().findTwoNumber(ary);
    }
}
