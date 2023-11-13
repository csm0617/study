package com.csm.study.datastructure.heap;

/**
 * 求数组的第K大元素
 */
public class E02Leetcode215 {

    /**
     * 求数组的第K大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        MinHeap heap = new MinHeap(nums.length);
        /*
            思路：
                 将k个元素先加到小顶堆中，然后遍历剩下的k~nums.length和堆顶元素比较
                 如果堆顶元素小，就把堆顶用当前元素替换掉
                 就是第K大的元素
         */
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.replace(nums[i]);
            }
//            System.out.println(heap);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {8, 9, 6, 2, 5, 3, 5, 1};
        System.out.println(new E02Leetcode215().findKthLargest(nums, 5));

    }

}
