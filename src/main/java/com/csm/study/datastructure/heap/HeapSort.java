package com.csm.study.datastructure.heap;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = {8, 7, 6, 2, 4, 3, 1, 9, 11, 5, 0};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(heap);
        //如果堆中还有元素，就不断的进行下面的操作：
        while (heap.size > 1) {
            //把堆顶(最大的元素)和最后一个位置互换
            System.out.println(heap.peek());
            heap.swap(0, heap.size - 1);
            //交换后堆的size-1,移除这个最大的元素（存放数据的是数组，并不是真的把这个元素删除了，只是让这个元素从堆中移除（也就是逻辑意义上的删除））
            heap.size--;
            //将堆顶的元素下潜，重新堆化
            heap.down(0);
        }
        //当堆中没有元素了,说明已经排好序了
        System.out.println(heap);
    }
}
