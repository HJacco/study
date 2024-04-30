package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 *  给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *  https://leetcode.cn/problems/symmetric-tree/
 */
public class Lc101_SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return process(root.left, root.right);
    }

    public boolean process(TreeNode left, TreeNode right) {
        if (left != null && right == null) {
            return false;
        }
        if (right != null && left == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        if (left.val != right.val) {
            return false;
        }
        return process(left.left, right.right) && process(left.right, right.left);
    }
}

