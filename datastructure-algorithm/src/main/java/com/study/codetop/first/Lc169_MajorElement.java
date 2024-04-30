package com.study.codetop.first;

/**
 *  给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc169_MajorElement {
    public int majorityElement(int[] nums) {
        int vote = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (candidate == nums[i]) {
                vote ++;
            } else {
                vote --;
                if (vote == 0) {
                    candidate = nums[i];
                    vote = 1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (candidate == nums[i]) {
                count ++;
            }
        }
        return count > nums.length / 2 ? candidate : -1;
    }
}
