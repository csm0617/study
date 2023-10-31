package com.csm.study.recursion.multi;

import java.util.Arrays;

/**
 * 递归求斐波那契的第n项（多路递归）
 */
public class E01Fibonacci {
    public static int f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    //-------------------------斐波那契优化背景---------------------------
    //        斐波那契是多路递归，在全部压栈完成以后结构是一个二叉树
    //                               f(5)
    //                          /             \
    //                      f(4)              f(3)
    //                     /      \          /    \
    //                  f(3)      f(2)      f(2)   f(1)
    //                  /   \      / \      /  \
    //                f(2)  f(1) f(1) f(0) f(1) f(0)
    //               /    \
    //              f(1)  f(0)
    //在返回调用的时候会有很多重复的计算f(3),f(2),当n越来越大时重复计算越来越多
    //通过加入一个结果数组，记录已经这些计算过的值，
    //在下次递归调用的时候，先判断是不是在结果数组中，
    //有就直接返回（对二叉树进行剪枝，减少重复计算）
    //没有就进行计算，并加入结果集中
    /**
     * 使用记忆法（备忘录法）改进
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        //存放斐波那契数组第n项的结果
        int[] cache = new int[n + 1];
        //为了区分第n项是否计算出结果，用-1填充，cache[n]==-1说明该项 没有计算出结果
        Arrays.fill(cache, -1);
        //第0和1项已知，初始化
        cache[0] = 0;
        cache[1] = 1;
        return recursion(n,cache);
    }

    /**
     * 斐波那契数组求第n项优化
     * @param n
     * @param cache
     * @return
     */
    public static int recursion(int n, int[] cache) {
        //每次递归前先查结果数组
        //如果n已经在结果数组里了，就直接返回
        if (cache[n] != -1) {
            return cache[n];
        }
        //如果不在结果数组，就递归调用计算，计算完以后存入结果数组。
        int x = recursion(n - 1, cache);
        int y = recursion(n - 2, cache);
        cache[n] = x + y;
        return cache[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(8));
    }
}
