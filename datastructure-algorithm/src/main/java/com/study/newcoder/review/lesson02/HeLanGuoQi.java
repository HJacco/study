package com.study.newcoder.review.lesson02;

import com.study.common.util.ArrayCommonOperate;

public class HeLanGuoQi {
    /**
     * 在left 到right范围上根据target对ary分区
     * 小于 等于  大于
     */
    public static void partition(int[] ary, int left, int right, int target) {
        int small = left - 1;
        int big = right + 1;
        while (left < big) {
            // 找到小于的元素， 则将当前元素与小于区域边界前一个元素交换，小于边界扩一，当前指针向前走一
            if (ary[left] < target) {
                ArrayCommonOperate.swap(ary, ++ small, left ++);
            // 找到大于的元素， 则将当前元素与大于区域边界前一个元素交换，大于边界扩一，当前指针不变
            } else if (ary[left] > target) {
                ArrayCommonOperate.swap(ary, -- big, left);
            } else {
                left ++;
            }
        }
    }
}
