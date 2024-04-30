package com.study.codetop.first;

import java.util.LinkedList;

/**
 *  给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc239_SlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int p = 0;
        LinkedList<Integer> doublyQueue = new LinkedList<>();
        for (int i = 0; i < nums.length; i ++) {
            int n = nums[i];
            // 双端队列单调性， 头结点是窗口最大元素
            while (!doublyQueue.isEmpty() && nums[doublyQueue.peekLast()] <= n) {
                doublyQueue.pollLast();
            }
            doublyQueue.addLast(i);
            // 判断窗口是否形成
            if ((i + 1) >= k) {
                // 判断过期
                if ((i - doublyQueue.peekFirst()) >= k) {
                    doublyQueue.pollFirst();
                }
                result[p ++] = nums[doublyQueue.peekFirst()];
            }
        }
        return result;
    }
}
