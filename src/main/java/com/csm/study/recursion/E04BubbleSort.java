package com.csm.study.recursion;

public class E04BubbleSort {
    public static void sort(int[] arr) {
        bubble2(arr, arr.length - 1);
    }

    /**
     * 递归实现冒泡排序改进版
     *
     * @param arr
     * @param j
     */

    private static void bubble2(int[] arr, int j) {
        if (j == 0) {
            return;
        }
        //在数组中可能会出现有一部分已经是排好序的
        //eg:[ 2 11 4 5 3 1 8 9 10 ]
        //第一轮的j应该是从arr.length-1的位置开始的换完应该是[ 2 4 5 3 1 8 9 10 11 ]
        //此时[8 9 10 11]已经排好序了，但是递归的次数始终是arr.length-1次，明显将有序区域进行了无意义的比较
        //所以定义一个 x作为指针 来划分 有序区域  和  无序区域
        //每当新一轮递归开始时，x被置为0，只要 i 和 i+1 发生交换
        //说明 i及其左侧是无序区域，需要更新x的值到i的位置，
        //当不再发生交换时，说明x右侧的区域已经排好序
        //所以右侧边界应该从x开始递归
        //还是上面的例子
        //第一轮结束后[ 2 4 5 3 1 8 9 10 11 ] x=7
        //但是第二轮结束后[ 2 4 3 1 5 8 9 10 11 ] 发生交换的是5和1  x=3 交给区间划分为无序[ 2 4 3 1 ] 和有序[ 5 8 9 10 11 ]
        //第三轮递归j的值从x=3开始，减少了无意义递归的次数
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (arr[i] > arr[i + 1]) {//为了稳定性不取等号
                //发生交换记录x指针
                x = i;
                int temp;
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        bubble2(arr, x);
    }


    /**
     * @param arr 数组
     * @param j   数组右边界的范围
     */
    private static void bubble(int[] arr, int j) {
        if (j == 0) {
            return;
        }
        for (int i = 0; i < j; i++) {
            if (arr[i] > arr[i + 1]) {//为了稳定性不取等号
                swap(arr, i, j);
            }
        }
        bubble(arr, j - 1);
    }

    /**
     * 交换数组中两个数的位置
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
