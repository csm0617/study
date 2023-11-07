package com.csm.study.stack;

import com.csm.study.datastructure.stack.LinkedListStack;
import org.junit.jupiter.api.Test;

/**
 * 链栈测试
 */
public class TestLinkedListStack {
    @Test
    public void push(){
        LinkedListStack<Integer> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        for (Integer integer : stack) {
            System.out.printf("%-4d",integer);
        }
    }
    @Test
    public void pop(){
        LinkedListStack<Integer> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.isFull());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        stack.push(5);
        System.out.println(stack.peek());
        System.out.println(stack.peek());

    }
}
