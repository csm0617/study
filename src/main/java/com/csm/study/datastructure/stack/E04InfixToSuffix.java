package com.csm.study.datastructure.stack;

import java.util.LinkedList;

/**
 * 中缀表达式转后缀表达式（带括号）
 */
public class E04InfixToSuffix {
    /*
        中缀表达式转后缀表达式：
        a+b                      ab+
        a+b-c                    ab+c-
        a*b+c                    ab*c+
        a+b*c                    abc*+
        a+b*c-d                  abc*+d-
        (a+b)*c                  ab+c*

        规律总结：
        1.遇到非运算符   直接拼串
        2.遇到 + - * /
             -- 它的优先级比栈顶的元素高，入栈
             -- 否则把栈里优先级 >= 它的都出栈， 它再入栈
        3.遍历完成，栈里剩余的运算符依次出栈
        4.带括号()
             -- 左括号直接入栈 ，左括号的优先级设置为0(最低优先级)
             -- 右括号就把栈里到左括号为止的所有运算符出栈(包括左括号也出栈，但是左括号不参与字符串的拼接)

        举例 (a+b*c-d)*e
        1.  (  左括号入栈  栈(
        2.  a  遇到+入栈   栈(+
        3.  abc 遇到*入栈  栈(+*
        4.  遇到- 把+*出栈 拼接abc*+   栈(-
        5.  abc*+d
        6.  遇到) 把括号内的全部的运算符-出栈 拼接abc*+d-
        7.  遇到*压栈
        8.  abc*+d-e 走到字符串结束了，把栈内的运算符全部出栈
        9.  abc*+d-e*
     */


    static int priority(char c) {
        int result = 0;
        switch (c) {
            case '*':
            case '/':
                result = 2;
                break;
            case '+':
            case '-':
                result = 1;
                break;
            case '(':
                result = 0;
                break;
            default:
                throw new IllegalArgumentException("不合法的运算符" + c);
        }
        return result;
    }

    static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                //如果是运算符
                case '+':
                case '-':
                case '*':
                case '/':
                    if (stack.isEmpty()) {//当栈里没有元素时直接入栈
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {//当栈里有元素时，如果当前的运算符优先级大于栈顶运算符，就入栈
                            stack.push(c);
                        } else {
                            //当栈不为空，如果栈顶运算符大于当前运算，就把栈顶运算符出栈，直到小于当前运算符
                            while (!stack.isEmpty() && priority(c) <= priority(stack.peek())) {
                                sb.append(stack.pop());
                            }
                            //出循环意味着，栈顶没有优先级大于当前运算符的优先级了，就把当前运算符压栈
                            stack.push(c);
                        }
                    }
                    break;
                case '('://左括号直接压栈
                    stack.push(c);
                    break;
                case ')': //把（）内的运算符从栈内弹出
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    //把左括号出栈
                    stack.pop();
                    break;
                default://字符直接拼接
                    sb.append(c);
                    break;
            }
        }
        //循环结束时，如果栈里还有运算符就全部出栈
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix("a+b-c/d+e*f"));   // ab+cd/-ef*+
        System.out.println(infixToSuffix("a*(b+c)"));      //abc+*
        System.out.println(infixToSuffix("(a+b*c-d)*e"));  //abc*+d-e*
    }
}
