package com.csm.study.datastructure.stack;
/*
    有效的括号
 */
public class E01Leetcode20 {
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                //如果一开始就是右括号，但是stack没有任何元素，此时pop和peek就会返回空，赋值给基本的char类型就会出空指针异常
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        E01Leetcode20 s = new E01Leetcode20();
        System.out.println(s.isValid("][]"));
        System.out.println(s.isValid("([{}])"));
        System.out.println(s.isValid("[(]"));
        System.out.println(s.isValid("[](){}"));
        System.out.println(s.isValid("[(])"));
    }
}
