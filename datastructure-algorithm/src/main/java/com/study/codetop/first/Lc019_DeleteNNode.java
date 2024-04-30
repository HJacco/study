package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *  https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class Lc019_DeleteNNode {
    /**
     * 快慢指针， 快指针先走n步， 然后快慢指针同时走，快指针走到头，慢指针正好走到倒数第n个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null && n == 1) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (n > 0) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
            n --;
        }
        ListNode pre = null;
        while (fast!= null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null) {
            return head.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return head;
    }
}
