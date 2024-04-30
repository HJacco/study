package com.study.common.entity;

/**
 *  common bean
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        ListNode current = this;
        StringBuffer tmp = new StringBuffer();
        while (null != current) {
            tmp.append(current.val).append("->");
            current = current.next;
        }
        tmp.append("null");
        return tmp.toString();
    }

}
