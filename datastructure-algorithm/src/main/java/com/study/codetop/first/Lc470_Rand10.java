package com.study.codetop.first;

/**
 *  给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 *
 * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 *
 * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc470_Rand10 {
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7(); // [1 - 49];
            if (num <= 40) return num % 10 + 1;
        }
    }

    int rand7() {
        return 1;
    }
}
