package com.csm.study.datastructure.deque;

import java.util.Iterator;
import java.util.Queue;

/**
 * 基于双向环形链表实现双端队列
 *
 * @param <E> 元素类型
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {
    static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;//容量
    int size;//大小
    Node<E> sentinel = new Node<>(null, null, null);//哨兵

    //初始化双端队列
    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;  //把哨兵作为尾的话，尾的next指向头
        sentinel.prev = sentinel;  //把哨兵作为头的话，头的prev指向尾
    }

    /**
     * 从双端队列的队头插入
     *
     * @param value 待插入元素
     * @return 插入成功返回true, 失败返回false
     */
    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        /*
            往队列的头部添加就是往在哨兵的后面添加
            a（哨兵）  added  b
         */
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     * 从双端队列的队尾插入
     *
     * @param value 待插入元素
     * @return 插入成功返回true, 失败返回false
     */
    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        /*
            在队列的尾部添加就是在最后一个位置添加，也就是哨兵的前一个位置添加
            a   added   b（哨兵）
         */
        Node<E> b = sentinel;
        Node<E> a = sentinel.prev;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     * 从双端队列的队头获取元素，并移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        /*
            从队头移除元素时，移除的元素是哨兵的下一个（环形双向链表）
         */
        Node<E> a = sentinel;
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     * 从双端队列的队头获取元素，并移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        /*
            从队尾移除元素时，被移除的元素是哨兵的前一个（双向环形链表）
         */
        Node<E> b = sentinel;
        Node<E> removed = b.prev;
        Node<E> a = removed.prev;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     * 从双端队列的队头获取元素，不移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E peekFirst() {
        if (isEmpty()){
            return null;
        }
        return sentinel.next.value;
    }

    /**
     * 从双端队列的队尾获取值，不移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E peekLast() {
        if (isEmpty()){
            return null;
        }
        return sentinel.prev.value;
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空返回true, 不为空返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断队列是否满了
     *
     * @return 满了返回true, 没满返回false
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
            Node<E> p = sentinel.next;//从第一个元素开始遍历

            @Override
            public boolean hasNext() {
                return p != sentinel;
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
