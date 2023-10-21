package com.csm.study.validator;

import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Slf4j
public class Validator {
    /**
     * 1，你想要测的方法a（最优解）
     * 2，实现复杂度不好但是容易实现的方法b（暴力解）
     * 3，实现一个随机样本产生器（长度也随机、值也随机）
     * 4，把方法a和方法b跑相同的输入样本，看看得到的结果是否一样
     * 5，如果有一个随机样本使得比对结果不一致，打印这个出错的样本进行人工干预，改对方法a和方法b
     * 6，当样本数量很多时比对测试依然正确，可以确定方法a（最优解）已经正确。
     *
     * 关键是第5步，找到一个数据量小的错误样本，便于你去带入debug
     * 然后把错误例子带入代码一步一步排查
     * Print大法、断点技术都可以
     * 对数器的门槛其实是比较高的，因为往往需要在两种不同思路下实现功能相同的两个方法，暴力一个、想象中的最优解是另一个。
     * 以后的很多题目都会用到对数器，几乎可以验证任何方法，尤其在验证贪心、观察规律方面很有用
     * 到时候会丰富很多对数器的实战用法，这里只是一个简单易懂的示例
     */

    //---------------------------------------《对数器示例》----------------------------------------
    public static void main(String[] args) {
        //随机数组的最大长度
        int N= 100;
        //随机数组每个值，在[1,v]之间随机
        int V=1000;
        //测试的次数
        int testTimes=50000;
        log.info("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            int[] selectArray = copyArray(arr);
            int[] bubbleArray = copyArray(arr);
            int[] insertArray = copyArray(arr);
            selectionSort(selectArray);
            bubbleSort(bubbleArray);
            insertSort(insertArray);
            if (!sameArray(selectArray,bubbleArray)||!sameArray(insertArray,bubbleArray)){
                log.error("出错了！");
                //-----------------------------------------------------------
                // 当有错了
                // 打印是什么例子，出错的
                // 打印三个功能，各自排序成了什么样
                // 可能要把例子带入，每个方法，去debug！
                //-----------------------------------------------------------
                log.info("原始数组{}",arr);
                log.info("selectArray:{}",selectArray);
                log.info("bubbleArray:{}",bubbleArray);
                log.info("insertArray:{}",insertArray);
            }

        }
        log.info("测试结束");
    }

    private static boolean sameArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 随机样本产生器
     * @param n 数组长度
     * @param v 随机整数的最大值
     * @return
     */
    public static int[] randomArray(int n,int v){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            //Math.random()->double ->等概率产生一个[0,1)随机小数
            //Math.random()*v 等概率产生一个[0,v)随机小数
            //(int) (Math.random()*v)->double->int 等概率产生一个[0,v-1]的随机整数
            //(int) (Math.random()*v) +1  等概率产生一个[1,v]的随机整数
            arr[i] = (int) (Math.random()*v) +1;
        }
        return arr;
    }

    //用备份的数据进行测试

    /**
     * 拷贝原数组数据到新数组中
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr){
        int length = arr.length;
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i]=arr[i];
        }
        return ans;
    }



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
            for (int j = i + 1; j < arr.length; j++) {
                //找到最小值
                if (arr[j] < arr[minIndex]) {
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
                swap(arr,j,j+1);
            }

        }
    }

}
