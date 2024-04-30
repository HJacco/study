package com.study.codetop.first;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Lc003_LongestSubStringNoRepeat {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int max = Integer.MIN_VALUE;
        Map<Character, Integer> indexMap = new HashMap<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (indexMap.containsKey(ch)) {
                left = Math.max(left, indexMap.get(ch) + 1);
            }
            indexMap.put(ch, right);
            max = Math.max(right - left + 1, max);
            right ++;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new Lc003_LongestSubStringNoRepeat().lengthOfLongestSubstring(s));
    }
}