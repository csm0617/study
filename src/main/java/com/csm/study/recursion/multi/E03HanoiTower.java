package com.csm.study.recursion.multi;

import java.util.LinkedList;

public class E03HanoiTower {
    //汉诺塔的三根柱子
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    /**
     * 递归将源柱子a上的圆盘移动到目标柱子c上
     * @param n 移动的圆盘数
     * @param a 源柱子
     * @param b 借助的柱子
     * @param c 目标柱子
     */
    static void remove(int n,
                       LinkedList<Integer> a,
                       LinkedList<Integer> b,
                       LinkedList<Integer> c) {
        if (n == 0) {
            return;
        }
        //先将前n-1个盘子从a借助c移动到b
        remove(n - 1, a, c, b);
        //再将第n个盘子从a移动到c
        c.addLast(a.removeLast());
        print();
        //最后将放在b的前n-1的借助a移动到c
        remove(n - 1, b, a, c);
    }

    //初始化a为存放的柱子
    /**
     * 初始化a柱子的圆盘数量
     *
     * @param n
     */
    static void init(int n) {
        for (int i = n; i >= 1; i--) {
            //从a的尾部加入[ ... 3 2 1 ]保证底部是最大的
            a.addLast(i);
        }
    }

    public static void main(String[] args) {
        init(2);
        print();
        remove(2,a,b,c);
    }

    private static void print() {
        System.out.println("------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
