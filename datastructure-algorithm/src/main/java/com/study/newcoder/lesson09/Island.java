package com.study.newcoder.lesson09;

public class Island {
    public static int findIsland(int[][] ary) {
        if (ary == null || ary.length == 0) {
            return 0;
        }
        int res = 0;
        int m = ary.length;
        for (int i = 0; i < ary.length; i ++) {
            for (int j = 0; j < ary[i].length; j ++) {
                if (ary[i][j] == 1) {
                    res ++;
                    inject(ary, i, j, m, ary[i].length);
                }
            }
        }
        return res;
    }

    public static void inject(int[][] ary, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || ary[i][j] != 1) {
            return;
        }
        ary[i][j] = 2;
        inject(ary, i - 1, j, m, n);
        inject(ary, i + 1, j, m, n);
        inject(ary, i, j + 1, m, n);
        inject(ary, i, j - 1, m, n);
    }
}
