package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc043_StringMulti {
    public static void main(String[] args) {
        String n1 = "2";
        String n2 = "3";
        new Lc043_StringMulti().multiply(n1, n2);
    }
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        List<String> subNumbers = new ArrayList<>();
        for (int j = num2.length() - 1; j >= 0; j --) {
            String n = appendZero(this.multiSingleNumber(num1, String.valueOf(num2.charAt(j))), num2.length() - j - 1);
            subNumbers.add(n);
        }
        String result = "0";
        for (String n : subNumbers) {
            result = this.add(n, result);
        }
        return result;
    }

    public String appendZero(String s, int n) {
        StringBuilder sBuilder = new StringBuilder(s);
        while (n > 0) {
            sBuilder.append("0");
            n --;
        }
        s = sBuilder.toString();
        return s;
    }

    public String multiSingleNumber(String num1, String singleNumber) {
        if ("0".equals(num1) || "0".equals(singleNumber)) {
            return "0";
        }
        int mod = 0;
        int single = singleNumber.charAt(0) - '0';
        StringBuilder result = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i --) {
            int en = num1.charAt(i) - '0';
            int sum = en * single + mod;
            result.insert(0, sum % 10);
            mod = sum / 10;
        }
        if (mod != 0) {
            result.insert(0, mod);
        }
        return result.toString();
    }

    public String add(String n1, String n2) {
        if ("0".equals(n1) || "0".equals(n2)) {
            return "0".equals(n1) ? n2 : n1;
        }
        int i = n1.length() - 1;
        int j = n2.length() - 1;
        StringBuilder result = new StringBuilder();
        int mod = 0;
        while (i >=0 && j >= 0) {
            int sum = (n1.charAt(i) - '0') + (n2.charAt(j) - '0') + mod;
            result.insert(0, sum % 10);
            mod = sum / 10;
            i--;
            j --;
        }
        while (i >= 0) {
            int sum = (n1.charAt(i) - '0') + mod;
            result.insert(0, sum % 10);
            mod = sum / 10;
            i --;
        }
        while (j >= 0) {
            int sum = (n2.charAt(j) - '0') + mod;
            result.insert(0, sum % 10);
            mod = sum / 10;
            j --;
        }
        if (mod > 0) {
            result.insert(0, mod);
        }
        return result.toString();
    }
}
