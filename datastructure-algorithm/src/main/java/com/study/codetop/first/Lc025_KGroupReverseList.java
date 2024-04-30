package com.study.codetop.first;

import com.study.common.entity.ListNode;
import java.util.List;

import java.util.ArrayList;

/**
 * k个一组翻转链表
 */
public class Lc025_KGroupReverseList {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (this.size(head) < k) {
            return head;
        }
        // 分组
        List<ListNode> kGroups = this.group(head, k);

        // 长度等于k的链表翻转
        for (int i = 0; i < kGroups.size(); i ++) {
            ListNode cur = kGroups.get(i);
            if (this.size(cur) == k) {
                cur = reverse(cur);
            }
            kGroups.set(i, cur);
        }
        // 串联
        return this.join(kGroups);

    }

    /**
     * 将原始链表分成k组
     */
    public List<ListNode> group(ListNode head, int k) {
        List<ListNode> groups = new ArrayList<>();
        ListNode cur = head;
        ListNode start = head;
        int count = 0;
        while (cur != null) {
            count ++;
            if (count % k == 0) {
                groups.add(start);

                start = cur.next;
                cur.next = null;
                cur = start;
            } else {
                cur = cur.next;
            }

        }
        if (null != start) {
            groups.add(start);
        }
        return groups;
    }

    public ListNode reverse(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode join(List<ListNode> nodes) {
        for (int i = 0; i < nodes.size() - 1; i ++) {
            ListNode pre = getLastNode(nodes.get(i));
            pre.next = nodes.get(i + 1);
        }
        return nodes.get(0);
    }

    public ListNode getLastNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public int size(ListNode head) {
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
