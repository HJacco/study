package com.study.newcoder.lesson11;

import com.study.common.entity.TreeNode;

public class MorrisTravel {

    public void travel(TreeNode head) {
        if (null == head) {
            return;
        }
        TreeNode current = head;
        TreeNode mostRight = null;
        while (null != current) {
            mostRight = current.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = current;
                    current = current.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            current = current.right;
        }
    }

    public void morrisPre(TreeNode head) {
        if (null == head) {
            return;
        }
        TreeNode current = head;
        TreeNode mostRight = null;
        while (null != current) {
            mostRight = current.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(current + " ");
                    mostRight.right = current;
                    current = current.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(current + " ");
            }
            current = current.right;
        }
    }

    public void morrisIn(TreeNode head) {
        if (null == head) {
            return;
        }
        TreeNode current = head;
        TreeNode mostRight = null;
        while (null != current) {
            mostRight = current.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = current;
                    current = current.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(current + " ");
            current = current.right;
        }
    }
}
