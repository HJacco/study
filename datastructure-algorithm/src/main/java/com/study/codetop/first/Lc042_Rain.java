package com.study.codetop.first;

import java.util.Stack;

/**
 *  给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *  LeetCode link : https://leetcode.cn/problems/trapping-rain-water/
 */
public class Lc042_Rain {
    public int trap(int[] height) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i ++) {

            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int bottom = stack.peek();
                int width = i - bottom - 1;
                int h = Math.min(height[stack.peek()], height[i]) - height[top];
                result += (width * h);
            }
            stack.push(i);
        }
        return result;
    }
}
