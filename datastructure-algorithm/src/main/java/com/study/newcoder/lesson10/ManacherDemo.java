package com.study.newcoder.lesson10;

import java.util.ArrayList;
import java.util.List;

public class ManacherDemo {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(nums, 0, result);
        return result;
    }

    public void process(int[] nums, int i, List<List<Integer>> result) {
        if (i == nums.length) {
            result.add(convertToList(nums));
            return;
        }
        for (int j = i; j < nums.length; j ++) {
            swap(nums, i, j);
            process(nums, i + 1, result);
            swap(nums, i, j);
        }
    }
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public List<Integer> convertToList(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i ++) {
            result.add(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        new ManacherDemo().permute(new int[]{1,2,3});
    }
}
