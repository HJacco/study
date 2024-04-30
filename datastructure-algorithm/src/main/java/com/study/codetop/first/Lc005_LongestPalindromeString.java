package com.study.codetop.first;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 *
 *  输入：s = "babad"
 *  输出："bab"
 *  解释："aba" 同样是符合题意的答案。
 * https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class Lc005_LongestPalindromeString {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i ++) {
            int l1 = expand(s, i, i);
            int l2 = expand(s, i, i + 1);
            int l = Math.max(l1, l2);
            if (l > (end - start)) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i --;
            j ++;
        }
        return j - i - 1;
    }
}
