package com.study.newcoder.review.lesson01;

import com.study.common.util.ArrayCommonOperate;

import java.util.Arrays;

/**
 * 经典排序复习
 */
public class ClassicSortReview {

    /**
     * 插入排序
     * 对数组在l 到r范围内排序
     */
    public static void insertSort(int[] ary, int l, int r) {
        if (null == ary || ary.length < 2) {
            return;
        }
        for (int i = l + 1; i <= r; i ++) {
            for (int j = i - 1; j >= l; j --) {
                if (ary[j + 1] < ary[j]) {
                    ArrayCommonOperate.swap(ary, j + 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     */
    public static void bubbleSort(int[] ary, int l, int r) {
        if (null == ary || ary.length < 2) {
            return;
        }
        for (int i = 0; i < r - l; i ++) { // n个数字进行n-1趟比较
            for (int j = l; j < r - i; j ++) {
                if (ary[j] > ary[j + 1]) {
                    ArrayCommonOperate.swap(ary, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] ary, int l, int r) {
        if (null == ary || ary.length < 2) {
            return;
        }
        for (int i = l; i < r; i ++) {
            for (int j = i + 1; j <=r; j ++) {
                if (ary[i] > ary[j]) {
                    ArrayCommonOperate.swap(ary, i, j);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] a = {6,3,2,1,4};
//        insertSort(a, 0, a.length - 1);
//        bubbleSort(a, 0, a.length - 1);
        selectSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }
}
