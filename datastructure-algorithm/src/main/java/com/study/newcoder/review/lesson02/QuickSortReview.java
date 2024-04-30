package com.study.newcoder.review.lesson02;

import com.study.common.util.ArrayCommonOperate;

import java.util.Arrays;

public class QuickSortReview {

    public static void quickSort(int[] ary) {
        if (ary == null || ary.length < 2) {
            return;
        }
        quickSort(ary, 0, ary.length - 1);
    }

    public static void quickSort(int[] ary, int left, int right) {
        if (right == left) {
            return;
        }
        // 以左边元素作为基准元素
        int pivot = ary[left];
        int l = left, r = right;
        while (l < r) {
            while (l < r && ary[r] >= pivot) {
                r --;
            }
            while (l < r && ary[l] <= pivot) {
                l ++;
            }
            if (l != r) {
                ArrayCommonOperate.swap(ary, l, r);
            }
        }
        // 当前位置与最左边元素交换
        ary[left] = ary[l];
        ary[l] = pivot;

        quickSort(ary, left, l);
        quickSort(ary, l + 1, right);
    }

    public static void main(String[] args) {
        int[] a = {4,2,1,5,8};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}
