package com.study.codetop.first;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 */
public class Lc020_ValidParentheses {

    /**
     * 遇到左括号压栈，遇到有括号弹栈，并且判断弹栈字符是否与有括号匹配
     */
    public boolean isValid(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }
        Map<Character, Character> parenthesesMap = new HashMap<>();
        parenthesesMap.put('}', '{');
        parenthesesMap.put(']', '[');
        parenthesesMap.put(')', '(');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (parenthesesMap.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != parenthesesMap.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
