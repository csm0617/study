package com.csm.study.recursion;

/**
 * 二分查找（递归版本）
 */
public class E03BinarySearch {
    public static int search(int[] arr, int target) {
        return f(arr, target, 0, arr.length);
    }

    /**
     * 做成private方法不对外暴露查找的范围
     *
     * @param arr
     * @param target
     * @param left
     * @param right
     * @return
     */
    private static int f(int[] arr, int target, int left, int right) {
        //递归结束条件
        if (left > right) {
            return -1;
        }
        int m = (left + right) >>> 1;
        if (arr[m] > target) {
            return f(arr, target, left, m - 1);
        } else if (arr[m] < target) {
            return f(arr, target, m + 1, right);
        } else {
            return m;
        }
    }
}
