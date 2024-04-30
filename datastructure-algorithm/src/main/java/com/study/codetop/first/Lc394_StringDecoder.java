package com.study.codetop.first;


import java.util.Stack;

/**
 *  给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc394_StringDecoder {

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<String> help = new Stack<>();

        for (int i = 0; i < s.length(); i ++) {
            String is = String.valueOf(s.charAt(i));
            if ("]".equals(is)) {
                StringBuilder str = new StringBuilder();
                String si = "";
                while (!(si = help.pop()).equals("[")) {
                    str.insert(0, si);
                }
                StringBuilder tmp = new StringBuilder();
                StringBuilder countStr = new StringBuilder();
                while (!help.isEmpty() && isDigital(help.peek())) {
                    countStr.insert(0, help.pop());
                }
                int count = Integer.parseInt(countStr.toString());
                while (count > 0) {
                    tmp.append(str.toString());
                    count --;
                }
                help.push(tmp.toString());
            } else {
                help.push(is);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!help.isEmpty()) {
            result.insert(0, help.pop());
        }
        return result.toString();
    }

    boolean isDigital(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
