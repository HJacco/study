package com.study.codetop.first;

import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class Lc032_LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        // 栈底元素是最后扫描到的未能被匹配的有括号的下标
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(-1);
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                indexStack.push(i);
            } else {
                indexStack.pop();
                if (indexStack.empty()) {
                    indexStack.push(i);
                } else {
                    ans = Math.max(ans, i - indexStack.peek());
                }
            }
        }
        return ans;
    }
}
