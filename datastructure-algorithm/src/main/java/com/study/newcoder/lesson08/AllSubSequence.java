package com.study.newcoder.lesson08;

import java.util.ArrayList;
import java.util.List;

public class AllSubSequence {

    public static void printAllSubSeq(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<>());
    }

    public static void process(char[] chars, int i, List<Character> result) {
        if (i == chars.length) {
            System.out.println(result);
        }

        List<Character> cpList = copy(result);
        cpList.add(chars[i]);
        process(chars, i + 1, cpList);

        List<Character> cpList2 = copy(result);
        process(chars, i + 1, cpList2);
    }

    public static List<Character> copy(List<Character> src) {
        return new ArrayList<>(src);
    }
}
