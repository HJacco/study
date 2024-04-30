package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
public class Lc160_IntersectionList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return headA == null ? headB : headA;
        }
        // 分别计算两个链表的长度
        int sizeA = this.sizeOf(headA);
        int sizeB = this.sizeOf(headB);

        ListNode longer = sizeA > sizeB ? headA : headB;
        ListNode shorter = longer == headA ? headB : headA;

        int minus = Math.abs(sizeA - sizeB);
        while (minus > 0) {
            longer = longer.next;
            minus --;
        }
        while (longer != null && shorter != null) {
            if (longer == shorter) {
                return longer;
            }
            longer = longer.next;
            shorter = shorter.next;
        }
        return null;
    }

    private int sizeOf(ListNode head) {
        if (head == null) {
            return 0;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size ++;
            cur = cur.next;
        }
        return size;
    }
}
