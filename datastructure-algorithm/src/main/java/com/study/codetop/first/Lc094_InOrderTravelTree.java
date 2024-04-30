package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *  给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *  https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class Lc094_InOrderTravelTree {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        process(root, result);
        return result;
    }

    void process(TreeNode head, List<Integer> result) {
        if (null == head) {
            return;
        }
        process(head.left, result);
        result.add(head.val);
        process(head.right, result);
    }
}
