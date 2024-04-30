package com.study.newcoder.lesson01;

import java.util.Arrays;

/**
 * 经典排序
 */
public class ClassicSort {

    /**
     * 插入排序
     * 对每个下标选出对应的最小或者最大的元素
     */
    public int[] selectSort(int[] ary) {
        for (int i = 0; i < ary.length - 1; i ++) {
            for (int j = i + 1; j < ary.length; j ++) {
                if (ary[i] > ary[j]) {
                    int t = ary[i];
                    ary[i] = ary[j];
                    ary[j] = t;
                }
            }
        }
        return ary;
    }

    /**
     * 游标前都是有序的
     */
    public void insertSort(int[] ary) {
        for (int i = 1; i < ary.length; i ++) {
            for (int j = i - 1; j >=0; j --) {
                if (ary[j + 1] > ary[j]) {
                    int t = ary[j + 1];
                    ary[j + 1] = ary[j];
                    ary[j] = t;
                } else {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(ary));
    }

    public void bubbleSort(int[] ary) {
        for (int i = 0; i < ary.length; i ++) {
            for (int j = 0; j < ary.length - i - 1; j ++) {
                if (ary[j] < ary[j + 1]) {
                    int t = ary[j + 1];
                    ary[j + 1] = ary[j];
                    ary[j] = t;
                }
            }
        }
        System.out.println(Arrays.toString(ary));
    }


    public static void main(String[] args) {
        int[] ary = {5,3,1,2,6};
//        new ClassicSort().insertSort(ary);

        new ClassicSort().bubbleSort(ary);
    }
}
