package com.study.codetop.first;


import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * https://leetcode.cn/problems/generate-parentheses/
 *
 */
public class Lc022_ParentheseGenerate {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        process(new char[2 * n], 0, n, n, result);
        return result;
    }
    void process(char[] travelPath, int i, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(String.valueOf(travelPath));
            return;
        }
        if (left > right) {
            return;
        }
        if (left == right) {
            travelPath[i] = '(';
            process(travelPath, i + 1, left - 1, right, result);
        } else {
            if (left > 0) {
                travelPath[i] = '(';
                process(travelPath, i + 1, left - 1, right, result);
            }
            if (right > 0) {
                travelPath[i] = ')';
                process(travelPath, i + 1, left, right - 1, result);
            }
        }
    }
}
