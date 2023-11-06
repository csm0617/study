package com.csm.study.datastructure.queue;

import com.csm.study.datastructure.queue.structure.Queue;

import java.util.Iterator;

/**
 * 数组实现环形队列（head和tail只计数,不作为索引来用）
 *
 * @param <E>
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int head = 0;//头指针
    private int tail = 0;//尾指针

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        array = (E[]) new Object[capacity];
    }

    /**
     *  head和tail都只用来计数,不作为索引，真正作为索引的是head%array.length
     *  eg: arr.length =3
     *  [ 0 1 2 ]
     *  //继续加tail=3,明显越界了，用tail%arr.length来作为索引
     *  3%3=0  所以需要更新的是 arr[0]
     */

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
        array[tail % array.length] = value;
        //更新尾指针指向
        tail++;
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
        E value = array[head % array.length];
        //再更新head指针
        head++;
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
        return array[head % array.length];
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回ture, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 检查队列是否满了
     *
     * @return 满了返回true, 没满返回false
     */
    @Override
    public boolean isFull() {
        return tail - head == array.length;
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
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p % array.length];
                p++;
                return value;
            }
        };
    }
}
