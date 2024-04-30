package com.study.newcoder.lesson02;

/**
 * 小和问题：
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组 的小和。求一个数组 的小和。
 * 例子:[1,3,4,2,5] 1左边比1小的数，没有; 3左边比3小的数，1; 4左 边比4小的数，1、3; 2左边比2小的数，
 * 1; 5左边比5小的数，1、3、4、 2; 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 思路：
 *   转换思路：找比某个数字大的数字的个数，在归并的过程中可以算比某个数字大的个数
 */
public class SmallSum {

    public int calculate(int[] ary) {
        if (ary == null || ary.length < 2) {
            return 0;
        }
        return process(ary, 0, ary.length - 1);
    }

    public int process(int[] ary, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return process(ary, l, mid) + process(ary, mid + 1, r) + merge(ary, l, mid, r);
    }

    public int merge(int[] ary, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int res = 0;
        int p1 = l, p2 = mid + 1, p = 0;
        while (p1 <= mid && p2 <= r) {
            res += ary[p1] < ary[p2] ? (r - p2 + 1) * ary[p1] : 0;
            help[p ++] = ary[p1] < ary[p2] ? ary[p1 ++] : ary[p2 ++];
        }

        while (p2 <= r) {
            help[p ++] = ary[p2 ++];
        }
        while (p1 <= mid) {
            help[p++] = ary[p1 ++];
        }
        for (int i = 0; i < help.length; i ++) {
            ary[i + l] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ary = {1,3,4,2,5};
        System.out.println(new SmallSum().calculate(ary));
    }

}
