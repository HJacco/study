package com.study.newcoder.lesson04;

import com.study.common.entity.DoublyLinkedNode;
import com.study.common.entity.ListNode;
import com.study.common.entity.RandomListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ListOperate {

    /**
     * 翻转单链表
     * 三个指针，一个指向pre， 一个指向head, 一个指向next
     */
    public static ListNode reverseSingleList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode current = head;
        while (current != null) {
            // 先拷贝next的引用
            next = current.next;
            // 把当前节点的的Next翻转 指向pre
            current.next = pre;

            // 移动三个指针
            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 翻转双向链表
     */
    public static DoublyLinkedNode reverseDoublyList(DoublyLinkedNode head) {
        DoublyLinkedNode prev = null;
        DoublyLinkedNode next = null;
        DoublyLinkedNode current = head;
        while (null != current) {
            next = current.next;
            current.next = prev;
            current.prev = next;

            prev = current;
            current = next;
        }
        return prev;

    }

    /**
     * 打印两个有序链表的公共部分
     */
    public static void printCommonPart(ListNode n1, ListNode n2) {
        ListNode current1 = n1;
        ListNode current2 = n2;

        while (current1 != null && current2 != null) {
            if (current1.val > current2.val) {
                current2 = current2.next;
            } else if (current1.val < current2.val) {
                current1 = current1.next;
            } else {
                System.out.println(current1.val);
                current1 = current1.next;
                current2 = current2.next;
            }
        }
    }

    /**
     * 判断链表是否是回文链表
     *
     */
    public static boolean isPalindromeWithStack(ListNode node) {
        Stack<ListNode> stack = new Stack<>();
        ListNode current = node;
        while (null != current) {
            stack.push(current);
            current = current.next;
        }
        current = node;
        while (!stack.empty()) {
            if (current.val != stack.pop().val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * 基于快慢指针，将最后一半链表压入栈里
     * 快慢指针的核心思路是刚开始的慢指针先走一格，然后快指针走两格，慢指针走一格；这样就能保证快指针走到头的时候，慢指针走到后半段的第一个节点
     */
    public static boolean isPalindromeWithHalfStack(ListNode node) {
        if (node == null || node.next == null) {
            return true;
        }

        ListNode slow = node.next;
        ListNode fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 链表后半部分压入栈
        Stack<ListNode> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        ListNode current = node;
        while (!stack.empty()) {
            if (current.val != stack.pop().val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * 判断单链表是否是回文模式
     * 时间复杂度O(n), 额外空间复杂度O(1)
     * 核心思路：先利用快慢指针，将指针挪到链表后半部分，然后对后半部分做逆序
     *         对比两部分链表，同时将后半部分链表还原
     */
    public static boolean isPalindromeFinalVersion(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 将慢指针挪到链表后半部分
        ListNode slow = head.next;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = head.next;
            fast = fast.next.next;
        }

        return true;
    }

    /**
     * 拷贝带有随机指针的链表
     * 基于map
     */
    public static RandomListNode copyListWithRandom1(RandomListNode head) {
        Map<RandomListNode, RandomListNode> duplicateNodes = new HashMap<>();
        RandomListNode current = head;
        while (null != current) {
            duplicateNodes.put(current, new RandomListNode(current.val));
            current = current.next;
        }

        current = head;
        while (current != null) {
            duplicateNodes.get(current).next = duplicateNodes.get(current.next);
            duplicateNodes.get(current).rand = duplicateNodes.get(current.rand);
            current = current.next;
        }
        return duplicateNodes.get(head);
    }

    public static RandomListNode copyListWithRandom2(RandomListNode head) {
        if (null == head) {
            return head;
        }

        // 在链表中插入复制节点
        RandomListNode current = head;
        RandomListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = new RandomListNode(current.val);
            current.next.next = next;
            current = next;
        }
        // 遍历链表，每次拿出两个节点并未拷贝节点设置rand
        current = head;
        RandomListNode copyNode = null;
        while (current != null) {
            next = current.next.next;
            copyNode = current.next;
            copyNode.rand = current.rand != null ? current.rand : null;
            current = next;
        }

        // 拆分链表
        current = head;
        RandomListNode copyHead = head.next;
        RandomListNode copyCurrent = null;
        while (current != null) {
            next = current.next.next;
            copyCurrent = current.next;
            current.next = next;
            copyCurrent.next = next != null ? next.next : null;
            current = next;
        }
        return copyHead;
    }

    /**
     * 求两个链表相交的第一个节点
     * 相交分两种情况：两个无环链表相交，两个有环链表相交
     */
    public static ListNode findFirstIntersectNode(ListNode head1, ListNode head2) {
        if (null == head1 || head2 == null) {
            return null;
        }
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 链表入环点
     * 快慢指针，当快慢指针相遇，快指针回到头节点，一次走一格，俩指针再次相遇则是入环点
     */
    public static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 相遇之后 fast指针回到头结点
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 两个无环链表相交问题
     * 先分别求出两个链表的长度，然后长链表的指针先走差值步；两链表指针相遇的点即第一个交叉点
     */
    public static ListNode noLoop(ListNode head1, ListNode head2) {
        if (null == head1 || null == head2) {
            return null;
        }
        int l1 = 0;
        ListNode current1 = head1;
        while (current1.next != null) {
            l1 ++;
            current1 = current1.next;
        }

        int l2 = 0;
        ListNode current2 = head2;
        while (current2.next != null) {
            l2 ++;
            current2 = current2.next;
        }
        // 尾部不是相同节点，说明不相交
        if (current1 != current2) {
            return null;
        }
        current1 = l1 < l2 ? head1 : head2;
        current2 = l1 >= l2 ? head1 : head2;
        int n = Math.abs(l1 - l2);
        while (n > 0) {
            current2 = current2.next;
            n --;
        }
        while (current1 != current2) {
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1;
    }

    /**
     * 两个有环链表相交
     * 交点要么在环上，要么在环外
     * */
    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        // 入环点相同，则交点不在环上，只需要按照无环相交的求法，把入环点当做终点，求交点即可
        if (loop1 == loop2) {
            ListNode c1 = head1;
            ListNode c2 = head2;
            int l = 0;
            while (c1 != null) {
                l ++;
                c1 = c1.next;
            }
            while (c2 != null) {
                l --;
                c2 = c2.next;
            }
            c1 = l > 0 ? head1 : head2;
            c2 = c1 == head1 ? head2 : head1;
            l = Math.abs(l);
            while (l > 0) {
                c1 = c1.next;
                l --;
            }
            while (c1 != c2) {
                c1 = c1.next;
                c2 = c2.next;
            }
            return c1;
            // 交点在环上，随便返回一个入环点
        } else {
            ListNode c1 = loop1.next;
            while (c1 != loop1) {
                if (c1 == loop2) {
                    return c1;
                }
                c1 = c1.next;
            }
            return null;
        }
    }

    /**
     * 将单向链表按照某个值划分成小于，等于，大于的三部分
     */
    public static ListNode partition(ListNode head, int target) {
        if (null == head) {
            return null;
        }
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size ++;
            current = current.next;
        }
        ListNode[] ary = new ListNode[size];

        current = head;
        int i = 0;
        while (current != null) {
            ary[i ++] = current;
            current = current.next;
        }

        int left = -1, right = ary.length;
        for (int j = 0; j < ary.length; ) {
            if (ary[j].val < target) {
                left ++;
                ListNode t = ary[left];
                ary[left] = ary[j];
                ary[j] = t;
                j ++;
            } else if (ary[j].val > target) {
                right --;
                ListNode t = ary[j];
                ary[j] = ary[right];
                ary[right] = t;
            } else {
                j ++;
            }
        }
        for (int j = 0; j < ary.length - 1; j ++) {
            ary[j].next = ary[j + 1];
        }
        ary[ary.length - 1].next = null;
        return ary[0];
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

//        ListNode nr = reverseSingleList(n1);
//        System.out.println(nr.toString());

        ListNode _n1 = new ListNode(0);
        ListNode _n2 = new ListNode(2);
        _n1.next = _n2;
        ListNode _n3 = new ListNode(3);
        _n2.next = _n3;
        ListNode _n4 = new ListNode(6);
        _n3.next = _n4;
        System.out.println(_n1.toString());
        printCommonPart(n1, _n1);

    }
}
