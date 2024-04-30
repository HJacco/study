package com.study.newcoder.review.lesson05;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestAncestor {


    public static TreeNode findLowestAncestor2(TreeNode n1, TreeNode n2, TreeNode head) {
        if (null == head || head == n1 || head == n2) {
            return head;
        }
        TreeNode left = findLowestAncestor2(n1, n2, head.left);
        TreeNode right = findLowestAncestor2(n1, n2, head.right);

        if (left != null  && right != null) {
            return head;
        }
        return left != null ? left : right;
    }


    public static TreeNode findLowestAncestor(TreeNode node1, TreeNode node2, TreeNode head) {
        if (null == node1 || null == node2 || null == head) {
            return null;
        }
        Map<TreeNode, TreeNode> bloodRelation = new HashMap<>();
        processBloodRelation(head, bloodRelation);

        Set<TreeNode> tmp = new HashSet<>();
        TreeNode t1  = node1;
        while (t1 != null) {
            tmp.add(t1);
            t1 = bloodRelation.get(t1);
        }

        TreeNode t2 = node2;
        while (t2 != null) {
            if (tmp.contains(t2)) {
                return t2;
            }
            t2 = bloodRelation.get(t2);
        }
        return null;
    }

    public static void processBloodRelation(TreeNode head, Map<TreeNode, TreeNode> relation) {
        if (null == head) {
            return;
        }
        relation.put(head, head.left);
        relation.put(head, head.right);
        processBloodRelation(head.left, relation);
        processBloodRelation(head.right, relation);
    }
}
