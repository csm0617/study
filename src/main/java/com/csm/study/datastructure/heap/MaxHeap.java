package com.csm.study.datastructure.heap;

import java.util.Arrays;

/**
 * 大顶堆
 * <p>
 * Floyd 建堆算法，也称为“自下而上堆化”（Bottom-Up Heap Construction），是一种用于构建堆数据结构的高效算法。该算法的核心思想是从最后一个非叶子节点开始，逐层向上进行堆化，确保每个子树都满足堆的性质。
 * <p>
 * 以下是 Floyd 建堆算法的基本原理：
 * <p>
 * 1.选择最后一个非叶子节点： 首先确定最后一个非叶子节点的索引。在一个数组表示的堆中，最后一个非叶子节点的索引可以通过 (n / 2) - 1 计算得到，其中 n 是数组的长度。
 * <p>
 * 2.自底向上堆化： 从最后一个非叶子节点开始，向上逐层进行堆化。对于每个非叶子节点，执行以下步骤：
 * <p>
 * 比较该节点与其子节点的值，找到最大（或最小）值的索引。
 * 如果最大（或最小）值的索引不是当前节点的索引，交换它们的值。
 * 递归地对交换后的子节点进行堆化。
 * 重复步骤2： 重复上述步骤，直到根节点被处理完毕，整个数组被调整成一个堆。
 * <p>
 * Floyd 建堆算法的优点在于它避免了对已经是堆的子树进行不必要的调整，从而减少了比较和交换的次数，提高了算法的效率。相比于其他建堆算法，Floyd 建堆算法通常具有更好的性能。
 */
public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    //初始化建堆
    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "array=" + Arrays.toString(array) +
                '}';
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
    private void replace(int replaced) {
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
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child]=offered;
    }


    /**
     * 将parent索引处的元素下潜：与两个孩子较大者交换，直至没孩子，或者孩子没他大
     *
     * @param parent 需要下潜元素的索引
     */
    private void down(int parent) {
        int max = parent;//假设一开始parent索引就是最大的
        int left = parent * 2 + 1;//左孩子索引
        int right = left + 1;//右孩子索引
        if (left < array.length && array[left] > array[max]) {
            max = left;
        }
        if (right < array.length && array[right] > array[max]) {
            max = right;
        }
        //当发现max的值发生改变的时候，才进行交换
        if (max != parent) {
            swap(max, parent);
            //交换完继续递归下潜
            down(max);
        }
    }

    /**
     * 交换数组中两个数的位置
     *
     * @param i 位置 i
     * @param j 位置 j
     */
    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void illegalIndex() {
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
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(heap);   //[10, 9, 7, 8, 5, 6, 3, 1, 4, 2]
    }

}
