package com.study.codetop.first;

import java.util.PriorityQueue;

/**
 * 数组中第K大的元素
 * 思路：构造小顶堆，堆顶元素小于元素，则弹出堆顶，把当前元素加进去
 */
public class Lc215_KthBig {
    /**
     *  思路：构造小顶堆，堆顶元素小于元素，则弹出堆顶，把当前元素加进去
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i ++) {
            if (queue.size() == k) {
                if (queue.peek() < nums[i]) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            } else {
                queue.offer(nums[i]);
            }

        }
        return queue.peek();
    }


    /**
     * 将nums转换成大顶堆， 弹出k次堆顶
     */
    public int findKthLargestWithHeap(int[] nums, int k) {
        // 构造大顶堆
        for (int i = 0; i < nums.length; i ++) {
            this.insertHeap(nums, i);
        }

        int count = 1;
        int heapSize = nums.length;
        while (true) {
            if (count == k) {
                return nums[0];
            }
            swap(nums, 0, heapSize - 1);
            this.heapify(nums, 0, -- heapSize);
            count ++;
        }
    }
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 9  0  2  3  5
     * 0  1  2  3  4
     */
    public void insertHeap(int[] nums, int index) {
        int parent = (index - 1) / 2;
        while (index > parent) {
            if (nums[index] > nums[parent]) {
                int t = nums[index];
                nums[index] = nums[parent];
                nums[parent] = t;
            }
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void heapify(int[] nums, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largestIndex = left + 1 < heapSize && nums[left + 1] > nums[left] ? left + 1 : left;
            if (nums[index] < nums[largestIndex]) {
                int t = nums[largestIndex];
                nums[largestIndex] = nums[index];
                nums[index] = t;
            }
            index = largestIndex;
            left = 2 * index + 1;
        }
    }
}
