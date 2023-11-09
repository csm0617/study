package com.csm.study.datastructure.queue.priority_queue;

import com.csm.study.datastructure.queue.structure.Queue;

import java.awt.*;

/**
 * 基于有序数组实现优先级队列（数组是根据优先级排序的）
 *
 * @param <E> 队列中的元素类型，必须实现Priority接口
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * 向队列尾插入值
     *
     * @param value 待插入值
     * @return 插入成功返回true，插入失败返回false
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    /**
     * 做插入排序将要插入的元素按优先级插入到有序数组中
     * @param value
     */
    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > value.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = value;
    }

    /**
     * 从队列头获取值，并移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = (E) array[size - 1];
        array[--size] = null;//先--，size先进行--，再把要删除的元素赋值为空
        return value;
    }

    /**
     * 从队列头获取值，不移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
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
}
