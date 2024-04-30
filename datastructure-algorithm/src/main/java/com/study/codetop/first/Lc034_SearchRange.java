package com.study.codetop.first;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc034_SearchRange {
    /**
     * 思路 ：分别用二分查找某个数字最左边出现的位置，和最右边出现的位置
     */
    public int[] searchRange(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }

        return new int[]{searchMostLeft(nums, target), searchMostRight(nums, target)};
    }

    public int searchMostLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1, mostLeft = Integer.MAX_VALUE;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                mostLeft = Math.min(mostLeft, mid);
                right = mid - 1;
            }
        }
        return mostLeft == Integer.MAX_VALUE ? -1 : mostLeft;
    }
    public int searchMostRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1, mostRight = Integer.MIN_VALUE;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                mostRight = Math.max(mostRight, mid);
                left = mid + 1;
            }
        }
        return mostRight == Integer.MIN_VALUE ? -1 : mostRight;
    }

    public static void main(String[] args) {
        int[] a = new int[]{5,7,7,8,8,10};
        new Lc034_SearchRange().searchRange(a, 8);
    }
}
