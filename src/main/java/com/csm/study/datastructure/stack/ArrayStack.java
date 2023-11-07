package com.csm.study.datastructure.stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private E[] array;
    private int top; //栈顶指针
    /*
        0   1   2   3   4   5   6
        底                      顶
        ps:和链栈不同（链栈基本上是头插push的，左侧是栈顶）
        但是数组实现栈 右侧 是栈顶 ，因为在 删除和添加元素 的时候在右侧的 性能会比较高
    */

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    /**
     * 向栈顶压入元素
     *
     * @param value 待压入值
     * @return 压入成功返回true，否则返回false
     */
    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        //top指向的是实际栈顶元素的后一位
        //eg:[ 0    1   2   3           ]
        //                      top
        //刚开始没有任何元素的时候top指向0，此时栈有4个元素,top应该指向的是3的后一位
        array[top++] = value;
        //后++先做array[top]=value 再top++;
        return true;
    }

    /**
     * 从栈顶弹出元素
     *
     * @return 栈非空返回栈顶元素，栈为空返回null
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
//        E value = array[top - 1];
//        top--;//不是真的要删除这个元素，而是top--(读不到就行了)
        //以上两行代码，等价于top先--
        return array[--top];
    }

    /**
     * 返回栈顶元素，不弹出
     *
     * @return 栈非空返回栈顶元素，栈为空返回null
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    /**
     * 判断栈是否为空
     *
     * @return 空返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 判断栈是否已满
     *
     * @return 满返回true, 否则返回false
     */
    @Override
    public boolean isFull() {
        return top == array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top;

            @Override
            public boolean hasNext() {
                return p > 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }
}
