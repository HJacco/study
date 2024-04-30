package com.study.newcoder.review.lesson11;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonousStack {
    public int[][] getNearLess(int[] ary) {
        if (null == ary || ary.length == 0) {
            return null;
        }
        int[][] result = new int[ary.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < ary.length; i ++) {
            while (!stack.isEmpty() && ary[stack.peek().get(0)] > ary[i]) {
                List<Integer> popIndexes = stack.pop();
                int leftIndex = !stack.empty() ? stack.peek().get(stack.peek().size() - 1) : -1;
                for (Integer index : popIndexes) {
                    result[index][0] = leftIndex;
                    result[index][1] = i;
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
            int leftIndex = !stack.empty() ? stack.peek().get(stack.peek().size() - 1) : -1;
            for (Integer index : popIndexes) {
                result[index][0] = leftIndex;
                result[index][1] = -1;
            }
        }
        return result;
    }
}
