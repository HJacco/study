package com.study.codetop.first;

import java.util.List;

import java.util.ArrayList;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 *
 *      输入：nums = [1,2,3]
 *      输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *  leetcode link : https://leetcode.cn/problems/permutations/
 */
public class Lc046_AllPermute {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> permute(int[] nums) {
        process(nums, 0, result);
        return result;
    }

    private void process(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            result.add(convertToList(nums));
        }
        for (int i = start; i < nums.length; i ++) {
            swap(nums, start, i);
            process(nums, start + 1, result);
            swap(nums, i, start);
        }
    }

    private List<Integer> convertToList(int[] a) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.length; i ++) {
            result.add(a[i]);
        }
        return result;
    }
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}