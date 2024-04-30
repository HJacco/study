package com.study.codetop.first;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *  
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc076_ShortestCoverString {
    public String minWindow(String s, String t) {
        Map<Character, Integer> sCharCount = new HashMap<>();
        Map<Character, Integer> tCharCount = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            tCharCount.put(t.charAt(i), tCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }

        // count 代表窗口中已经涵盖的t的字符数量
        int len = Integer.MAX_VALUE, count = 0;
        String ans = "";
        for (int i = 0, j = 0; i < s.length(); i ++) {
            sCharCount.put(s.charAt(i), sCharCount.getOrDefault(s.charAt(i), 0) + 1);

            // 窗口中每扫描到一个t的字符，count ++
            if (tCharCount.containsKey(s.charAt(i)) && sCharCount.get(s.charAt(i)) <= tCharCount.get(s.charAt(i))) {
                count ++;
            }

            // 判断是否满足窗口收缩条件
            while (j < i && (!tCharCount.containsKey(s.charAt(j)) || sCharCount.get(s.charAt(j)) > tCharCount.get(s.charAt(j)))) {
                int c = sCharCount.get(s.charAt(j)) - 1;
                sCharCount.put(s.charAt(j), c);
                j ++;
            }

            if (count == t.length() && len > (i - j + 1)) {
                len = i - j + 1;
                ans = s.substring(j, i + 1);
            }
        }
        return ans;
    }
}
