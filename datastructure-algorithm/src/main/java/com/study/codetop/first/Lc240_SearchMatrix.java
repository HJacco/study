package com.study.codetop.first;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc240_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 第一列中找出小于等于target的最大值所在的行
        int index = this.findBigestRowIndex(matrix, target);
        if (index == -1) {
            return false;
        }
        for (int i = 0; i <= index; i ++) {
            int idx = this.binsearch(matrix[i], target);
            if (idx != -1) {
                return true;
            }
        }
        return false;

    }

    int binsearch(int[] ary, int target) {
        int left = 0;
        int right = ary.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (ary[mid] == target) {
                return mid;
            } else if (ary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return -1;
    }

    int findBigestRowIndex(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        if (matrix[0][0] > target) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        int bigest = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][0] < target) {
                left  = mid + 1;
                bigest = Math.max(bigest, mid);
            } else {
                right = mid - 1;
            }
        }
        return bigest;
    }
}
