package com.csm.study.datastructure.stack;

import org.yaml.snakeyaml.events.Event;

/**
 * 双栈模拟队列
 * 调用push,pop的方法最多100次
 */
public class E05Leetcode232 {

    //模拟队头
    ArrayStack<Integer> s1 = new ArrayStack<>(100);
    //模拟队尾
    ArrayStack<Integer> s2 = new ArrayStack<>(100);

    /*
           队列头                队列尾
        顶      底            底       顶
            s1                    s2
       eg:
         往队列中加入a,b
         入队,就需要调用s2.push,就是s2.push(a)  s2.push(b)                s1[]  s2[a b]
         出队,需要调用s1.pop(),但此时s1为空
         需要先把s2[a b]中的元素全部取出s2.pop(),用s1接收s1.push(s2.pop())  s1[a b] s2[]
         返回 s1.pop()       s1[b]  s2[]
         ！！！如果s1本来就不为空，直接将s1出队
     */

    /**
     * 队尾添加
     *
     * @param x
     */
    public void push(int x) {
        s2.push(x);
    }

    /**
     * 从队列中出队
     *
     * @return
     */
    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    /**
     * 当两个栈都为空时，队列才为空
     *
     * @return
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
