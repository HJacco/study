package com.study.newcoder.review.lesson05;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TreeSearch {

    public static void preOrderTravel(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);

        preOrderTravel(root.left);

        preOrderTravel(root.right);
    }

    public static void preOrderTravel2(TreeNode head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode> help = new Stack<>();
        help.push(head);
        while (!help.isEmpty()) {
            TreeNode node = help.pop();
            System.out.println(node.val);
            if (null != node.right) {
                help.push(node.right);
            }
            if (null != node.left) {
                help.push(node.left);
            }
        }
    }

    public static void inOrderTravel(TreeNode head) {
        if (head == null) {
            return;
        }

        inOrderTravel(head.left);

        System.out.println(head.val);

        inOrderTravel(head.right);
    }

    public static void inOrderTravel2(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = head;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.println(current.val);
                stack.push(current.right);
            }

        }
    }

    public static void postOrderTravel(TreeNode head) {
        if (null == head) {
            return;
        }
        postOrderTravel(head.left);

        postOrderTravel(head.right);

        System.out.println(head.val);
    }

    public static void postOrderTravel2(TreeNode head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode> first = new Stack<>();
        Stack<TreeNode> second = new Stack<>();
        first.push(head);
        while (!first.isEmpty()) {
            TreeNode current = first.pop();
            second.push(current);
            if (null != current.left) {
                first.push(current.left);
            }
            if (null != current.right) {
                first.push(current.right);
            }
        }

        while (!second.isEmpty()) {
            System.out.println(second.pop().val);
        }
    }

    /**
     * 层序遍历
     */
    public static void bfs(TreeNode head) {
        if (null == head) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.val);

            if (null != current.left) {
                queue.offer(current.left);
            }
            if (null != current.right) {
                queue.offer(current.right);
            }
        }
    }

    /**
     * 求最大宽度
     */
    public static int getMaxWidth(TreeNode head) {
        if (null == head) {
            return 0;
        }

        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        int currentLevel = 1;
        int currentLevelNodes = 0;
        int maxWidth = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            int nodeLevel = nodeLevelMap.get(current);
            if (currentLevel == nodeLevel) {
                currentLevelNodes ++;
            } else {
                maxWidth = Math.max(maxWidth, currentLevelNodes);
                currentLevelNodes = 1;
                currentLevel ++;
            }

            if (null != current.left) {
                nodeLevelMap.put(current.left, currentLevel + 1);
                queue.offer(current.left);
            }
            if (null != current.right) {
                nodeLevelMap.put(current.right, currentLevel + 1);
                queue.offer(current.right);
            }
        }
        maxWidth = Math.max(maxWidth, currentLevelNodes);
        return maxWidth;
    }

}
