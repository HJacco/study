package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc105_ConstructTreeFromPreAndInOrder {

    Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i ++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return this.buildRecursion(preorder, 0, inorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildRecursion(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int index = inOrderIndexMap.get(preorder[preStart]);

        TreeNode head = new TreeNode(preorder[preStart]);

        int leftLen = index - inStart;
//        int rightLen = inEnd - index;

        head.left = this.buildRecursion(preorder, preStart + 1, preStart + leftLen, inorder, inStart, index - 1);
        head.right = this.buildRecursion(preorder, preStart + leftLen + 1, preEnd, inorder, index + 1, inEnd);
        return head;
    }
}
