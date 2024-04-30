package com.study.codetop.first;

import com.study.common.entity.ListNode;

import java.util.Stack;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class Lc234_PalindromeList {

    public boolean isPalindrome(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head.next;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }

        ListNode current = head;
        while (!stack.isEmpty()) {
            if (current.val != stack.pop()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

}
