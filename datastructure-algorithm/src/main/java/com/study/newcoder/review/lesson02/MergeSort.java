package com.study.newcoder.review.lesson02;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] ary) {
        if (null == ary || ary.length < 2) {
            return;
        }
        mergeSort(ary, 0, ary.length - 1);
    }

    /**
     * 在left 到 right范围内对数组ary排序
     */
    public static void mergeSort(int[] ary, int left, int right) {
        if (left == right) {
            return ;
        }
        int mid = left + (right - left) / 2;
        mergeSort(ary, left, mid);
        mergeSort(ary, mid + 1, right);

        merge(ary, left, mid, right);
    }

    public static void merge(int[] ary, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int l = left, r = mid + 1, i = 0;
        while (l <= mid && r <= right) {
            help[i ++] = ary[l] < ary[r] ? ary[l ++] : ary[r ++];
        }
        while (l <= mid) {
            help[i ++] = ary[l ++];
        }
        while (r <= right) {
            help[i ++] = ary[r ++];
        }

        for (int j = 0; j < help.length; j ++) {
            ary[left + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] a = {3,5,2,1,4};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
