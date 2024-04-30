package com.study.codetop.first;


import java.util.Stack;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 *  
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc042_RemoveKthDigits {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        char[] ary = num.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < ary.length; i ++) {
            while (!stack.isEmpty() && stack.peek() > ary[i] && k > 0) {
                stack.pop();
                k --;
            }
            if (stack.isEmpty() && ary[i] == '0') {
                continue;
            }
            stack.push(ary[i]);
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k --;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.length() > 0 ? result.toString() : "0";
    }
}
