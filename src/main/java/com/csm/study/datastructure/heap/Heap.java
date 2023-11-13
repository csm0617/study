package com.csm.study.datastructure.heap;

public class Heap {
    int size;
    int[] array;
    boolean max;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (max) {
            sb.append("minHeap:[ ");
            for (int i = size - 1; i >= 0; i--) {
                if (i != 0) {
                    sb.append(array[i] + "  ");
                } else {
                    sb.append(array[i] + "");
                }
            }
        } else {
            sb.append("maxHeap:[ ");
            for (int i = 0; i < size; i++) {
                if (i != size - 1) {
                    sb.append(array[i] + "  ");
                } else {
                    sb.append(array[i] + "");
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * 初始化堆
     *
     * @param capacity 容量
     * @param max      true:大顶堆 false:小顶堆
     */
    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    //初始化建堆
    public Heap(int[] array, boolean max) {
        this.array = array;
        this.size = array.length;
        this.max = max;
        heapify();
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
        if (isFull()) {//满了就扩容
            grow();
        }
        up(offered);
        size++;
        return true;
    }

    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0,
                newArray, 0,
                size);
        array = newArray;

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
            boolean cmp = max ? offered > array[parent] : offered < array[parent];
            if (cmp) {//比父节点大，父节点下移到child位置
                array[child] = array[parent];
            } else {//不比父节点大了就退出while循环
                break;
            }
            //
            child = parent;
        }
        //退出while循环，child已经找到合适的位置了
        //两种可能：
        //1.找到比自己大的父节点了,break退出循环
        //2.来到根节点child==0退出循环
        //不管哪种方式，最终child的位置就是最终位置（总结：先找位置，最后再交换）
        array[child] = offered;
    }


    /**
     * 将parent索引处的元素下潜：与两个孩子较大者交换，直至没孩子，或者孩子没他大
     *
     * @param parent 需要下潜元素的索引
     */
    public void down(int parent) {
        int maxOrMin = parent;//假设一开始parent索引就是最大的
        int left = parent * 2 + 1;//左孩子索引
        int right = left + 1;//右孩子索引
        //!!!比较的范围应该是size，不是array.length
        //因为size是堆的属性，而array.length是数组的属性，不能混着用
        //否则堆顶出堆的时候，和最后一个元素交换，又换回来了。
        if (left < size && (max ? array[left] > array[maxOrMin] : array[left] < array[maxOrMin])) {
            maxOrMin = left;
        }
        if (right < size && (max ? array[right] > array[maxOrMin] : array[right] < array[maxOrMin])) {
            maxOrMin = right;
        }
        //当发现max的值发生改变的时候，才进行交换
        if (maxOrMin != parent) {
            swap(maxOrMin, parent);
            //交换完继续递归下潜
            down(maxOrMin);
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
        Heap heap = new Heap(5, false);
        for (int i = 0; i <= 10; i++) {
            heap.offer(i);
            System.out.println(heap);
            System.out.println(heap.array.length);
        }
    }
}
