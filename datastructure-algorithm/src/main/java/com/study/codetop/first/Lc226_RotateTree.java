package com.study.codetop.first;

import com.study.common.entity.TreeNode;

/**
 *  给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc226_RotateTree {

    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }
}
