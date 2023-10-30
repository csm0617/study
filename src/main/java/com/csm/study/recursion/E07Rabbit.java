package com.csm.study.recursion;

/**
 * 兔子问题:求第n个月兔子的数量
 */
public class E07Rabbit {

    public static int rabbit(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return rabbit(n - 1) + rabbit(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(rabbit(8));
    }
}
