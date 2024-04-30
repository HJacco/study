package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Lc021_MergeTwoOrderedList {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode headHolder = new ListNode(-1);
        ListNode head = headHolder;
        while(list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if (list1 != null) {
            head.next = list1;
        }
        if (null != list2) {
            head.next = list2;
        }
        return headHolder.next;
    }
}
