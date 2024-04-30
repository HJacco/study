package com.study.codetop.first;

import java.util.LinkedList;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 *
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3+2*2"
 * 输出：7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc227_Calculator {

    public static void main(String[] args) {
        System.out.println(calculate("2+3+4"));
    }
    public static int calculate(String s) {
        s = s.replace(" ", "");
        LinkedList<Character> operator = new LinkedList<>();
        LinkedList<Integer> numbers = new LinkedList<>();
        if (s.charAt(0) == '-') {
            numbers.addLast(0);
        }
        int index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + (s.charAt(index) - '0');
                    index ++;
                }
                numbers.addLast(num);
            } else if (s.charAt(index) == '+' || s.charAt(index) == '-') {
                operator.addLast(s.charAt(index));
                index ++;
            } else if (s.charAt(index) == '*' || s.charAt(index) == '/') {
                char op = s.charAt(index);
                index = index + 1;
                int two = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    two = two * 10 + (s.charAt(index) - '0');
                    index ++;
                }
                int one = numbers.pollLast();
                if (op == '*') {
                    numbers.addLast(one * two);
                } else {
                    numbers.addLast(one / two);
                }
            }
        }
        int result = numbers.pollFirst();
        while (!operator.isEmpty()) {
            char op = operator.pollFirst();
            int n = numbers.pollFirst();
            if (op == '+') {
                result += n;
            } else {
                result -= n;
            }
        }
        return result;
    }
}
