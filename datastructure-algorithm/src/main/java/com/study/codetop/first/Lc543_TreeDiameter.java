package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class Lc543_TreeDiameter {

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDistance - 1;
    }


    public NodeInfo process(TreeNode head) {
        if (head == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo left = process(head.left);
        NodeInfo right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        // 当前树的最大距离是 左子树最大距离，右子树最大距离，左子树高度+右子树高度+1 三者较大者，而直径是最大距离-1
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height + 1);
        return new NodeInfo(height, maxDistance);
    }

    class NodeInfo {
        int height;
        int maxDistance;

        public NodeInfo(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
}
