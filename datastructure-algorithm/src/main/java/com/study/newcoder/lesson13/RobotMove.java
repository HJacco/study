package com.study.newcoder.lesson13;

public class RobotMove {

    public static int move(int n, int target, int start, int steps) {
        if (n < 2 || start < 1 || target < 1 || target > n || steps > n || steps < n) {
            return 0;
        }
        return moveWays(n, target, start, steps);
    }

    /**
     * @param n 1~n位置
     * @param current 当前位置
     * @param restStep 剩余步数
     * @param target   目标位置
     */
    public static int moveWays(int n, int target, int current, int restStep) {
        if (restStep == 0) {
            return current == target ? 1 : 0;
        }
        if (current == 1) {
            return moveWays(n, target, 2, restStep - 1);
        }
        if (current == n) {
            return moveWays(n, target, n - 1, restStep - 1);
        }
        return moveWays(n, target, current - 1, restStep - 1) +
                moveWays(n, target, current + 1, restStep - 1);
    }

    public static int dpMoveWays(int n, int target, int current, int restStep) {
        if (n < 2 || current < 1 || target < 1 || target > n || restStep > n || restStep < n) {
            return 0;
        }
        int[][] dp = new int[current + 1][restStep + 1];
        dp[0][target] = 1;
        for (int i = 1; i <= current; i ++) {
            for (int j = 1; j <= restStep; j ++) {
                if (i == 1) {
                    dp[i][j] = dp[2][j - 1];
                } else if (i == n) {
                    dp[i][j] = dp[n - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
                }
            }
        }
        return dp[target][0];
    }
}
