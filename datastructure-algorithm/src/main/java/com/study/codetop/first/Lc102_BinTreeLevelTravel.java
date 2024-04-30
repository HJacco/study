package com.study.codetop.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.study.common.entity.TreeNode;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class Lc102_BinTreeLevelTravel {
    /**
     * 二叉树的层序遍历：基于队列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelMap.put(root, 1);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int level = levelMap.get(node);
            if (level == result.size()) {
                result.get(level - 1).add(node.val);
            } else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(node.val);
                result.add(tmp);
            }
            if (node.left != null) {
                queue.offer(node.left);
                levelMap.put(node.left, level + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                levelMap.put(node.right, level + 1);
            }
        }
        return result;
    }
}
