package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 */
public class Lc082_DeleteRepeatNodeListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast.val != slow.val) {
                fast = fast.next;
                pre = slow;
                slow = slow.next;
            } else {
                while (fast != null && fast.val == slow.val) {
                    fast = fast.next;
                    slow = slow.next;
                }
                if (pre != null) {
                    pre.next = fast;
                } else {
                    head = fast;
                }
                if (fast != null) {
                    slow = fast;
                    fast = fast.next;
                }

            }
        }
        return head;
    }
}
