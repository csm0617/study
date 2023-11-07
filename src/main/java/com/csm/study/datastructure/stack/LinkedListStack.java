package com.csm.study.datastructure.stack;

import java.awt.*;
import java.util.Iterator;

/**
 * 链表实现栈
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int capacity = Integer.MAX_VALUE;
    private int size;

    private Node<E> head = new Node<>(null, null);//head作为哨兵节点

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /*
        head -> 1 -> null;
        假设此时加入2，那么
        首先2->1->null   也就是把新节点指向  added = new  Node<>(value, head.next)
        然后head ->2   也就是 head.next = added
    */

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
        head.next = new Node<>(value, head.next);
        size++;
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
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
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
        return head.next.value;
    }

    /**
     * 判断栈是否为空
     *
     * @return 空返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断栈是否已满
     *
     * @return 满返回true, 否则返回false
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
