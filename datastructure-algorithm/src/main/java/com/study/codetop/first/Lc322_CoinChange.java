package com.study.codetop.first;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc322_CoinChange {
    
    /**
     * dp
     * 当i >= coins[j]
     *  dp[i] = Math.min(dp[i-coins[0]],dp[i-coins[1]],dp[i-coins[2]]) + 1
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i ++) {
            dp[i] = max;
        }
        dp[0] = 0;
        for (int i = 1; i < dp.length; i ++) {
            for (int j = 0; j < coins.length; j ++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
