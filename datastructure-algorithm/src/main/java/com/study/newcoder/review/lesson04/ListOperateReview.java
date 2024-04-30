package com.study.newcoder.review.lesson04;

import com.study.common.entity.DoublyLinkedNode;
import com.study.common.entity.ListNode;

import java.util.Stack;

public class ListOperateReview {
    public static ListNode reverseLinkedList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            // 整体向前移动
            current = next;
        }
        return pre;
    }

    /**
     * 反转双向链表
     * @param head
     *      头节点
     * @return DoublyLinkedNode
     */
    public static DoublyLinkedNode reverseDoublyLinkedList(DoublyLinkedNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        DoublyLinkedNode pre = null;
        DoublyLinkedNode next = null;
        DoublyLinkedNode current = head;
        while (null != current) {
            next = current.next;
            current.next = pre;
            current.prev = next;

            // 整体向前移动
            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 打印两个有序链表的公共部分
     */
    public static void printCommonPart(ListNode h1, ListNode h2) {
        if (null == h1 || null == h2) {
            return;
        }

        ListNode c1 = h1;
        ListNode c2 = h2;
        while (c1 != null && c2 != null) {
            if (c1.val > c2.val) {
                c2 = c2.next;
            } else if (c1.val < c2.val) {
                c1 = c1.next;
            } else {
                System.out.println(c1.val);
                c1 = c1.next;
                c2 = c2.next;
            }
        }
    }

    /**
     * 判断是否是回文结构
     */
    public static boolean isPalindromeWithStack(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode slow = head.next;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        ListNode current = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != current.val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }


    public static ListNode partition(ListNode head, int target) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode next = null;

        ListNode smallHead = null;
        ListNode smallTail = null;

        ListNode equalHead = null;
        ListNode equalTail = null;

        ListNode bigHead = null;
        ListNode bigTail = null;

        while (current != null) {
            next = current.next;
            current.next = null;
            if (current.val < target) {
                if (null == smallHead) {
                    smallHead = smallTail = current;
                } else {
                    smallTail.next = current;
                    smallTail = smallTail.next;
                }
            } else if (current.val == target) {
                if (null == equalHead) {
                    equalHead = equalTail = current;
                } else {
                    equalTail.next = current;
                    equalTail = equalTail.next;
                }
            } else {
                if (null == bigHead) {
                    bigHead = bigTail = current;
                } else {
                    bigTail.next = current;
                    bigTail = bigTail.next;
                }
            }
            current = next;
        }

        if (null != smallTail) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (null != equalTail) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;

        System.out.println(n1.toString());
        System.out.println(reverseLinkedList(n1));
    }
}
