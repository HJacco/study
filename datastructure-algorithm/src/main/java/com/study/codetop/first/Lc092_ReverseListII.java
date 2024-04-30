package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc092_ReverseListII {
    ListNode postNode = null;
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseTopN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public ListNode reverseTopN(ListNode head, int n) {
        if (n == 1) {
            postNode = head.next;
            return head;
        }
        ListNode newHead = reverseTopN(head.next, n - 1);
        head.next.next = head;
        head.next = postNode;
        return newHead;
    }
}
