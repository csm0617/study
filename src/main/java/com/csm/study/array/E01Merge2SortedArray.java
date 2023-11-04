package com.csm.study.array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 */
public class E01Merge2SortedArray {

    /**
     * 方法一：递归
     * 递归实现合并两个有序数组，这里吧两个有序数组合并到一个数组里的只是划分的区间不同
     * @param a1  原始数组
     * @param i   第一个有序区间的起始位置
     * @param iEnd 第一个有序区间的结束位置
     * @param j    第二个有序区间的开始位置
     * @param jEnd 第二个有序区间的结束位置
     * @param a2  新数组
     * @param k   新数组的末尾位置（因为合并是在新数组的末尾添加）
     */

    public static void merge(int[] a1, int i, int iEnd,
                             int j, int jEnd,
                             int[] a2, int k) {
        //结束递归的条件，有序区间的长度不同，比较的过程中肯定有一个先结束
        //如果当i已经走到iEnd了，说明第一个有序区间 先 结束了
        if (i > iEnd) {
            //需要把第二个有序区间剩余的元素直接copy到新数组的末尾
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
            //记得return，做递归的题的时候，递归结束一定记得return否则代码会继续向下执行
            return;
        }
        //同理当j已经走到jEnd了，说明第二个有序区间 先 结束了
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
            return;
        }
        //把两个有序数组的元素对比，小的加入新数组的末尾
        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            //小的数组指针后移，大的指针不变，继续递归
            merge(a1, i + 1, iEnd, j, jEnd, a2, k + 1);
        } else {
            a2[k] = a1[j];
            merge(a1, i, iEnd, j + 1, jEnd, a2, k + 1);
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 2, 5, 6, 8};
        int[] a2 = new int[a1.length];
        merge(a1, 0, 3, 4, a1.length - 1, a2, 0);
        Arrays.stream(a2).forEach(System.out::print);
    }
}
