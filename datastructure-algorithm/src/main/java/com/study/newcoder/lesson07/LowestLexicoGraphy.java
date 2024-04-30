package com.study.newcoder.lesson07;

import java.util.Arrays;

public class LowestLexicoGraphy {

    public static String lowestString(String[] strAry) {
        if (strAry == null || strAry.length == 0) {
            return "";
        }
        Arrays.sort(strAry, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        String result = "";
        for (int i = 0; i < strAry.length; i ++) {
            result += strAry[i];
        }
        return result;
    }
}
