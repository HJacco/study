package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 *  给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *  https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class Lc199_BinTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        Map<Integer, TreeNode> levelMap = new TreeMap<>();
        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        q.offer(root);
        levelMap.put(1, root);
        nodeLevelMap.put(root, 1);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int curLevel = nodeLevelMap.get(node);
            if (node.left != null) {
                levelMap.put(curLevel + 1, node.left);
                nodeLevelMap.put(node.left, curLevel + 1);
            }
            if (node.right != null) {
                levelMap.put(curLevel + 1, node.right);
                nodeLevelMap.put(node.right, curLevel + 1);
            }
        }

        for (Integer level : levelMap.keySet()) {
            result.add(levelMap.get(level).val);
        }
        return result;

    }
}
