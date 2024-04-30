package com.study.codetop.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 */
public class Lc015_ThreeNumberSum {
    /**
     * 对数组排序，然后固定第一个数字，在剩余范围求two sum；过程中需要将重复数字跳过
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <  3) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = nums[i] + leftNum + rightNum;
                if (sum == 0) {
                    result.add(Arrays.asList(new Integer[]{nums[i], leftNum, rightNum}));
                    while (left < right && nums[left] == leftNum) {
                        left ++;
                    }
                    while (left < right && nums[right] == rightNum) {
                        right --;
                    }
                } else if (sum > 0) {
                    right --;
                    while (left < right && nums[right] == rightNum) {
                        right --;
                    }
                } else {
                    left ++;
                    while (left < right && nums[left] == leftNum) {
                        left ++;
                    }
                }
            }
        }
        return result;
    }
}
