package com.study.newcoder.lesson05;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TreeOperate {
    /**
     * 递归方式 先序遍历
     */
    public static void preOrderTravelWithRecursion(TreeNode head) {
        if (null == head) {
            return;
        }
        System.out.println(head.val);
        preOrderTravelWithRecursion(head.left);
        preOrderTravelWithRecursion(head.right);
    }

    /**
     *  递归方式  中序遍历
     */
    public static void inOrderTravelWithRecursion(TreeNode head) {
        if (null == head) {
            return;
        }

        inOrderTravelWithRecursion(head.left);
        System.out.println(head.val);
        inOrderTravelWithRecursion(head.right);
    }

    /**
     * 递归方式 后序遍历
     */
    public static void postOrderTravelWithRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        postOrderTravelWithRecursion(head.left);
        postOrderTravelWithRecursion(head.right);

        System.out.println(head.val);
    }

    /**
     * 先序遍历
     * 先序遍历 非递归
     */
    public static void preOrderTravel(TreeNode head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode> container = new Stack<>();
        container.push(head);
        while (!container.isEmpty()) {
            TreeNode node = container.pop();
            System.out.println(node.val);

            if (null != node.right) {
                container.push(node.right);
            }
            if (null != node.left) {
                container.push(node.left);
            }
        }
    }

    public static void inOrderTravel(TreeNode head) {
        if (null == head) {
            return;
        }
        TreeNode current = head;
        Stack<TreeNode> container = new Stack<>();
        while (current != null || !container.isEmpty()) {
            if (null != current) {
                container.push(current);
                current = current.left;
            } else {
                current = container.pop();
                System.out.println(current.val);
                container.push(current.right);
            }
        }
    }

    public static void postOrderTravel(TreeNode head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode> container = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        container.push(head);
        while (!container.isEmpty()) {
            TreeNode node = container.pop();
            help.push(node);

            if (null != node.left) {
                container.push(node.left);
            }
            if (null != node.right) {
                container.push(node.right);
            }
        }

        while (!help.isEmpty()) {
            System.out.println(help.pop().val);
        }
    }

    /**
     * 宽度优先遍历
     */
    public static void widthPriorityTravel(TreeNode head) {
        if (null != head) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(head);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                System.out.println(node.val);

                if (null != node.left) {
                    queue.offer(node.left);
                }
                if (null != node.right) {
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 二叉树宽度
     */
    public static int getMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }

        // 节点与层数映射关系
        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        // 访问节点所在层
        int currentLevel = 1;
        // 当前层节点数
        int currentLevelNodes = 1;
        int maxWidth = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            int currentNodeLevel = nodeLevelMap.get(current);
            // 当前节点所在层数和遍历的层数一致，则当前层节点数+1
            if (currentNodeLevel == currentLevel) {
                currentLevelNodes ++;
            } else {
                maxWidth = Math.max(maxWidth, currentLevelNodes);
                currentLevelNodes = 1;
                currentLevel ++;
            }

            if (current.left != null) {
                nodeLevelMap.put(current.left, currentLevel + 1);
                queue.offer(current.left);
            }
            if (current.right != null) {
                nodeLevelMap.put(current.right, currentLevel + 1);
                queue.offer(current.right);
            }
        }
        maxWidth = Math.max(maxWidth, currentLevelNodes);
        return maxWidth;

    }

    /**
     * 判断是否搜索二叉树
     * 搜索二叉树：
     *      当前节点大于左子树任意一节点，小于右子树的任意一节点
     *
     * 思路：中序遍历，判断当前节点是否小于父节点
     */
    public static int preVale = Integer.MIN_VALUE;
    public static boolean isBST(TreeNode head) {
        if (null == head) {
            return true;
        }

        boolean left = isBST(head.left);
        if (head.val <= preVale) {
            return false;
        } else {
            preVale = head.val;
        }
        boolean right = isBST(head.right);
        return left && right;
    }

    public static boolean isBST2(TreeNode head) {
        if (null == head) {
            return true;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        processInOrder(head, list);
        int preValue = Integer.MIN_VALUE;
        for (TreeNode t : list) {
            if (t.val <= preValue) {
                return false;
            }
            preValue = t.val;
        }
        return true;
    }

    public static void processInOrder(TreeNode head, LinkedList<TreeNode> inOrderList) {
        if (head == null) {
            return;
        }
        processInOrder(head.left, inOrderList);
        inOrderList.add(head);
        processInOrder(head.right, inOrderList);
    }

    /**
     * 是否是完全二叉树
     * 完全二叉树:
     *      叶子节点集中在最后两层，且叶子节点向左集中
     * 思路：
     *      基于广度优先遍历，如果遇到叶子节点，则后面应该全是叶子节点
     */
    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode left = null;
        TreeNode right = null;
        boolean isLeaf = false;
        queue.offer(head);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            left = current.left;
            right = current.right;
            if ((isLeaf && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            } else {
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 判断是否是平衡二叉树
     * 平衡二叉树：
     *      左右子树高度差不超过1
     *
     */
    public static boolean isBalanceBinaryTree(TreeNode head) {
        if (null == head) {
            return true;
        }
        return processBalanceBinaryTree(head).isBalance;
    }

    public static ReturnInfo processBalanceBinaryTree(TreeNode head) {
        if (null == head) {
            return new ReturnInfo(true, 0);
        }
        ReturnInfo left = processBalanceBinaryTree(head.left);
        ReturnInfo right = processBalanceBinaryTree(head.right);
        int height = Math.max(left.height, right.height);
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(right.height - left.height) < 2;
        return new ReturnInfo(isBalance, height);
    }

    public static class ReturnInfo {
        public boolean isBalance;
        public int height;

        public ReturnInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    /**
     * 判断是否是满二叉树
     * 满二叉树：
     *      高度h, 节点数n   n = 2^h - 1
     */
    public static boolean isFullBT(TreeNode head) {
        if (null == head) {
            return true;
        }
        FullBTReturnInfo result = process(head);
        return result.nodeNum == (1 << result.height - 1);
    }

    public static FullBTReturnInfo process(TreeNode head) {
        if (null == head) {
            return new FullBTReturnInfo(0, 0);
        }
        FullBTReturnInfo left = process(head.left);
        FullBTReturnInfo right = process(head.right);

        int height = Math.max(left.height, right.height) + 1;
        int nodeNum = left.nodeNum + right.nodeNum + 1;
        return new FullBTReturnInfo(nodeNum, height);
    }

    public static class FullBTReturnInfo {
        public int nodeNum;
        public int height;

        public FullBTReturnInfo(int nodeNum, int height) {
            this.nodeNum = nodeNum;
            this.height = height;
        }
    }
}
