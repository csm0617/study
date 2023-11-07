package com.csm.study.datastructure.stack;

import java.util.LinkedList;

/**
 * 后缀表达式求值(逆波兰表达式)
 */
public class E02Leetcode150 {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        Integer a, b;
        //遍历字符串
        for (String t : tokens) {
            //遇到数字就压栈
            switch (t) {
                //遇到运算符
                case "+":
                    //就从栈中弹出两个操作数，进行运算后将结果压栈
                    //注意：先出栈的是 x (+ - * /) y 中的y
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a + b);
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a * b);
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;
                default:
                    stack.push(Integer.parseInt(t));
                    break;
            }

        }
        //最后栈中只有一个数字，出栈就是结果
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens={"100","4","5","*","/"};
        E02Leetcode150 e02Leetcode150 = new E02Leetcode150();
        System.out.println(e02Leetcode150.evalRPN(tokens));
    }
}
