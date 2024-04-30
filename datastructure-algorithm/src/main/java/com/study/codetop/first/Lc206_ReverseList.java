package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Lc206_ReverseList {

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (null != current) {
            next = current.next;

            current.next = pre;

            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 翻转链表递归版
     */
    public ListNode reverseListWithRecursion(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode tmp = reverseListWithRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }
}
