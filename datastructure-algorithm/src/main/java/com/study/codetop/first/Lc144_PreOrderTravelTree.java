package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 *  给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *  https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class Lc144_PreOrderTravelTree {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (null == root) {
            return result;
        }
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }
}
