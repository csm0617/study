package com.csm.study.datastructure.queue.priority_queue;


import com.csm.study.datastructure.queue.structure.Queue;

/**
 * 大顶堆实现优先队列
 * 堆：是由完全二叉树实现的所以可以用数组模拟
 */
public class PriorityQueue4<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue4(int capacity) {
        array = new Priority[capacity];
    }

    /* (上浮)
        1.入堆新元素，需要加入到数组的末尾，此时索引的位置为child
        2.不断比较新元素与它的父节点(parent)优先级
            --如果父节点的优先级低，那么就将父节点向下移动到child的位置，child索引被更新为parent索引，寻找下一个parent
            --直到父节点的优先级更高，或者child索引为0（child索引来到了根节点的位置0）
     */
    @Override
    public boolean offer(E offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;//child来到数组末尾需要添加的空位上
        int parent = (child - 1) / 2;//向下取整（根据完全二叉数的特性找到父节点）
        while (child > 0 && offered.priority() > array[parent].priority()) {//如果child索引没有来到根节点，并且新加元素的优先级大于父节点的优先级
            array[child] = array[parent];//将父节点下移
            child = parent;//child索引来到parent
            parent = (child - 1) / 2;//来到下一个parent的位置
        }
        //找到合适位置（来到根节点0或者找到优先级比它大的父节点了）
        array[child] = offered;//
        return true;
    }

    /*
        1.交换堆顶和尾部元素，让尾部元素出队（因为数组从尾部移除效率更高）
        2.（下潜）
            --从堆顶开始，将父元素（也就是刚刚与堆顶交换后的成为新堆顶的元素）与两个孩子较大者进行交换
            --直到父元素大于两个孩子，或者没有孩子为止
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        //交换堆顶和最后一个元素
        swap(0, size - 1);
        size--;
        E e = (E) array[size];//此时的队尾元素，也就是之前的堆顶元素（优先级最高）
        array[size] = null;//置空
        //----------------下潜--------------------
        down(0);
        return e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;//计算左孩子的索引（完全二叉树的性质）
        int right = left + 1;//右孩子=左孩子+1
        //假设从一开始父节点的优先级最高
        int max = parent;
        //寻找优先级比自己大的孩子的索引，更新为max
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {//如果max的值发生了改变，说明有孩子的优先级更大
            swap(max, parent);//那么交换父节点和优先级大的孩子
            down(max);//继续递归，直到parent的优先级最大，或者没有左右孩子了，max并没有被更新。
        }
    }

    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /*
        直接返回堆顶元素
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
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
