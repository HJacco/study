package com.study.newcoder.review.lesson10;


public class KMP {
    public int index(String source, String target) {
        if (null == source || null == target) {
            return -1;
        }
        char[] sourceCharAry = source.toCharArray();
        char[] targetCharAry = target.toCharArray();
        if (sourceCharAry.length < targetCharAry.length) {
            return -1;
        }

        int[] nextAry = next(targetCharAry);
        int i1 = 0;
        int i2 = 0;
        while (i1 < source.length() && i2 < target.length()) {
            if (sourceCharAry[i1] == targetCharAry[i2]) {
                i1 ++;
                i2 ++;
            } else if (nextAry[i2] > 0) {
                i2 = nextAry[i2];
            } else {
                i1 ++;
            }
        }
        return i1 - i2;
    }

    public static void main(String[] args) {
        String source = "abcbdds";
        String target = "ab";
        System.out.println(new KMP().index(source, target));
    }

    /**
     * 求字符数字最大前置匹配度数组
     */
    public int[] next(char[] chs) {
        if (chs.length == 1) {
            return new int[]{-1};
        }
        if (chs.length == 0) {
            return new int[]{};
        }
        int[] nextAry = new int[chs.length];
        nextAry[0] = -1;
        nextAry[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < chs.length){
            if (chs[i - 1] == chs[cn]) {
                nextAry[i ++] = ++cn;
            } else if (cn > 0) {
                cn = nextAry[cn];
            } else {
                nextAry[i ++] = 0;
            }
        }

        return nextAry;
    }

}
