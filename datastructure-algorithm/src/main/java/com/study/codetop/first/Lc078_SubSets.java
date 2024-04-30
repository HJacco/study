package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc078_SubSets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(new ArrayList<>(), nums, 0);
        return result;
    }

    void dfs(List<Integer> path, int[] nums, int index) {
        if (index == nums.length) {
            result.add(path);
            return;
        }
        List<Integer> noAddPath = new ArrayList<>(path);
        dfs(noAddPath, nums, index + 1);

        List<Integer> addPath = new ArrayList<>(path);
        addPath.add(nums[index]);
        dfs(addPath, nums, index + 1);
    }
}
