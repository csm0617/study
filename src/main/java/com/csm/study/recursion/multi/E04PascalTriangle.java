package com.csm.study.recursion.multi;

public class E04PascalTriangle {

    /**
     * 求杨辉三角第i行第j列元素的值
     * 行和列都从0开始
     *
     * @param triangle 用来记录计算结果的二维数组（记忆法优化从O(2的n次)--->O(n的平方)）
     * @param i        行
     * @param j        列
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
//        print(5);
        print2(5);
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

    /**
     *  1                    0   [ 1 0 0 0 0 0 ]
     *  1 1                  1   [ 1 1 0 0 0 0 ]
     *  1 2 1                2   [ 1 2 1 0 0 0 ]
     *  1 3 3 1              3   [ 1 3 3 1 0 0 ]
     *  1 4 6 4 1            4   [ 1 4 6 4 1 0 ]
     */

    /**
     * eg: 打印第3行的时候，只有 第2行 有用，0行 和 1行 已经没用了
     * 可以采用动态规划的思想
     *
     * @param row 一维数组
     * @param i   行数 从0开始
     */
    private static void creatRow(int[] row, int i) {

        if (i == 0) {
            row[0] = 1;
            return;
        }
        /**
         * 行   row[n]的值
         * 3   [ 1 3 3 1 0 0 ]
         * 4   [ 1 4 6 4 1 0 ]
         * 当i>0时，可以发现第4行的row[i]是由第3行的row[i]+row[i-1]得到的
         * 动态的去维护更新这个数组就行
         */
        //将数组从最右边往最左边更新
        //注意边界，j应该从当前行号i开始，一直到j>0,因为row[0]=1,不需要再更新了
        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
            //以上面的为例
            //[1 0 0 0 1 0]   由 [1 3 3 1 0 0]  1 = 0 + 1;
            //[1 0 0 4 1 0]   由 [1 3 3 1 0 0]  1 = 1 + 3;
            //[1 0 6 4 1 0]   由 [1 3 3 1 0 0]  1 = 3 + 3;
            //[1 4 6 4 1 0]   由 [1 3 3 1 0 0]  1 = 3 + 1;
            //[1 4 6 4 1 0]   由 [1 3 3 1 0 0]  1 = 3 + 1;
        }
    }

    public static void print2(int n) {
        //
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {//行
            //更新当前行数为i的一维数组
            creatRow(row,i);
            //打印空格
            printSpace(n, i);
            //取出每一行的元素打印
            for (int j = 0; j <= i; j++) {
                //printf格式化输出
                //%d 数字  %4d输出数字后4个空格  %-4d输出数字后4个空格 并左对齐
                System.out.printf("%-4d",row[j]);
            }
            //一行结束后就换行
            System.out.println();
        }
    }
}
