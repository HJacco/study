package com.study.codetop.first;

/**
 *  给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 子数组 是数组的连续子序列。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc152_MultiMaxSubArray {
    public static void main(String[] args) {
        new Lc152_MultiMaxSubArray().maxProduct(new int[]{2,-5,-2,-4,3});
    }
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int maxV = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(nums[i], nums[i] * min[i - 1]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(nums[i], nums[i] * max[i - 1]));
            maxV = Math.max(max[i], maxV);
        }
        return maxV;
    }
}
