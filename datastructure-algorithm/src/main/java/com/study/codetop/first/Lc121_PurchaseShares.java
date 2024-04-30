package com.study.codetop.first;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc121_PurchaseShares {
    /**
     * dp
     * int[] dp = new int[prices.length]
     * dp[i] 代表前i天最第低价格
     * 第i天卖出的最大利润是当天价格减去前面最低价格
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp[i] 代表前i天最低价格
        int[] dp = new int[prices.length];
        dp[0] = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i ++) {
            dp[i] = Math.min(dp[i - 1], prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - dp[i]);
        }
        return maxProfit;
    }
}
