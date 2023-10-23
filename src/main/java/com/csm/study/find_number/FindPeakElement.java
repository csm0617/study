package com.csm.study.find_number;

public class FindPeakElement {

    //------------------------------------------------------------------------------------------------
    // 峰值元素是指其值严格大于左右相邻值的元素
    // 给你一个整数数组 nums，已知任何两个相邻的值都不相等
    // 找到峰值元素并返回其索引
    // 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    // 你可以假设 nums[-1] = nums[n] = 无穷小
    // 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    // 测试链接 : https://leetcode.cn/problems/find-peak-element/
    // eg:[2 3 5 8 9 7] eg:[8 6 7 5 9 3]
    //------------------------------------------------------------------------------------------------

    class Solution {
        public int findPeakElement(int[] arr) {
            //题意是1.数组的超出左右端点的位置都是无穷小   nums[-1] = nums[n] = 无穷小
            //2.相邻的任意两个数不相等
            //3.只需找出一个峰值返回即可

            //当数组只有一个元素时，就是峰值
            int n = arr.length;
            if (n == 1) {
                return 0;
            }
            //当数组长度>=2时
            //单独验证arr[0]是不是峰值
            if (arr[0] > arr[1]) {
                return 0;
            }
            //单独验证[n-1]是不是峰值
            if (arr[n - 1] > arr[n - 2]) {
                return n - 1;
            }
            // 当数组长度>=2,如果0和n-1都不是峰值说明 arr[0]<arr[1]  arr[n-2]>arr[n-1]
            // 左边0-1是递增的，右边是n-2到n-1递减的，
            // 根据罗尔定理中间必有极大值，也就是峰值
            //----------------------------------二分查找---------------------------
            //左右端点下标，中间位置值，找到返回下标
//        int l = 0, r = arr.length - 1, m = 0, ans = -1;
            //错了错了，刚刚已经排除了0和n-1位置是峰值点，所以二分查找峰值点的区间应该是在1~~n-2上
            int l = 1, r = arr.length - 2, m = 0, ans = -1;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                //如果二分的中间值arr[m-1]<arr[m]>arr[m+1]
                //说明中值就是峰值直接break
                //如果arr[m-1]>arr[m],出现下降说明m的左侧一定存在峰值
                if (arr[m - 1] > arr[m]) {
                    r = m - 1;
                    //如果arr[m+1]>arr[m],出现下降说明m的右侧一定存在峰值
                } else if (arr[m] < arr[m + 1]) {
                    l = m + 1;
                } else {
                    ans = m;
                    break;
                }
            }
            return ans;


        }


        //（写不了对数器，因为峰值可能不止1个）
        //找到第一个峰值
        public int validator(int[] arr){
            //当数组只有一个元素时，就是峰值

            int n = arr.length;
            if (n == 1) {
                return 0;
            }
            //当数组长度>=2时
            //单独验证arr[0]是不是峰值
            if (arr[0] > arr[1]) {
                return 0;
            }
            //单独验证[n-1]是不是峰值
            if (arr[n - 1] > arr[n - 2]) {
                return n - 1;
            }
            //排除边界条件后，直接遍历找峰值
            for (int i = 1; i < arr.length-2; i++) {
                if (arr[i]>arr[i-1]&&arr[i]<arr[i+1]){
                    return i;
                }
            }
            //找不到返回-1
            return -1;
        }

    }

}
