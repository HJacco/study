package com.study.codetop.first;

/**
 *  在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 *  https://leetcode.cn/problems/maximal-square/
 */
public class Lc221_MaxSquare {

    public int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j] 表示以matrix[i][j]为右下角的正方形最大边长
        int maxSide = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
