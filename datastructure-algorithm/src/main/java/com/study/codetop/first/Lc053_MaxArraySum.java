package com.study.codetop.first;

/**
 *  给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class Lc053_MaxArraySum {
    /**
     *  思路：dp
     *  int[] dp = new int[nums.length - 1]
     *  dp[i] 代表以num[i]结尾的子数组的最大和
     *  如果以i前面一个元素结尾的子数组的最大和小于0， 则i位置的最大和就是当前元素
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (Integer i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }
}
