package com.study.codetop.first;

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc695_IslandAreaMax {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, this.inject(grid, m, n, i, j));
                }
            }
        }
        return maxArea;
    }
    int inject(int[][] grid, int m, int n, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        res += inject(grid, m, n, i + 1, j);
        res += inject(grid, m, n, i - 1, j);
        res += inject(grid, m, n, i, j + 1);
        res += inject(grid, m, n, i, j - 1);
        return res;
    }
}
