package com.csm.study.recursion.multi;

public class E04PascalTriangle {

    /**
     * 求杨辉三角第i行第j列元素的值
     * 行和列都从0开始
     *
     * @param i 行
     * @param j 列
     * @return 杨辉三角第i行第j列元素的值
     */
    public static int element(int i, int j) {
        //将杨辉三角的两边初始化为1
        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    public static void main(String[] args) {
        print(5);
    }

    /**
     * 打印杨辉三角中的空格
     * @param n 杨辉三角高度
     * @param i 行数
     */
    private static void printSpace(int n, int i) {
        int num = (n - i - 1) * 2;  // *2 是根据 %-4d的 4 经过4/2得来的
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    /**
     * 打印杨辉三角
     * @param n 杨辉三角的高度（从0开始）
     */
    public static void print(int n) {
        for (int i = 0; i < n; i++) {//行
            //在每一行开始前打印空格
            printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                //printf格式化输出
                //%d 数字  %4d输出数字后4个空格  %-4d输出数字后4个空格 并左对齐
                System.out.printf("%-4d", element(i, j));
            }
            //一行结束后就换行
            System.out.println();
        }
    }
}
