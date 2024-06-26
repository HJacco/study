package com.study.codetop.first;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc056_MergeRange {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i ++) {
            if (result.size() == 0) {
                result.add(intervals[i]);
            } else  {
                int lastIndex = result.size() - 1;
                int[] lastAry = result.get(lastIndex);
                if (lastAry[1] >= intervals[i][0]) {
                    result.set(lastIndex, new int[]{lastAry[0], Math.max(intervals[i][1], lastAry[1])});
                } else {
                    result.add(intervals[i]);
                }
            }
        }
        int[][] fResult = new int[result.size()][2];
        return result.toArray(fResult);
    }
}
