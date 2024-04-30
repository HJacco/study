package com.study.codetop.first;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc415_StringNumAdd {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return num1 == null || num1.length() == 0 ? num2 : num1;
        }
        int mod = 0;
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 && j >= 0) {
            int n1 = num1.charAt(i) - '0';
            int n2 = num2.charAt(j) - '0';
            int n = n1 + n2 + mod;
            result.insert(0, (n % 10));
            mod = n / 10;
            i --;
            j --;
        }
        int z = i >= 0 ? i : j;
        num1 = i >= 0 ? num1 : num2;
        while (z >= 0) {
            int n1 = num1.charAt(z) - '0';
            int n = n1 + mod;
            result.insert(0, (n % 10));
            mod = n / 10;
            z --;
        }
        if (mod > 0) {
            result.insert(0, mod);
        }
        return result.toString();
    }
}
