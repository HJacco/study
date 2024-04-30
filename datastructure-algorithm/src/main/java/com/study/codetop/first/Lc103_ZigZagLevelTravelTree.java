package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * url ：https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 */
public class Lc103_ZigZagLevelTravelTree {

    /**
     * 两个栈来交替控制层序遍历顺序
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<TreeNode> leftToRight = new Stack<>();
        Stack<TreeNode> rightToLeft = new Stack<>();
        leftToRight.push(root);
        while (!leftToRight.isEmpty() || !rightToLeft.isEmpty()) {
            List<Integer> leftToRightTravel = new ArrayList<>();
            while (!leftToRight.isEmpty()) {
                TreeNode node = leftToRight.pop();
                leftToRightTravel.add(node.val);
                if (node.left != null) {
                    rightToLeft.push(node.left);
                }
                if (node.right != null) {
                    rightToLeft.push(node.right);
                }
            }
            if (leftToRightTravel.size() > 0) {
                result.add(leftToRightTravel);
            }

            List<Integer> rightToLeftTravel = new ArrayList<>();
            while (!rightToLeft.isEmpty()) {
                TreeNode node = rightToLeft.pop();
                rightToLeftTravel.add(node.val);
                if (node.right != null) {
                    leftToRight.push(node.right);
                }
                if (node.left != null) {
                    leftToRight.push(node.left);
                }
            }
            if (rightToLeftTravel.size() > 0) {
                result.add(rightToLeftTravel);
            }
        }
        return result;
    }
}
