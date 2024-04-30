package com.study.newcoder.lesson08;

import java.util.List;

/**
 * 字符串全排列
 */
public class AllPermutationOfStr {

    public static void process(char[] chs, int i, List<String> result) {
        if (i == chs.length) {
            result.add(String.valueOf(chs));
        }

        boolean[] visited = new boolean[26];
        for (int j = i; j < chs.length; j ++) {
            if (!visited[chs[j] - 'a']) {
                visited[chs[j] - 'a'] = true;
                swap(chs, i, j);
                process(chs, i + 1, result);
                swap(chs, j, i);
            }
        }
    }
    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
