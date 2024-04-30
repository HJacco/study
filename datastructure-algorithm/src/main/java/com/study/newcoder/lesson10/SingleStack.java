package com.study.newcoder.lesson10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SingleStack {
    public static int[][] getNearLessNoRepeat(int[] ary) {
        int[][] result = new int[ary.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ary.length; i ++) {
            while (!stack.isEmpty() && ary[stack.peek()] > ary[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                result[popIndex][0] = leftLessIndex;
                result[popIndex][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            result[popIndex][0] = leftLessIndex;
            result[popIndex][1] = -1;
        }
        return result;
    }

    public static int[][] getNearLess(int[] ary) {
        int[][] result = new int[ary.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < ary.length; i ++) {
            while (!stack.isEmpty() && stack.peek().get(0) > ary[i]) {
                List<Integer> popIndexes = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popIndex : popIndexes) {
                    result[popIndex][0] = leftLessIndex;
                    result[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && ary[stack.peek().get(0)] == ary[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                stack.push(indexes);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIndexes = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popIndex : popIndexes) {
                result[popIndex][0] = leftLessIndex;
                result[popIndex][1] = -1;
            }
        }
      return result;
    }
}
