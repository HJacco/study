package com.study.lesson4;


import com.study.common.entity.ListNode;
import com.study.newcoder.lesson04.ListOperate;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ListOperateTest {

    @Test
    public void testIsPalindromeWithStack () {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        System.out.println(n1.toString());
        Assert.isTrue(!ListOperate.isPalindromeWithStack(n1), "fail");


        ListNode n1_ = new ListNode(1);
        ListNode n2_ = new ListNode(2);
        n1_.next = n2_;
        ListNode n3_ = new ListNode(1);
        n2_.next = n3_;
        Assert.isTrue(ListOperate.isPalindromeWithStack(n1_), "fail");
    }
}
