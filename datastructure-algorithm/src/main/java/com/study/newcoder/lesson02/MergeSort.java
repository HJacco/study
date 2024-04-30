package com.study.newcoder.lesson02;

import java.util.Arrays;

public class MergeSort {
    public void sort(int[] ary) {
        if (ary == null || ary.length < 2) {
            return;
        }
        sort(ary, 0, ary.length - 1);
    }

    // 切分 合并
    public void sort(int[] ary, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        sort(ary, l, mid);
        sort(ary, mid + 1, r);
        merge(ary, l, mid, r);

    }

    public void merge(int[] ary, int l, int mid, int r) {
        // 辅助数组
        int[] help = new int[r - l + 1];
        // 三个指针， 一个指针指向左边子序列，一个指向右边， 一个指向辅助数组
        int p1 = l, p2 = mid + 1, p = 0;
        while (p1 <= mid && p2 <= r) {
            help[p ++] = ary[p1] < ary[p2] ? ary[p1 ++] : ary[p2 ++];
        }

        while (p1 <= mid) {
            help[p++] = ary[p1 ++];
        }
        while (p2 <= r) {
            help[p ++] = ary[p2 ++];
        }

        //辅助数组copy会原数组
        for (int i = 0; i < help.length; i ++) {
            ary[i + l] = help[i];
        }
    }


    public static void main(String[] args) {
        int[] ary = {5,3,1,2,7};
        new MergeSort().sort(ary);
        System.out.println(Arrays.toString(ary));
    }

}
