package com.csm.study.datastructure.heap;

/**
 * leetcode703求数据流中的第k大元素
 */
public class E03Leetcode703 {
    private MinHeap heap;

    /**
     * 初始化，调用add方法构造包含K个最大元素的小顶堆，堆顶就是第k个最大元素
     *
     * @param k    第K个最大元素
     * @param nums 数组
     */
    public E03Leetcode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * 模拟数据流中添加元素，并返回数据流中的第k个最大的元素
     *
     * @param val 添加的元素
     * @return 数据流中的第k个最大的元素
     */
    public int add(int val) {
        /*
            如果堆中的元素还没满，就往堆里加元素，满了就和堆顶元素比较
            比堆顶元素大就替换堆顶元素
         */
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        //返回堆顶元素
        return heap.peek();
    }

    public static void main(String[] args) {
        E03Leetcode703 test = new E03Leetcode703(3, new int[]{4, 5, 8, 2});
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }

}
