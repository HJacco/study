package com.study.newcoder.lesson13;

public class MinCoins {

    public static int process(int[] coins, int i, int rest) {
        if (i == coins.length) {
            return rest == 0 ? 0 : -1;
        }
        int result = -1;
        for (int k = 0; k * coins[i] <= rest; k ++) {
            int next = process(coins, i + 1, rest - k * coins[i]);
            if (next != -1) {
                result = result == -1 ? next + k : Math.min(result, next + k);
            }
        }
        return result;
    }
}
