package com.study.common.util;

public class ArrayCommonOperate {

    public static void swap (int[] ary, int l, int r) {
        if (null == ary || l >= ary.length || r >= ary.length || l < 0 || r < 0) {
            return;
        }
        int t = ary[l];
        ary[l] = ary[r];
        ary[r] = t;
    }
}
