package com.study.codetop.first;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 */
public class Lc001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            indexMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i ++) {
            if (indexMap.containsKey(target - nums[i]) && indexMap.get(target - nums[i]) != i) {
                return new int[]{i, indexMap.get(target- nums[i])};
            }
        }
        return new int[]{-1, -1};
    }
}