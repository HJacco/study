package com.study.codetop.first;

import com.study.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc098_BinSearchTree {

    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderTravel(root, result);
        for (int i = 1; i < result.size(); i ++) {
            if (result.get(i) <= result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrderTravel(TreeNode node, List<Integer> travelPath) {
        if (null == node) {
            return;
        }
        inOrderTravel(node.left, travelPath);
        travelPath.add(node.val);
        inOrderTravel(node.right, travelPath);
    }
}
