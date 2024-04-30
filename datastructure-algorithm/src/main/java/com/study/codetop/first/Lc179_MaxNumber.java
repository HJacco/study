package com.study.codetop.first;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc179_MaxNumber {
    public String largestNumber(int[] nums) {
        String[] numStrAry = new String[nums.length];
        for (int i = 0; i < numStrAry.length; i ++) {
            numStrAry[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrAry, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (numStrAry[0].equals("0")) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        for (String s : numStrAry) {
            result.append(s);
        }
        return result.toString();

    }
}
