package com.study.newcoder.review.lesson01;

public class BinarySearch {
    /**
     * 二分查找数组中某个数的位置
     */
    public static int binarySearch(int[] ary, int target) {
        if (null == ary || ary.length == 0) {
            return -1;
        }
        int l = 0, r = ary.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (ary[mid] < target) {
                l = mid + 1;
            } else if (ary[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找数组中大于某个数最左侧数字的位置
     */
    public static int searchBigThanLeftIndex(int[] ary, int target) {
        if (null == ary || ary.length == 0) {
            return -1;
        }
        int left = 0;
        int right = ary.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ary[mid] >= target) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * 查找局部最小值
     */
    public static int searchPartMin(int[] ary) {
        if (ary == null || ary.length == 0) {
            return -1;
        }
        if (ary.length == 1) {
            return ary[0];
        }

        if (ary[0] < ary[1]) {
            return 0;
        }
        if (ary[ary.length - 1] < ary[ary.length - 2]) {
            return ary[ary.length - 1];
        }
        int left = 1;
        int right = ary.length - 2;

        // 搜索区间是左闭右开
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (ary[mid] > ary[mid - 1]) {
                right = mid - 1;
            } else if (ary[mid] > ary[mid + 1]) {
                left = mid + 1;
            } else  {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] a = {1,3,5,7,9,12};
        System.out.println(binarySearch(a, 7));

        int[] b = {1,2,3,4,5,7,8,8,8,8,8,10};
        System.out.println(searchBigThanLeftIndex(b, 6));

        int[] c = {5,4,3,2,3,6};
        System.out.println(searchPartMin(c));
    }
}
