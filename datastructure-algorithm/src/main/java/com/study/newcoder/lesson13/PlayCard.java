package com.study.newcoder.lesson13;

public class PlayCard {
    /**
     * 先手函数
     * 在i 到 j 范围上取纸牌
     */
    public static int f(int[] cards, int i, int j) {
        if (i == j) {
            return cards[i];
        }
        return Math.max(cards[i] + s(cards, i + 1, j), cards[j] + s(cards, i, j - 1));
    }

    public static int s(int[] cards, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(cards, i + 1, j), f(cards, i, j - 1));
    }

    public static int win(int[] cards) {
        if (null == cards || cards.length == 0) {
            return 0;
        }
        return Math.max(f(cards, 0, cards.length - 1), s(cards, 0, cards.length - 1));
    }


    public static int dp(int[] cards) {
        if (null == cards || cards.length == 0) {
            return 0;
        }
        int[][] f = new int[cards.length][cards.length];
        int[][] s = new int[cards.length][cards.length];
        for (int i = 0; i < f.length; i ++) {
            f[i][i] = cards[i];
            for (int j = i - 1; j >= 0; j --) {
                f[j][i] = Math.max(cards[j] + s[i + 1][j], cards[i] + s[j][i - 1]);
                s[i][j] = Math.min(f[j + 1][i], f[j][i - 1]);
            }
        }
        return Math.max(f[0][cards.length - 1], s[0][cards.length - 1]);
    }
}
