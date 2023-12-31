package com.csm.study.select_bubble_insert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelectBubbleInsert {

    //交换数组中i和j两个位置的数
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //选择排序，
    public static void selectionSort(int[] arr) {
        //数组为空或者数组长度小于2（天然有序）不需要排序
        if (arr == null || arr.length < 2) {
            return;
        }
        //minIndex指向最小值位置
        for (int minIndex, i = 0; i < arr.length - 1; i++) {
            //i~n-1，找最小值位置，放到i
            //0~n-1
            //2~n-1
            // 默认设置i为最小值位置
            minIndex = i;
            //比较位置从 i+1开始
            for (int j = i + 1; j < arr.length - 1; j++) {
                //找到最小值
                if (arr[j] < arr[i]) {
                    minIndex = j;
                }
            }
            //交换
            swap(arr, i, minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        //数组为空或者数组长度小于2（天然有序）不需要排序
        if (arr == null || arr.length < 2) {
            return;
        }
        //--------------------外层循环-----------------
        //刚开始0~n-1 谁大谁往后 end=arr.length - 1
        //0~n-2 n-1已经搞定，谁大谁往后 end--
        //0~n-3 n-2已经搞定，谁大谁往后
        //0~end--
        //0~0  到0位置时已经不需要比较了所以end>0
        //--------------------------------------------
        for (int end = arr.length - 1; end > 0; end--) {
            //--------------------内层循环-----------------
            //从0~end位置进行比较
            //0 1  i==0  1
            //1 2  i==1  2
            //2 3  i==2  3
            //end-1 end  i==end-1 最后一个比较对象应该是end-1和end
            //所以内层循环的起始条件 i=0开始，i只需要到end-1的位置就可以了，结束条件i<end,i++
            //——------------------------------------------
            for (int i = 0; i < end; i++) {
                //谁大谁往后
                if (arr[i] > arr[i + 1]) {
                    //交换
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    //冒泡新写法
    public static void bubbleSort2(int[] arr) {
        int n = arr.length;
        int end = n - 1, i = 0;
        while (end>0){
            if (arr[i]>arr[i+1]){
                swap(arr,i,i+1);
            }
            if (i<end-1){
                i++;
            }else{
                end--;
                i=0;
            }
        }
        log.info("{}",arr);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        //极端条件
        if (arr == null && arr.length < 2) {
            return;
        }
        //-------------------------外层循环---------------------------
        //插入 n个 数
        //0 ~ 0  插入第一个数 天然有序不用判断
        //0 ~ 1  插入第二个数 保证新插入的位置i==1,在0~1范围内有序
        //1 ~ 2  插入第三个数 保证新插入的位置i==2,在0~2范围内有序
        //2 ~ 3  插入第四个数 保证新插入的位置i==3,在0~3范围内有序
        //0 ~ n-1 插入第n个数 保证新插入的位置i==n-1,在0~n-1范围内有序
        //所以外层循环的开始i==1 一直到i==n-1 也就是arr.length-1
        //-----------------------------------------------------------
        for (int i = 0; i < arr.length; i++) {
            //----------------------------内层循环-----------------------
            //插入的数要和之前有序的数进行比较，假设6的位置是j，新插入的1在j+1的位置,那么j+1就要一直和j进行比较，
            //极端条件eg: 23456 新插入1
            //假设有序数组最后元素的位置是j,也就是6的位置是j，那么新插入的1在j+1的位置
            //判断结束的位置应该是j>=0（极端情况）
            //普通条件 23567 插入4   235647 235467  234567 当新插入的j+1小于j位置的数时就要发生交换，直到arr[j+1]>arr[j]
            //所以内层循环从j=i-1开始（j是有序数组的最后位置，也就是新插入的数的前一个位置）
            //结束条件是当数组的当前数arr[j]<arr[j+1]新插入的这个数时停止，否则j--不断左移，直到j==0和j+1==1进行最后一次判断
            //----------------------------------------------------------
            //0 ~ i-1的位置上有序了，新来的数是[i]，往左看
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                //满足拆入的数j+1比前一个数j小时就要发生交换
                swap(arr, j, j + 1);
            }

        }
    }


}
