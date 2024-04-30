package com.study.common.entity;

public class DoublyLinkedNode {

    public int val;

    public DoublyLinkedNode next;

    public DoublyLinkedNode prev;

    public DoublyLinkedNode() {
    }

    public DoublyLinkedNode(int val, DoublyLinkedNode next, DoublyLinkedNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public DoublyLinkedNode getNext() {
        return next;
    }

    public void setNext(DoublyLinkedNode next) {
        this.next = next;
    }

    public DoublyLinkedNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedNode prev) {
        this.prev = prev;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        DoublyLinkedNode current = this;
        while (null != current) {
            sb.append(current.val).append("<=>");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
