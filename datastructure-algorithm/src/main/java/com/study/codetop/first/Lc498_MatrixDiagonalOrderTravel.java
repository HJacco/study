package com.study.codetop.first;

/**
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * https://leetcode.cn/problems/diagonal-traverse/
 */
public class Lc498_MatrixDiagonalOrderTravel {
    public int[] findDiagonalOrder(int[][] mat) {
        // y
        int m = mat.length;
        // x
        int n = mat[0].length;
        int[] rs = new int[m * n];
        int p = 0;
        for (int i = 0; i < m + n  - 1; i ++) {
            if (i % 2 == 1) {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    rs[p ++] = mat[x ++][y --];
                }
            } else {  // 右到左，上到下
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    rs[p ++] = mat[x --][y ++];
                }
            }
        }
        return rs;
    }
}
