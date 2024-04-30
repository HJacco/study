package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc662_TreeMaxWidth {

    public int widthOfBinaryTree(TreeNode root) {
        if (null == root ) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        Map<TreeNode, Integer> nodeLevelIndexMap = new HashMap<>();


        //每一层最左边node 位置
        Map<Integer, Integer> nodeLevelLeftIndexMap = new HashMap<>();
        //每一层最右边node 位置
        Map<Integer, Integer> nodeLevelRightIndexMap = new HashMap<>();

        q.offer(root);
        nodeLevelMap.put(root, 1);
        nodeLevelIndexMap.put(root, 1);
        nodeLevelLeftIndexMap.put(1, 1);
        nodeLevelRightIndexMap.put(1, 1);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int curLevel = nodeLevelMap.get(node);
            int curIndex = nodeLevelIndexMap.get(node);
            if (node.left != null) {
                nodeLevelMap.put(node.left, curLevel + 1);
                q.offer(node.left);
                nodeLevelIndexMap.put(node.left, 2 * curIndex - 1);
                if (!nodeLevelLeftIndexMap.containsKey(curLevel + 1)) {
                    nodeLevelLeftIndexMap.put(curLevel + 1, 2 * curIndex - 1);
                }
                nodeLevelRightIndexMap.put(curLevel + 1, 2 * curIndex - 1);
            }
            if (node.right != null) {
                nodeLevelMap.put(node.right, curLevel + 1);
                q.offer(node.right);
                nodeLevelIndexMap.put(node.right, 2 * curIndex);
                if (!nodeLevelLeftIndexMap.containsKey(curLevel + 1)) {
                    nodeLevelLeftIndexMap.put(curLevel + 1, 2 * curIndex);
                }
                nodeLevelRightIndexMap.put(curLevel + 1, 2 * curIndex);
            }
        }
        int maxWidth = Integer.MIN_VALUE;
        for (int l : nodeLevelLeftIndexMap.keySet()) {
            int left = nodeLevelLeftIndexMap.get(l);
            int right = nodeLevelRightIndexMap.get(l);
            maxWidth = Math.max(maxWidth, (right - left + 1));
        }
        return maxWidth;
    }
}
