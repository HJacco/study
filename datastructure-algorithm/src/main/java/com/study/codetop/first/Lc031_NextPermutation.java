package com.study.codetop.first;

/**
 *  整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc031_NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return ;
        }
        int n = nums.length;
        // 从后向前找，找到第一个num[i] < num[i + 1]的  i
        int smallIndex = n - 2;
        while (smallIndex >= 0 && nums[smallIndex] >= nums[smallIndex + 1]) {
            smallIndex --;
        }
        if (smallIndex >= 0) {
            int bigIndex = n - 1;
            while (bigIndex >= 0 && nums[smallIndex] >= nums[bigIndex]) {
                bigIndex --;
            }
            // 交换 small 与 big
            int t = nums[smallIndex];
            nums[smallIndex] = nums[bigIndex];
            nums[bigIndex] = t;
        }
        // 对small 后面的元素翻转
        int left = smallIndex + 1, right = n - 1;
        while (left < right) {
            int tt = nums[left];
            nums[left] = nums[right];
            nums[right] = tt;
            left ++;
            right --;
        }
    }
}
