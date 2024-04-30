package com.study.newcoder.review.lesson02;

import com.study.newcoder.lesson02.SmallSum;

/**
 * 无序数组中的小和问题
 */
public class SmallNumberPlus {
    public static int plusSmallNumber(int[] ary) {
        if (ary == null || ary.length < 2) {
            return 0;
        }
        return plusSmallNumber(ary, 0, ary.length - 1);
    }

    public static int plusSmallNumber(int[] ary, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;

        return plusSmallNumber(ary, left, mid)
                + plusSmallNumber(ary, mid + 1, right)
                + merge(ary, left, mid, right);

    }

    public static int merge(int[] ary, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int l = left, r = mid + 1, t = 0;
        int result = 0;
        while (l <= mid && r <= right) {
            result = ary[l] < ary[r] ? ((right - r + 1) * ary[l]) : result;
            tmp[t ++] = ary[l] < ary[r] ? ary[l ++] : ary[r ++];
        }
        while (l <= mid) {
            tmp[t ++] = ary[l ++];
        }
        while (r <= right) {
            tmp[t ++] = ary[r ++];
        }
        for (int i = 0; i < tmp.length; i ++) {
            ary[i + left] = tmp[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ary = {1,3,4,2,5};
        System.out.println(new SmallSum().calculate(ary));
    }
}
