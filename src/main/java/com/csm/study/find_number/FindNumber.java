package com.csm.study.find_number;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FindNumber {
    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTimes = 500000;
        log.info("开始测试");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            //数组排序
            Arrays.sort(arr);
            int num = (int) (Math.random() * V) + 1;
            if (exist(arr, num) != right(arr, num)) {
                log.info("出错了！！！");
            }
        }

        log.info("测试结束");
    }

    //
    private static int[] randomArray(int n, int V) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            //等概率产生从[1,V]的随机整数
            arr[i] = (int) (Math.random() * V) + 1;
        }
        return arr;
    }

    //对数器,通过遍历的方式查找这个数
    public static boolean right(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (num == arr[i]) {
                return true;
            }
        }
        return false;
    }

    //二分查找保证数组有序才行
    public static boolean exist(int[] arr, int num) {
//        log.info("num:{}  arr:{}",num,arr);
        //特殊情况
        if (arr == null || arr.length == 0) {
            return false;
        }
        //
        int leftIndex = 0, rightIndex = arr.length - 1, middleIndex = 0;
        while (leftIndex <= rightIndex) {
//            middleIndex = (leftIndex + rightIndex) / 2;
            middleIndex=leftIndex+((rightIndex-leftIndex)>>1);
//            log.info("mid:{}",arr[middleIndex]);
            if (arr[middleIndex] == num) {
//                log.info("二分查找：{}在数组下标为{}的位置",num,middleIndex);
                return true;
            } else if (arr[middleIndex] > num) {
                rightIndex = middleIndex - 1;
            } else {
                leftIndex = middleIndex + 1;
            }
        }
//        log.info("二分查找未找到！！！");
        return false;
    }

}
