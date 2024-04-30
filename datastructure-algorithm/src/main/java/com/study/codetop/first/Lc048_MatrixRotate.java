package com.study.codetop.first;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc048_MatrixRotate {

    public void rotate(int[][] matrix) {
        // 水平翻转
        for (int i = 0; i < matrix.length / 2; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = t;
            }
        }
        // 对角线翻转
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < i; j ++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
