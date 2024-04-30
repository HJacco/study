package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

/**
 *  有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc093_RestoreIp {
    public List<String> restoreIpAddresses(String s) {

        char[] chs = s.toCharArray();
        char[] resAry = new char[chs.length + 3];
        process(chs, 0, 3, resAry, 0);

        return result;
    }

    List<String> result = new ArrayList<String>();
    private void process(char[] chs, int index, int dotNums, char[] resAry, int resIndex) {

        if (dotNums == 0 && index == chs.length) {
            if (isValid(resAry)) {
                result.add(String.valueOf(resAry));
            }
            return;
        }
        if (index < chs.length) {
            resAry[resIndex] = chs[index];
            process(chs, index+1, dotNums, resAry, resIndex+1);
        }
        if (dotNums > 0) {
            resAry[resIndex] = '.';
            process(chs, index, dotNums-1, resAry, resIndex+1);
        }

    }
    private boolean isValid(char[] chs) {
        String s = String.valueOf(chs);
        String[] segments = s.split("\\.");
        if (segments.length != 4) {
            return false;
        }
        for(String seg : segments) {
            if (seg.length() == 0) {
                return false;
            } else {
                Long num = Long.parseLong(seg);
                if (seg.length() > 1 && seg.startsWith("0")) {
                    return false;
                }
                if (num >=0 && num<=255) {
                    continue;
                } else {
                    return false;
                }

            }
        }
        return true;
    }
}
