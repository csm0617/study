package com.csm.study.find_number;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FindLeft {
    //求>=num的最左位置
    //eg:2 4 5 5 5 6 8 9  求>=5的最左位置是2，求>=6的最左位置是7 找不到就返回-1

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
            if (findLeft(arr,num)!=validator(arr,num)){
                log.info("出错了");
                log.info("arr:{}， num:{}",arr,num);
                log.info("findLeft:{}  validator:{}",findLeft(arr,num),validator(arr,num));
            }
        }
        log.info("结束测试");

    }
    //产生随机数组
    private static int[] randomArray(int n, int V) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            //等概率产生从[1,V]的随机整数
            arr[i] = (int) (Math.random() * V) + 1;
        }
        return arr;
    }


    /**
     * 对数器 求大于等于num的最左位置
     *
     * @param arr
     * @param num
     * @return  找到返回下标，找不到返回-1
     */
    public static int validator(int[] arr, int num) {
        int ans = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return ans;
    }

    /**
     * 求大于等于num的最左位置
     *
     * @param arr
     * @param num
     * @return
     */
    public static int findLeft(int[] arr, int num) {
        int leftIndex = 0, rightIndex = arr.length - 1;
        //默认没找到
        int ans = -1;
        //当左边小于右边时，也就是还有数时
        while (leftIndex <= rightIndex) {
            //二分
            int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
            if (arr[middleIndex] >= num) {
                //如果中点>=num,说明左侧一定有>=num的数，更新ans的坐标
                ans = middleIndex;
                //继续二分，在左侧继续寻找
                rightIndex = middleIndex - 1;
            }else {
                //如果<，说明左侧没找到，则不需要更新ans,在这次二分的右侧寻找
                leftIndex = middleIndex + 1;
            }
        }
        return ans;
    }

}
