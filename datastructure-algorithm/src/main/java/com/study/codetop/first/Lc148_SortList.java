package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc148_SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // binary split list
        ListNode[] segments = this.split(head);
        ListNode h1 = sortList(segments[0]);
        ListNode h2 = sortList(segments[1]);
        // merge
        return merge(h1, h2);
    }

    ListNode merge(ListNode h1, ListNode h2) {
        if (null == h1 || null == h2) {
            return h1 != null ? h1 : h2;
        }
        ListNode headHolder = new ListNode(-1);
        ListNode current = headHolder;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                current.next = h1;
                h1 = h1.next;
            } else {
                current.next = h2;
                h2 = h2.next;
            }
            current = current.next;
        }
        if (h1 != null) {
            current.next = h1;
        }
        if (h2 != null) {
            current.next = h2;
        }
        return headHolder.next;
    }

    public ListNode[] split(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode h = slow.next;
        slow.next = null;
        return new ListNode[]{head, h};
    }


}
