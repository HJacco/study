package com.study.codetop.first;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc014_LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = new String[]{"a", "b"};
        System.out.println(new Lc014_LongestCommonPrefix().longestCommonPrefix(strs));
    }
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        String str = strs[0];
        for (int i = 1; i < strs.length; i ++) {
            str = this.longestCommonPrefix(str, strs[i]);
        }
        return str;
    }

    public String longestCommonPrefix(String s1, String s2) {

        int len = Math.min(s1.length(), s2.length());
        if (len == 0) {
            return "";
        }
        for (int i = 0; i <= len - 1; i ++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (i == 0) {
                    return "";
                }
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, len);
    }
}
