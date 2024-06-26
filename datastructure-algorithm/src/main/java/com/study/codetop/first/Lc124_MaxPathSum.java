package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 *  路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc124_MaxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        process(root);
        return max;
    }

    int process(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = Math.max(process(head.left), 0);
        int right = Math.max(process(head.right), 0);
        int sum = left + right + head.val;
        max = Math.max(max, sum);
        return Math.max(left, right) + head.val;
    }
}
