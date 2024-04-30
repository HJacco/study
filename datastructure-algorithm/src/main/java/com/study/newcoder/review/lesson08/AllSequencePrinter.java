package com.study.newcoder.review.lesson08;

import java.util.ArrayList;
import java.util.List;

public class AllSequencePrinter {
    public static List<String> allSubSequenceOfStr(String str) {
        List<String> result = new ArrayList<>();
        process(str.toCharArray(), 0, result, new ArrayList<>());
        return result;
    }

    public static void process(char[] chs, int index, List<String> result, List<Character> tmp) {
        if (index == chs.length) {
            result.add(String.valueOf(tmp));
            return;
        }

        List<Character> tmp1 = copy(tmp);
        List<Character> tmp2 = copy(tmp);

        tmp1.add(chs[index]);
        process(chs, index + 1, result, tmp1);

        process(chs, index + 1, result, tmp2);
    }
    public static List<Character> copy(List<Character> src) {
        return new ArrayList<>(src);
    }
}
