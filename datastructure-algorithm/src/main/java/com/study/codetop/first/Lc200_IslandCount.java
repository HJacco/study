package com.study.codetop.first;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc200_IslandCount {

    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        int R = grid.length;
        int C = grid[0].length;
        int count = 0;
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j ++) {
                if (grid[i][j] == '1') {
                    count ++;
                    inject(grid, R, C, i, j);
                }
            }
        }
        return count;
    }

    public void inject(char[][] grid, int R, int C, int i, int j) {
        if (i < 0 || j < 0 || i >= R || j >= C || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        inject(grid, R, C, i + 1, j);
        inject(grid, R, C, i - 1, j);
        inject(grid, R, C, i, j + 1);
        inject(grid, R, C, i, j - 1);
    }
}
