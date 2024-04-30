package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *  给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc129_TreeNodeSum {
    List<Integer> result = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        process(root, root.val);
        int sum = 0;
        for (Integer i : result) {
            sum += i;
        }
        return sum;
    }
    void process(TreeNode node, int num) {
        if (node.left == null && node.right == null) {
            result.add(num);
            return;
        }
        if (node.left != null) {
            process(node.left, num * 10 + node.left.val);
        }
        if (node.right != null) {
            process(node.right, num * 10 + node.right.val);
        }
    }
}
