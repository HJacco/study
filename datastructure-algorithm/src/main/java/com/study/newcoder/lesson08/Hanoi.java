package com.study.newcoder.lesson08;

/**
 * 汉诺塔问题
 */
public class Hanoi {

    public static void func(int rest, int down, String from , String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            func(rest - 1, down - 1, from , to, help);
            func(1, down, from , help, to);
            func(rest - 1, down - 1, help, from ,to);
        }
    }
}
