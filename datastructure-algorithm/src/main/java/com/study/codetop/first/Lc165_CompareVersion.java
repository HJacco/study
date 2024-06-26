package com.study.codetop.first;

/**
 *  给你两个版本号 version1 和 version2 ，请你比较它们。
 *
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。
 * 修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 *
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。
 * 如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 *
 * 返回规则如下：
 *
 * 如果 version1 > version2 返回 1，
 * 如果 version1 < version2 返回 -1，
 * 除此之外返回 0。
 *  
 *
 * 示例 1：
 *
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc165_CompareVersion {

    public int compareVersion(String version1, String version2) {
        String[] versionFields = version1.split("\\.");
        String[] version2Fields = version2.split("\\.");

        int[] versionIntFields = new int[versionFields.length];
        int[] version2IntFields = new int[version2Fields.length];
        for (int i = 0; i < versionFields.length; i ++) {
            versionIntFields[i] = Integer.parseInt(versionFields[i]);
        }
        for (int i = 0; i < version2Fields.length; i ++) {
            version2IntFields[i] = Integer.parseInt(version2Fields[i]);
        }
        int longVersionLen = version2IntFields.length > versionIntFields.length ? version2IntFields.length : versionIntFields.length;

        int[] minus = new int[longVersionLen];
        for (int i = 0; i < longVersionLen; i ++) {
            int v1 = 0, v2 = 0;
            if (i < versionIntFields.length) {
                v1 = versionIntFields[i];
            }
            if (i < version2IntFields.length) {
                v2 = version2IntFields[i];
            }
            minus[i] = v1 - v2;
        }
        for (int i = 0; i < minus.length; i ++) {
            if (minus[i] < 0) {
                return -1;
            } else  if (minus[i] > 0) {
                return 1;
            }
        }
        return 0;
    }
}
