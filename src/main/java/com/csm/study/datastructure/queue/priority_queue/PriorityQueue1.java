package com.csm.study.datastructure.queue.priority_queue;

import com.csm.study.datastructure.queue.structure.Queue;

/**
 * 基于无序数组实现优先级队列
 *
 * @param <E> 队列中的元素类型，必须实现Priority接口
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
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
        array[size++] = value;//后++是先赋值，再++
        return true;
    }

    //返回优先级最高的索引值
    private int selectMax() {
        int max = 0;
        //假设初始优先级最高的索引在0位置，从索引1开始遍历整个数组，寻找最大优先级的位置
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    //根据优先级最高的索引，删除数组中优先级最高的元素
    private void remove(int index) {
        //如果最高优先级的元素不是在最后一个位置(size-1就是最后一个位置，因为是从0开始先存元素，再size++)
        if (index < size - 1) {
            //那么就把当前位置（最大优先级所在的索引位置）往后的元素全部向前移动一个位置
            System.arraycopy(array, index + 1, array, index, (size - 1) - index);
            //index-1是源数组的位置（被删除的位置的后一位），移动到目标数组的index(被删除位置)，移动
            // 长度是（size-1）(数组的最后一位)到被删除元素index的距离
        }
        //删除元素size--（如果是在最后一个位置就不需要移动，只需要将size--,下次添加会覆盖掉）
//        size--;
        array[--size]=null;//把删除的位置赋值为空
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
        int maxIndex = selectMax();
        E value = (E) array[maxIndex];
        remove(maxIndex);
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
        int max = selectMax();
        return (E) array[max];
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
