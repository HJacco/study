package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

/**
 *  给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 *  说明：每次只能向下或者向右移动一步。
 *
 *  https://leetcode.cn/problems/minimum-path-sum/
 */
public class Lc064_MinPathSum {

    Integer steps = Integer.MAX_VALUE;

    public int minPathSum(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        dfs(grid, 0, 0, M, N, new ArrayList<>());
        return steps;
    }

    public int dp(int[][] grid, int M, int N) {
        int[][] dp = new int[M][N];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < M; i ++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i ++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < M; i ++) {
            for (int j = 1; j < N; j ++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return grid[M - 1][N - 1];
    }

   /**
    * dfs 解法会超时
    */
    public void dfs(int[][] grid, int x, int y, int M, int N, List<Integer> result) {
        if (x == (M - 1) && y == (N - 1)) {
            result.add(grid[x][y]);
            steps = Math.min(steps, sum(result));
            return;
        }
        if (x < M) {
            List<Integer> xResult = new ArrayList<>(result);
            xResult.add(grid[x][y]);
            dfs(grid, x + 1, y, M, N, xResult);
        }
        if (y < N) {
            List<Integer> yResult = new ArrayList<>(result);
            yResult.add(grid[x][y]);
            dfs(grid, x, y + 1, M, N, yResult);
        }
    }
    int sum(List<Integer> ns) {
        int s = 0;
        for (Integer n : ns) {
            s += n;
        }
        return s;
    }
}
