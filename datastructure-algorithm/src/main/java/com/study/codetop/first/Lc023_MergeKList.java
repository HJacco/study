package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * LeetCode link : https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class Lc023_MergeKList {

    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        ListNode h = lists[0];
        for (int i = 1; i < lists.length; i ++) {
            h = merge2Lists(h, lists[i]);
        }
        return h;
    }

    public ListNode merge2Lists(ListNode head1, ListNode head2) {
        if (null == head1 || null == head2) {
            return head1 == null ? head2 : head1;
        }
        ListNode headHolder = new ListNode(-1);
        ListNode head = headHolder;
        ListNode l1 = head1, l2 = head2;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        }
        if (l2 != null) {
            head.next = l2;
        }
        return headHolder.next;
    }
}
