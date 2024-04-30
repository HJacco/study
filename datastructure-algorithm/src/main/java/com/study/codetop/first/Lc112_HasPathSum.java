package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc112_HasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        return process(root, targetSum);
    }
    boolean process(TreeNode root, int targetSum) {
        if (null == root) {
            if (0 == targetSum) {
                return true;
            } else {
                return false;
            }
        }
        if (root.left == null && root.right == null) {
            return process(null, targetSum - root.val);
        }
        boolean leftFlag = false, rightFlag = false;
        if (root.left != null) {
            leftFlag = process(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            rightFlag = process(root.right, targetSum - root.val);
        }
        return leftFlag || rightFlag;
    }
}
