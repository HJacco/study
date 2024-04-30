package com.study.newcoder.lesson02;

import java.util.Arrays;

public class QuickSort {
    public void sort(int[] ary) {
        if (ary == null || ary.length < 2) {
            return;
        }
        sort(ary, 0, ary.length - 1);

    }

    public void sort(int[] ary, int l, int r) {
        if (l == r) {
            return;
        }
        int p = ary[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && ary[j] >= p) {
                j --;
            }
            while (i < j && ary[i] <= p) {
                i ++;
            }
            if (i != j) {
                int t = ary[j];
                ary[j] = ary[i];
                ary[i] = t;
            }
        }
        ary[l] = ary[i];
        ary[i] = p;
        sort(ary, l, i);
        sort(ary, i + 1, r);
    }

    public static void main(String[] args) {
        int[] ary = {1,3,4,2,5};
        new QuickSort().sort(ary);
        System.out.println(Arrays.toString(ary));
    }
}
