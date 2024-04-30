package com.study.newcoder.review.lesson09;

public class Island {
    public int islandCount(int[][] sea) {
        if (sea == null || sea.length == 0 || sea[0].length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < sea.length; i ++) {
            for (int j = 0; j < sea[i].length; j ++) {
                if (sea[i][j] == 1) {
                    result ++;
                    inject(sea, i, j, sea.length, sea[i].length);
                }
            }
        }
        return result;
    }

    public void inject(int[][] sea, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m | j >= n || sea[i][j] != 1) {
            return;
        }
        sea[i][j] = 2;
        inject(sea, i + 1, j, m, n);
        inject(sea, i, j + 1, m, n);
        inject(sea, i - 1, j, m, n);
        inject(sea, i, j - 1, m, n);
    }
}
