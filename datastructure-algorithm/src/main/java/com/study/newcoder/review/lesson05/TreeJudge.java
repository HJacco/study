package com.study.newcoder.review.lesson05;

import com.study.common.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class TreeJudge {

    /**
     * 判断是否是搜索二叉树
     * 思路：中序遍历，遍历结果呈现递增
     */
    public static boolean isSBT(TreeNode head) {
        if (null == head) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> travelValues = new ArrayList<>();

        TreeNode current = head;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                travelValues.add(current.val);
                stack.push(current.right);
            }
        }


        for (int i = 0; i < travelValues.size() - 1; i ++) {
            if (travelValues.get(i) > travelValues.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是完全二叉树
     * 思路：
     *      广度优先遍历，遇到叶子节点，后面全是叶子节点
     */
    public static boolean isCBT(TreeNode head) {
        if (null == head) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if ((isLeaf && (current.left != null || current.right != null))
                    || (current.left == null && current.right != null)) {
                return false;
            }
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            } else {
                isLeaf = true;
            }
        }
        return true;
    }

    public static boolean isBBT(TreeNode head) {
        if (null == head) {
            return true;
        }
        return isBBTProcess(head).isBalance;
    }

    public static BalanceBTReturnInfo isBBTProcess(TreeNode head) {
        if (null == head) {
            return new BalanceBTReturnInfo(true, 0);
        }

        BalanceBTReturnInfo left = isBBTProcess(head.left);
        BalanceBTReturnInfo right = isBBTProcess(head.right);
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) < 2;
        int height = Math.max(left.height, right.height);
        return new BalanceBTReturnInfo(isBalance, height);
    }


    /**
     * 是否是完全二叉树
     * 高度h, 节点数n   n = 2^h - 1
     */

    public static boolean isFullCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        FullBTReturnInfo returnInfo = isFullBT(head);
        return returnInfo.nodeNum == ((1 << returnInfo.height) - 1);
    }

    public static FullBTReturnInfo isFullBT(TreeNode head) {
        if (null == head) {
            return new FullBTReturnInfo(0, 0);
        }
        FullBTReturnInfo leftInfo = isFullBT(head.left);
        FullBTReturnInfo rightInfo = isFullBT(head.right);
        return new FullBTReturnInfo(Math.max(leftInfo.height, rightInfo.height) + 1,
                leftInfo.nodeNum + rightInfo.nodeNum + 1);
    }

    static class FullBTReturnInfo {
        public int height;
        public int nodeNum;

        public FullBTReturnInfo(int height, int nodeNum) {
            this.height = height;
            this.nodeNum = nodeNum;
        }
    }

    static class BalanceBTReturnInfo {
        public boolean isBalance;
        public int height;

        public BalanceBTReturnInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}
