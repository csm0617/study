package com.csm.study.recursion.multi;

public class E04PascalTriangle {

    /**
     * 求杨辉三角第i行第j列元素的值
     * 行和列都从0开始
     * @param  triangle 用来记录计算结果的二维数组（记忆法优化从O(2的n次)--->O(n的平方)）
     * @param i 行
     * @param j 列
     * @return 杨辉三角第i行第j列元素的值
     */
    public static int element(int[][] triangle, int i, int j) {
        //如果已经计算过了，直接返回
        if (triangle[i][j] > 0) {
            return triangle[i][j];
        }
        //将杨辉三角的两边初始化为1
        if (j == 0 || i == j) {
            triangle[i][j] = 1;
            return 1;
        }
        //没有计算结果的
        triangle[i][j] = element(triangle, i - 1, j - 1) + element(triangle, i - 1, j);
        return triangle[i][j];
    }

    public static void main(String[] args) {
        print(5);
    }
    /**
     * 打印杨辉三角中的空格
     *
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
     *
     * @param n 杨辉三角的高度（从0开始）
     */
    public static void print(int n) {
        //定义一个二维数组来存放已经计算好的值,行数时能确定的列数没法确定
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {//行
            //每进行一轮外层循环就初始化一行二维数组
            //第i行的列数是i+1
            triangle[i] = new int[i + 1];
            //在每一行开始前打印空格
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                //printf格式化输出
                //%d 数字  %4d输出数字后4个空格  %-4d输出数字后4个空格 并左对齐
                System.out.printf("%-4d", element(triangle, i, j));
            }
            //一行结束后就换行
            System.out.println();
        }
    }
}
