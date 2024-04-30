package com.study.newcoder.lesson01;

public class BinarySearch {
    public int search(int[] ary, int target) {
        if (ary.length == 0) {
            return -1;
        }
        int left = 0;
        int right = ary.length - 1;
        // while 条件决定搜索区间 [left, right]
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (ary[middle] == target) {
                return middle;
            }
            if (ary[middle] < target) {
                left = middle + 1;
            }
            if (ary[middle] > target) {
                right = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 搜索有序数组中大于某个数最左侧的数字的位置
     * 思路：每次二分，找到中间位置的数字，如果该数字比目标数字大，则记录该位置，并继续在左半边中查找比目标数字大的数字；直到不能二分
     */
    public int searchFirstNumberBigThanTargetNumber(int[] ary, int target) {
        int left = 0;
        int right = ary.length - 1;
        int index = -1;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (ary[middle] >= target) {
                index = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return index;
    }

    /**
     * 搜索无序数组中局部最小值（小于左右两边相邻的数字）
     * 思路：左边数字，向下递减，右边数字也是向下递减，则数组中一定存在某个拐点，
     *      每次二分，判断是左边向下，还是右边向下，左边向下，则查找右半边，右边向下则查找左半边，直到找到这个数字
     */
    public int searchPartialMin(int[] ary) {
        if (ary.length == 1) {
            return ary[0];
        }
        int left = 0;
        int right = ary.length - 1;

        if (ary[left] <= ary[left + 1]) {
            return ary[left];
        }
        if (ary[right] <= ary[right - 1]) {
            return ary[right];
        }

        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (ary[middle] <= ary[middle - 1] && ary[middle] <= ary[middle + 1]) {
                return ary[middle];
            } else if (ary[middle] >= ary[middle - 1]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ary = {1, 2, 3, 4, 4, 4, 5, 5, 5};
        System.out.println(new BinarySearch().search(ary, 1));
        System.out.println(new BinarySearch().searchFirstNumberBigThanTargetNumber(ary, 5));

        int[] orderAry = {1,2,3,4,5,6};
        int[] unOrderAry = {5,4,3,4,5,6};
        System.out.println(new BinarySearch().searchPartialMin(orderAry));
        System.out.println(new BinarySearch().searchPartialMin(unOrderAry));
    }
}
