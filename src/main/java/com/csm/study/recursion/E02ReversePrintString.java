package com.csm.study.recursion;

/**
 * 逆序打印字符串（递归）
 */
public class E02ReversePrintString {

    /**
     * 字符串反转打印（递归）
     *
     *           停止         n==str.length ;
     * f(n) =
     *           f(n+1)         0<=n<=str.length-1 ;
     * @param n
     * @param str
     */
    public static void f(int n, String str) {

        if (n == str.length()) {
            return;
        }
        f(n + 1, str);
        //因为是逆序打印，递是压栈，归是出栈，
        //所以打印的操作应该放在调用递归函数后面
        System.out.println(str.charAt(n));
    }

    public static void main(String[] args) {
        f(0, "hello");
    }

}
