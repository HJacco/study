package com.study.newcoder.review.lesson08;
import java.util.List;
public class AllSequenceArrange {
    public static void process(char[] chs, int index, List<String> result) {
        if (index == chs.length) {
            result.add(String.valueOf(chs));
        }
        boolean[] visited = new boolean[26];
        for (int j = index; j < chs.length; j ++) {
            if (!visited[j]) {
                swap(chs, j, index);
                process(chs, index + 1, result);
                swap(chs, index, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
