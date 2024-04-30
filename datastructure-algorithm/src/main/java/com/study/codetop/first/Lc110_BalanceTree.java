package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * https://leetcode.cn/problems/balanced-binary-tree/
 *
 */
public class Lc110_BalanceTree {
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalance;
    }

    TreeInfo process(TreeNode node) {
        if (node == null) {
            return new TreeInfo(0, true);
        }
        TreeInfo left = process(node.left);
        TreeInfo right = process(node.right);
        boolean isBalance = (Math.abs(left.height - right.height) <= 1) && left.isBalance && right.isBalance;
        int height = Math.max(left.height, right.height) + 1;
        return new TreeInfo(height, isBalance);
    }

    class TreeInfo {
        int height;
        boolean isBalance;
        public TreeInfo(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }
}
