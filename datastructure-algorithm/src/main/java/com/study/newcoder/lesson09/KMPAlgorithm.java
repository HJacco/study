package com.study.newcoder.lesson09;

public class KMPAlgorithm {

    public int indexOf(String source, String target) {
        if (null == source || null == target || target.length() > source.length() || target.length() < 1) {
            return -1;
        }
        char[] sourceCharAry = source.toCharArray();
        char[] targetCharAry = target.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(targetCharAry);
        while (i1 < source.length() && i2 < target.length()) {
            if (sourceCharAry[i1] == targetCharAry[i2]) {
                i1 ++;
                i2 ++;
            } else if (i2 == 0) {
                i1 ++;
            } else {
                i2 = next[i2];
            }
        }

        return i2 == target.length() ? i1 - i2 : -1;
    }

    public static void main(String[] args) {
        KMPAlgorithm kmp = new KMPAlgorithm();
        System.out.println(kmp.indexOf("abcd", "cd"));
    }

    /**
     * 字符串前缀后缀最大匹配度
     */
    public int[] getNextArray(char[] chs) {
        if (chs.length == 1) {
            return new int[]{-1};
        }

        int[] result = new int[chs.length];
        result[0] = -1;
        result[1] = 0;
        int i = 2;
        // 代表前一个位置的最大匹配度
        // 代表已匹配成的字符串后面一个位置
        int cn = 0;
        while (i < result.length) {
            if (chs[i - 1] == chs[cn]) {
                result[i ++] = ++ cn;
            } else if (cn > 0) {
                cn = result[cn];
            } else {
                result[i ++] = 0;
            }
        }
        return result;
    }
}
