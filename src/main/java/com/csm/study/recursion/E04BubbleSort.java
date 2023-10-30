package com.csm.study.recursion;

public class E04BubbleSort {
    public static void sort(int[] arr){
        bubble(arr,arr.length-1);
    }


    /**
     *
     * @param arr 数组
     * @param j 数组右边界的范围
     */
    private static void bubble(int[] arr,int j){
        if (j==0){
            return;
        }
        for (int i = 0; i < j; i++) {
            if (arr[i]>arr[i+1]){//为了稳定性不取等号
                swap(arr,i,j);
            }
        }
        bubble(arr,j-1);
    }

    /**
     * 交换数组中两个数的位置
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr,int i, int j){
        int temp;
        temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
