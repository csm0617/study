package com.csm.study.datastructure.queue;

import com.csm.study.datastructure.queue.structure.Queue;

import java.util.Iterator;

/**
 * 数组实现环形队列（数组的最后一位不存数据）
 *
 * @param <E>
 */
public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int head = 0;//头指针
    private int tail = 0;//尾指针
    private int size = 0;//元素个数

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    /**
     * 向队列尾插入值
     *
     * @param value 待插入值
     * @return 插入成功返回true，插入失败返回false
     */
    @Override
    public boolean offer(E value) {
        //先判断队列是否满了
        if (isFull()) {
            return false;
        }
        //在尾指针处添加
        array[tail] = value;
        //更新尾指针指向
        tail = (tail + 1) % array.length;
        size++;
        return true;
    }

    /**
     * 从队列头获取值，并移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E poll() {
        //先判断队列为不为空
        if (isEmpty()) {
            return null;
        }
        //不为空，拿到队头元素
        E value = array[head];
        //再更新head指针
        head = (head + 1) % array.length;
        size--;
        return value;
    }

    /**
     * 从队列头获取值，不移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E peek() {
        //先判断队列是否为空
        if (isEmpty()) {
            return null;
        }
        //不为空返回队头
        return array[head];
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回ture, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查队列是否满了
     *
     * @return 满了返回true, 没满返回false
     */
    @Override
    public boolean isFull() {
        return size == array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count++;
                return value;
            }
        };
    }
}
