package com.study.newcoder.review.lesson04;

import com.study.common.entity.ListNode;

/**
 * 链表相交问题
 */
public class ListCross {

    /**
     * 判断链表是否有环，无环返回null, 有环返回入环点
     */
    public static ListNode loop(ListNode head) {
        if (null == head || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if (loop1 == loop2) {
            ListNode c1 = head1;
            ListNode c2 = head2;
            int n = 0;
            while (c1 != loop1) {
                n ++;
                c1 = c1.next;
            }
            while (c2 != loop2) {
                n --;
                c2 = c2.next;
            }
            c1 = n > 0 ? head1 : head2;
            c2 = n < 0 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n --;
                c1 = c1.next;
            }
            while (c1 != c2) {
                c1 = c1.next;
                c2 = c2.next;
            }
            return c1;
        } else {
            ListNode c1 = loop1.next;
            while (c1 != loop1) {
                if (c1 == loop2) {
                    return loop1;
                }
                c1 = c1.next;
            }
            return null;
        }
    }

    public static ListNode noLoopCross(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        int len = 0;
        ListNode current1 = l1;
        while (current1.next != null) {
            len ++;
            current1 = current1.next;
        }

        ListNode current2 = l2;
        while (current2.next != null) {
            len --;
            current2 = current2.next;
        }

        if (current1 != current2) {
            return null;
        }

        current1 = len > 0 ? l1 : l2;
        current2 = len > 0 ? l2 : l1;
        len = Math.abs(len);
        while (len != 0) {
            len --;
            current1 = current2.next;
        }
        while (current1 != current2) {
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1;

    }
}
