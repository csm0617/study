package com.csm.study.datastructure.queue.priority_queue;

import com.csm.study.datastructure.list.structure.ListNode;

public class MinHeap {
    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        array = new ListNode[capacity];
    }
    /*  构造小顶堆 (上浮)
        1.入堆新元素，需要加入到数组的末尾，此时索引的位置为child
        2.不断比较新元素与它的父节点(parent)优先级
            --如果父节点的优先级（值）大，那么就将父节点向下移动到child的位置，child索引被更新为parent索引，寻找下一个parent
            --直到父节点的优先级（值）更小，或者child索引为0（child索引来到了根节点的位置0）
    */
    boolean offer(ListNode offered){
        if (isFull()) {
            return false;
        }
        int child = size++;//child来到数组末尾需要添加的空位上
        int parent = (child - 1) / 2;//向下取整（根据完全二叉数的特性找到父节点）
        while (child > 0 && offered.val < array[parent].val) {//如果child索引没有来到根节点，并且新加元素的优先级小于父节点的优先级（值）
            array[child] = array[parent];//将父节点下移
            child = parent;//child索引来到parent
            parent = (child - 1) / 2;//来到下一个parent的位置
        }
        //找到合适位置（来到根节点0或者找到优先级（值）比它更小的父节点了）
        array[child] = offered;//
        return true;
    }

    public ListNode poll(){
        if (isEmpty()) {
            return null;
        }
        //交换堆顶和最后一个元素
        swap(0, size - 1);
        size--;
        ListNode node = array[size];//此时的队尾元素，也就是之前的堆顶元素（优先级最高）
        array[size] = null;//置空
        //----------------下潜--------------------
        down(0);
        return node;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;//计算左孩子的索引（完全二叉树的性质）
        int right = left + 1;//右孩子=左孩子+1
        //假设从一开始父节点的优先级（值）最小
        int min = parent;
        //寻找优先级（值）比自己更小的孩子的索引，更新为min
        if (left < size && array[left].val < array[min].val) {
            min = left;
        }
        if (right < size && array[right].val < array[min].val) {
            min = right;
        }
        if (min != parent) {//如果min的值发生了改变，说明有孩子的优先级（小）更小
            swap(min, parent);//那么交换父节点和优先级（值）更小的孩子
            down(min);//继续递归，直到parent的优先级最小，或者没有左右孩子了，min并没有被更新。
        }
    }

    private void swap(int i, int j) {
        ListNode t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean isFull(){
        return size==array.length;
    }

}
