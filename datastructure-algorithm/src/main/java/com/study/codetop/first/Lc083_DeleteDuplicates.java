package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 *  https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 *
 */
public class Lc083_DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
            } else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        slow.next = fast;
        return head;
    }

}
