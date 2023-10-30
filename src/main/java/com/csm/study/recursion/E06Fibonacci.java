package com.csm.study.recursion;

/**
 * 递归求斐波那契的第n项
 */
public class E06Fibonacci {
    public static int f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return f(n-1) + f(n-2);
    }

    public static void main(String[] args) {
        System.out.println(f(8));
    }
}
