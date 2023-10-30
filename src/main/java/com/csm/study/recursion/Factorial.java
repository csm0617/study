package com.csm.study.recursion;
//递归求n的阶乘
public class Factorial {


    /**
     * 递归公式
     *            1               (n=0,n=1)  结束条件
     * f(n) =
     *           n*f(n-1)         (n!=0&&n!=1)
     */



    /**
     *求n的阶乘
     * @param n
     * @return
     */
    public static int f(int n){
        if (n==0||n==1){
            return 1;
        }
        return n*f(n-1);
    }

    public static void main(String[] args) {
        System.out.println(f(5));
    }
}
