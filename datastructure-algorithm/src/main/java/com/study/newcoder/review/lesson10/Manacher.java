package com.study.newcoder.review.lesson10;

public class Manacher {

    public char[] manacherStr(String str) {
        char[] chs = str.toCharArray();
        char[] manacherChs = new char[2 * chs.length + 1];
        int index = 0;
        for (int i = 0; i < manacherChs.length; i ++) {
            manacherChs[i] = (i & 1) == 0 ? '#' : chs[index ++];
        }
        return manacherChs;
    }

    public int maxLcd(String str) {
        if (null == str || "".equals(str)) {
            return 0;
        }
        char[] manacherChs = manacherStr(str);
        int[] lengthAry = new int[manacherChs.length];
        int center = -1;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < manacherChs.length; i ++) {
            lengthAry[i] = right > i ? Math.min(right - i, lengthAry[2 * center - i]) : 1;
            while (i + lengthAry[i] < manacherChs.length && i - lengthAry[i] > 0) {
                if (manacherChs[i + lengthAry[i]] == manacherChs[i - lengthAry[i]]) {
                    lengthAry[i] ++;
                } else {
                    break;
                }
            }
            if (right < i + lengthAry[i]) {
                center = i;
                right = i + lengthAry[i];
            }
            max = Math.max(max, lengthAry[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str = "abcdsesdc";
        Manacher m = new Manacher();
        System.out.println(m.maxLcd(str));
    }
}
