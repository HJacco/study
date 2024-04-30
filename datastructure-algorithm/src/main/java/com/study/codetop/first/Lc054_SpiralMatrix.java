package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class Lc054_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        // 边界
        if (null == matrix || matrix.length == 0) {
            return result;
        }
        int left = 0, right = matrix[0].length - 1, top = 0, down = matrix.length - 1;
        while (left <= right && top <= down) {
            // 遍历上边 left -> right
            for (int i = left; i <= right; i ++) {
                result.add(matrix[top][i]);
            }
            top ++;
            if (top > down) {
                break;
            }
            // 遍历右边 top -> down
            for (int i = top; i <= down; i ++) {
                result.add(matrix[i][right]);
            }
            right --;
            if (left > right) {
                break;
            }
            // 遍历下边 matrx[down][right -> left]
            for (int i = right; i >= left; i --) {
                result.add(matrix[down][i]);
            }
            down --;
            if (top > down) {
                break;
            }
            // 遍历左边 matrix[down -> top][left]
            for (int i = down; i >= top; i --) {
                result.add(matrix[i][left]);
            }
            left ++;
            if (left > right) {
                break;
            }
        }
        return result;
    }
}
