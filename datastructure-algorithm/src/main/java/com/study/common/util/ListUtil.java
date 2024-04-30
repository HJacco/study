package com.study.common.util;

import com.study.common.entity.DoublyLinkedNode;
import com.study.common.entity.ListNode;

public class ListUtil {

    public static String printDoublyLinkedList(DoublyLinkedNode head) {
        StringBuffer sb = new StringBuffer();
        DoublyLinkedNode current = head;
        while (null != current) {
            sb.append(current.val).append("<=>");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }


    public static String printList(ListNode head) {
        StringBuffer sb = new StringBuffer();
        ListNode current = head;
        while (null != current) {
            sb.append(current.val).append("<=>");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
