package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class Lc024_SwapPairList {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = head.next;

        ListNode pre = new ListNode(-1);
        ListNode before = head;
        pre.next = before;
        ListNode after = before.next;
        ListNode next = null;

        while (before != null && after != null) {
            next = after.next;
            pre.next = after;
            after.next = before;
            before.next = next;

            // 整体向前移动
            pre = pre.next.next;
            before = pre.next;
            after = (before == null ? null : before.next);
        }

        return result;
    }
}
