package com.study.codetop.first;

/**
 * 数组排序
 */
public class Lc912_ArraySort {
    public int[] sortArray(int[] nums) {
        this.quickSort(nums, 0, nums.length - 1);
        this.mergeSort(nums, 0, nums.length - 1);
        this.heapSort(nums);
        return nums;
    }

    /**
     * 快速排序，对数组ary 在left, right范围上做排序
     */
    public void quickSort(int[] ary, int left, int right) {
        if (ary == null || ary.length < 2 || left >= right) {
            return ;
        }
        int pivot = ary[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && ary[j] >= pivot) {
                j --;
            }
            while (i < j && ary[i] <= pivot) {
                i ++;
            }
            if (i != j) {
                int t = ary[i];
                ary[i] = ary[j];
                ary[j] = t;
            }
        }
        ary[left] = ary[i];
        ary[i] = pivot;
        quickSort(ary, left, i);
        quickSort(ary, i + 1, right);
    }

    /**
     * 归并排序，对数组ary在left, right 范围上做排序
     */
    public void mergeSort(int[] ary, int left, int right) {
        if (ary == null || ary.length < 2 || left >= right) {
            return ;
        }
        // 分治 排序
        int mid = left + (right - left) / 2;
        mergeSort(ary, left, mid);
        mergeSort(ary, mid + 1, right);
        // 合并
        merge(ary, left, mid, right);
    }

    public void merge(int[] ary, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int p = 0;
        while (p1 <= mid &&  p2 <= right) {
            help[p ++] = ary[p1] < ary[p2] ? ary[p1 ++] : ary[p2 ++];
        }
        while (p1 <= mid) {
            help[p ++] = ary[p1 ++];
        }
        while (p2 <= right) {
            help[p ++] = ary[p2 ++];
        }
        for (int i = 0; i < help.length; i ++) {
            ary[i + left] = help[i];
        }
    }


    /**
     * 堆排序，对数组ary在left, right 范围上做排序
     */
    public void heapSort(int[] ary) {
        // 首先将数组做堆调整
        for (int i = 0; i < ary.length; i ++) {
            this.insertHeap(ary, i);
        }
        // 将堆顶元素与堆最后的元素交换，堆大小减一， 再次调整堆
        int heapSize = ary.length;
        // 与堆尾元素交换，并将堆大小减一
        this.swap(ary, 0, -- heapSize);
        while (heapSize > 0) {
            this.heapify(ary, 0, heapSize);
            this.swap(ary, 0, -- heapSize);
        }
    }

    public void insertHeap(int[] ary, int index) {
        while (ary[index] > ary[(index - 1) / 2]) {
            this.swap(ary, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int[] ary, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            // 找出Index的较大的子节点
            int largest = left + 1 < heapSize && ary[left] < ary[left + 1] ? left + 1 : left;
            // 比较较大子节点与当前节点的大小， 当前节点较小：则交换
            if (ary[index] < ary[largest]) {
                swap(ary, index, largest);
            }
            index = largest;
            left = 2 * index + 1;
        }
    }

    public void swap(int[] ary, int i, int j) {
        int t = ary[i];
        ary[i] = ary[j];
        ary[j] = t;
    }
}
