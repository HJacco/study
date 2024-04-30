package com.study.newcoder.lesson05;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeOperate2 {
    /**
     * 求两个节点的最低公共祖先
     * 1、先通过遍历树完成对每个节点的父节点的记录
     * 2、对n1到头结点的链路记录
     * 3、遍历n2到头结点的链路
     */
    public static TreeNode lowestCommonAncestor(TreeNode n1, TreeNode n2, TreeNode head) {
        if (null == n1 || null == n2 || null == head) {
            return null;
        }
        Map<TreeNode, TreeNode> bloodRelation = new HashMap<>();
        processLowestCommonAncestor(head, bloodRelation);

        HashSet<TreeNode> tmp = new HashSet<>();
        TreeNode t1 = n1;
        while (t1 != null) {
            tmp.add(t1);
            t1 = bloodRelation.get(t1);
        }

        TreeNode t2 = n2;
        while (t2 != null) {
            if (tmp.contains(t2)) {
                return t2;
            }
            t2 = bloodRelation.get(t2);
        }
        return null;

    }
    public static void processLowestCommonAncestor(TreeNode head, Map<TreeNode, TreeNode> relation) {
        if (null == head) {
            return;
        }
        relation.put(head.left, head);
        relation.put(head.right, head);

        processLowestCommonAncestor(head.left, relation);
        processLowestCommonAncestor(head.right, relation);
    }


    public static TreeNode lowestCommonAncestor2(TreeNode n1, TreeNode n2, TreeNode head) {
        if (head == null || head == n1 || head == n2) {
            return head;
        }
        TreeNode left = lowestCommonAncestor(n1, n2, head.left);
        TreeNode right = lowestCommonAncestor(n1, n2, head.right);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }

    /**
     * 序列化一棵树
     * 先序遍历
     */
    public static String serializeTree(TreeNode head) {
        if (null == head) {
            return "#!";
        }
        String result = head.val + "!";
        result += serializeTree(head.left);
        result += serializeTree(head.right);
        return result;
    }

    public static TreeNode deSerializeTree(String str) {
        String[] nodesStr = str.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < nodesStr.length; i ++) {
            queue.offer(nodesStr[i]);
        }
        return deSerialize(queue);
    }
    public static TreeNode deSerialize(Queue<String> queue) {
        String nodeStr = queue.poll();
        if ("#".equalsIgnoreCase(nodeStr)) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(nodeStr));
        head.left = deSerialize(queue);
        head.right = deSerialize(queue);
        return head;
    }
}
