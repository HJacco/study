package com.study.newcoder.lesson10;

public class ManacherAlgorithm {
    public char[] manacherString(String str) {
        char[] chs = str.toCharArray();
        char[] result = new char[2 * chs.length + 1];
        int index = 0;
        for (int i = 0; i < result.length; i ++) {
            result[i] = (i & 1) == 0 ? '#' : chs[index ++];
        }
        return result;
    }

    public int maxLcpLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int center = -1;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < charArr.length; i ++){
            pArr[i] = right > i ? Math.min(pArr[2 * center - i], right - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i] ++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > right) {
                right = i + pArr[i];
                center = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str = "babab";
        System.out.println(new ManacherAlgorithm().maxLcpLength(str));
    }
}
