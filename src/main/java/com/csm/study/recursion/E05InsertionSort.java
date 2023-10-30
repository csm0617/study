package com.csm.study.recursion;

public class E05InsertionSort {
    public static void sort(int[] arr) {
        //只有一个元素时默认已经排好序
        insertion(arr, 1);
    }


    /**
     * @param arr 待排序数组
     * @param low 指向未排序区域的最左指针
     */
    private static void insertion(int[] arr, int low) {
        //1.结束条件：当low指针从0位置往右一直走向超出数组，也就是数组没有元素的时候
        //说明已经全部排好序了，直接return
        if (low == arr.length) {
            return;
        }
        //3.递归部分
        //定义一个i指向已排序部分的最右边端（未排序区域最左端low-1的位置）
        int i = low - 1;
        //定义一个变量来存放即将排序的arr[low]
        int t = arr[low];
        //从已排序的部分i的位置从右往左找，找到第一个比t小的数说明已经找到插入的位置了
        while (i >= 0 && arr[i] > t) {// i在左移过程中要>=0保障数组不越界
            //---------------why i>0 ?示例--------------
            //eg:[ 2 5 6 7 8 9 1]
            //此时low=6 ,arr[low]=1 ,i=low-1=5 arr[i]=9
            //当i--，i来到0,arr[i]=2时 > t,此时i还要左移，此时数组就要越界了，
            //设置while中的循环条件i>=0 ，跳出循环时i==-1，那么执行赋值arr[i+1]=arr[0]=t 刚好满足t插入的位置是0,插入正确
            //[1 2 5 6 7 8 9]
            //------------------------------------------
            //没找到
            //那么将a[i]往后移一个位置，给t腾出插入位置
            arr[i + 1] = arr[i];
            //i继续向左移动
            i--;
        }
        //跳出while循环后，此时arr[i]<t,那么i+1的位置就是arr[low]的插入位置
        if (i + 1 != low){//eg：[2 5 6] 插入6时5已经比6小了不会进入到while中这是i+1=low没必要再进行一次赋值操作
            arr[i + 1] = t;
        }
        //2.继续递归调用
        insertion(arr, low + 1);
    }
}
