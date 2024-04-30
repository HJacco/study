package com.study.newcoder.review.lesson02;

import com.study.common.util.ArrayCommonOperate;

import java.util.Arrays;

public class HeapReview {

    /**
     * index上浮操作
     */
    public static void heapInsert(int[] ary, int index) {
        while (ary[(index - 1) / 2] < ary[index]) {
            ArrayCommonOperate.swap(ary, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    /**
     * index位置下沉操作
     */
    public static void heapify(int[] ary, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && ary[left + 1] > ary[left] ? left + 1 : left;
            largest = ary[largest] < ary[index] ? index : largest;
            if (largest == index) {
                break;
            }
            ArrayCommonOperate.swap(ary, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    public static void heapSort(int[] ary) {
        if (null == ary || ary.length < 2) {
            return;
        }
        // 首先构造堆；从0开始对元素做上浮操作
        for (int i = 0; i < ary.length; i ++) {
            heapInsert(ary, i);
        }
        int heapSize = ary.length;
        // 把堆顶元素和最后一个元素交换，然后对堆顶元素做下沉操作，堆大小减一
        ArrayCommonOperate.swap(ary, 0, -- heapSize);
        while (heapSize > 0) {
            heapify(ary, 0, heapSize);
            ArrayCommonOperate.swap(ary, 0, -- heapSize);
        }
    }

    public static void main(String[] args) {
        int[] a = {4,3,1,7,5};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
