package com.csm.study.datastructure.heap;

/**
 * 小顶堆
 */
public class MinHeap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        array = new int[capacity];
    }

    //初始化建堆
    public MinHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("heap:[ ");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(array[i] + "\t");
            } else {
                sb.append(array[i] + "");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * 建堆算法
     */
    private void heapify() {
        //怎么找到最后这个非叶子节点 size/2 -1 （当前堆的大小除以2减-1）适用于下标从0开始
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {
        illegalIndex();
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 堆顶元素
     */
    public int poll() {
        illegalIndex();
        int top = array[0];
        swap(0, size - 1);//将堆顶和最后一个元素交换
        size--;
        down(0);
        return top;
    }

    /**
     * 删除指定索引处的元素
     *
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        illegalIndex(index);
        int removed = array[index];
        swap(index, size - 1);//思路也是和最后一个元素交换位置后，删除最后一个位置元素
        size--;
        down(index);
        return removed;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }


    /**
     * 判断堆是否为空
     *
     * @return 空返回true, 非空返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断堆是否满了
     *
     * @return 满了返回true, 不满返回false
     */
    public boolean isFull() {
        return size == array.length;
    }

    /**
     * 将新添加的元素offered上浮：直到offered小于父元素或者到堆顶
     *
     * @param offered 添加的额元素
     */
    public void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered < array[parent]) {//比父节点小，父节点下移到child位置
                array[child] = array[parent];
            } else {//不比父节点小了就退出while循环
                break;
            }
            //
            child = parent;
        }
        //退出while循环，child已经找到合适的位置了
        //两种可能：
        //1.找到比自己小的父节点了,break退出循环
        //2.来到根节点child==0退出循环
        //不管哪种方式，最终child的位置就是最终位置（总结：先找位置，最后再交换）
        array[child] = offered;
    }


    /**
     * 将parent索引处的元素下潜：与两个孩子较小者交换，直至没孩子，或者孩子没他小
     *
     * @param parent 需要下潜元素的索引
     */
    public void down(int parent) {
        int min = parent;//假设一开始parent索引就是最小的
        int left = parent * 2 + 1;//左孩子索引
        int right = left + 1;//右孩子索引
        //!!!比较的范围应该是size，不是array.length
        //因为size是堆的属性，而array.length是数组的属性，不能混着用
        //否则堆顶出堆的时候，和最后一个元素交换，又换回来了。
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        //当发现min的值发生改变的时候，才进行交换
        if (min != parent) {
            swap(min, parent);
            //交换完继续递归下潜
            down(min);
        }
    }

    /**
     * 交换数组中两个数的位置
     *
     * @param i 位置 i
     * @param j 位置 j
     */
    public void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public void illegalIndex() {
        if (isEmpty()) {
            throw new IllegalArgumentException("堆已经空了,返回0位置不合法");
        }
    }

    private void illegalIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(index + "位置索引不合法");
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        MinHeap heap = new MinHeap(array);
        System.out.println(heap);
    }
}
