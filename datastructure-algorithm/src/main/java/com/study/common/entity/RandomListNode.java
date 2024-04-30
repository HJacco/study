package com.study.common.entity;

public class RandomListNode {
    public int val;
    public RandomListNode next;

    public RandomListNode rand;


    public RandomListNode() {

    }

    public RandomListNode(int val) {
        this.val = val;
    }

    public RandomListNode(int val, RandomListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        RandomListNode current = this;
        StringBuffer tmp = new StringBuffer();
        while (null != current) {
            tmp.append(current.val).append("->");
            current = current.next;
        }
        tmp.append("null");
        return tmp.toString();
    }
}
