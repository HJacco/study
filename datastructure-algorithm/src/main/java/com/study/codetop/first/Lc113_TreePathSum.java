package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc113_TreePathSum {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return result;
        }
        List<Integer> r = new ArrayList<>();
        r.add(root.val);
        process(root, targetSum, r);
        return result;
    }
    void process(TreeNode head, int target, List<Integer> path) {
        // 到达叶子节点
        if (head.left == null && head.right == null) {
            if (check(target, path)) {
                result.add(path);
            }
        }
        if (head.left != null) {
            List<Integer> leftPath = new ArrayList<>(path);
            leftPath.add(head.left.val);
            process(head.left, target, leftPath);
        }
        if (head.right != null) {
            List<Integer> rightPath = new ArrayList<>(path);
            rightPath.add(head.right.val);
            process(head.right, target, rightPath);
        }
    }

    boolean check(int target, List<Integer> path) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum == target;
    }

}
