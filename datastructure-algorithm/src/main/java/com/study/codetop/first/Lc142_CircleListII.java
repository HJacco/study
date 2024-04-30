package com.study.codetop.first;

import com.study.common.entity.ListNode;

/**
 *  给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数
 * pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc142_CircleListII {
    /**
     * 快慢指针，快指针走两次，慢指针走一次，相遇之后， 快指针回到开头位置，再次相遇的点即为入环点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != slow) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
