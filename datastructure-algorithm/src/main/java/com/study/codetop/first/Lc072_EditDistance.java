package com.study.codetop.first;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc072_EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] minDistances = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i ++) {
            minDistances[i][0] = i;
        }
        for (int j = 1; j <= len2; j ++) {
            minDistances[0][j] = j;
        }
        for (int i = 1; i <= len1; i ++) {
            for (int j = 1; j <= len2; j ++) {
                int left = minDistances[i][j - 1] + 1;
                int right = minDistances[i - 1][j] + 1;
                int mid = minDistances[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    mid += 1;
                }
                minDistances[i][j] = Math.min(mid, Math.min(left, right));
            }
        }
        return minDistances[len1][len2];
    }
}
