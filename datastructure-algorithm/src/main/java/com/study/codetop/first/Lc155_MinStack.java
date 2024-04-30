package com.study.codetop.first;

import java.util.Stack;

/**
 *  设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc155_MinStack {
    class MinStack {
        Stack<Integer> store = new Stack<>();
        Stack<Integer> min = new Stack<>();
        public MinStack() {
            min.push(Integer.MAX_VALUE);
        }

        void push(Integer n) {
            store.push(n);
            min.push(Math.min(min.peek(), n));
        }
        void pop() {
            store.pop();
            min.pop();
        }
        int top() {
            return store.peek();
        }
        int getMin() {
            return min.peek();
        }
    }
}
