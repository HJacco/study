package com.study.codetop.first;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc062_RobotUniquePath {
    int pathCount = 0;
    public int uniquePaths(int m, int n) {
        step(m, n, 1, 1);
        return pathCount;
    }
    public void step(int m, int n, int i, int j) {
        if (i == m && j == n) {
            pathCount ++;
            return;
        }
        if (i < m) {
            step(m, n, i + 1, j);
        }
        if (j < n) {
            step(m, n, i, j + 1);
        }
    }

    public int dp(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i ++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= n; i ++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= m; i ++) {
            for (int j = 2; j <= n; j ++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Lc062_RobotUniquePath().uniquePaths(3, 1));
    }

}
