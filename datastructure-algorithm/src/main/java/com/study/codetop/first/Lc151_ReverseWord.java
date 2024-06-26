package com.study.codetop.first;

import java.util.Stack;

/**
 * 给你一个字符串 s ，颠倒字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc151_ReverseWord {
    /**
     * 思路：字符串从后往前依次压入栈中，遇到空格且栈不为空，出栈
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<Character> help = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = s.length() - 1; i >=0 ; i --) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                if (help.isEmpty()) {
                    continue;
                } else {
                    while (!help.isEmpty()) {
                        result.append(help.pop());
                    }
                    result.append(" ");
                }
            } else {
                help.push(ch);
            }
        }
        while (!help.isEmpty()) {
            result.append(help.pop());
        }
        if (result.charAt(result.length() - 1) == ' ') {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
