package com.study.newcoder.lesson08;

public class NQueen {
    /**
     * i 代表处理到i行
     * record[i] 代表第i行的多少列放了皇后
     * n代表问题规模
     */
    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int result = 0;
        for (int j = 0; j < n; j ++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                result += process(i + 1, record, n);
            }
        }
        return result;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int m = 0; m < record.length; m ++) {
            if (j == record[m] || Math.abs(record[m] - j) == Math.abs(m - i)) {
                return false;
            }
        }
        return true;
    }
}
