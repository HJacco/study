package com.study.newcoder.lesson02;

import com.study.common.util.ArrayCommonOperate;

import java.util.Arrays;

public class Heap {
    /**
     *  一个数字处于index位置，向上浮动
     *
     **/
    public void heapInsert(int[] ary, int index) {
        // 当前节点大于父节点
        while(ary[index] > ary[(index - 1) / 2]) {
            ArrayCommonOperate.swap(ary, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * index位置的数向下移动
     */
    public void heapify(int[] ary, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找出较大的子节点
            // 当存在右子节点并且右子节点比左子节点大的时候返回右子节点
            int largest = left + 1 < heapSize && ary[left + 1] > ary[left] ? left + 1 : left;
            // 再拿较大子节点跟当前节点比较
            largest = ary[index] < ary[largest] ? largest : index;
            // 当前节点比较大，则不向下移动
            if (largest == index) {
                break;
            }
            // 当前节点与子节点交换
            ArrayCommonOperate.swap(ary, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }


    /**
     * 堆排序
     * 首先先将数组构造成大顶堆
     *   从0位置开始依次对元素进行上浮操作
     *
     */
    public void heapSort(int[] ary) {
        if (null == ary || ary.length < 2) {
            return;
        }
        // 依次进行上浮操作，完成大顶堆构造
        for (int i = 0; i < ary.length; i ++) {
            this.heapInsert(ary, i);
        }
        int heapSize = ary.length;
        // 将堆顶元素移动到数组尾部， 并将堆size -1;
        ArrayCommonOperate.swap(ary, 0, -- heapSize);
        while (heapSize > 0) {
            this.heapify(ary, 0, heapSize);
            // 堆底元素移动到堆顶 并通过下沉操作完成堆恢复
            ArrayCommonOperate.swap(ary, 0, -- heapSize);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,5,4,3,2};
        new Heap().heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
