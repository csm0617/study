package com.csm.study.datastructure.queue;

import com.csm.study.datastructure.queue.structure.Queue;

import java.util.Iterator;

/**
 * 数组实现环形队列（head和tail只计数,不作为索引来用）
 *
 * @param <E>
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int head = 0;//头指针
    private int tail = 0;//尾指针
    //-------------------------------------基于上次代码提交的改进------------------------------------

        /*
            上个版本的head++和tail++只用来计数会超过Int的的最大值溢出，
            但是怎么能让在溢出tail%arr.length的情况下还能继续找到索引呢
        */

    /*
     * 求模运算
     * 如果除数是2的n次方
     * 那么被除数的后n位即为余数(模)
     * 求被除数的后n位的方法，与2^n-1位与
     * 15 % 4    15 二进制：1111   4：2^2
     * 所以15 % 4 == 1111 & 0011
     * 0011 == 3
     * */

    /*
     * 在ArrayQueue3的基础上为了避免tail或者head++发生溢出
     * 所以求环形队列索引 tail % arr.length 可以转化为 tail & (arr.length-1) 来求
     * 但前提是 arr.length 也就是数组的容量必须是 2的n次方
     * */
    //-------------------------------------基于上次代码提交的改进------------------------------------
//
//    /**
//     * 方法1
//     * 基于以上说明，数组的容量必须是2^n
//     * @param capacity
//     */
//    @SuppressWarnings("all")
//    public ArrayQueue3(int capacity) {
//        if ((capacity&capacity-1)!=0){
//            throw new IllegalArgumentException("传入的数组容量必须是2^n");
//        }
//        array = (E[]) new Object[capacity];
//    }

    /**
     * 方法2
     * 基于以上说明，数组的容量必须是2^n
     * 将传入的capacity改为离他最近的大的一个2^n
     *
     * @param capacity
     */
    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        //求capacity的log以2为底的对数(换底公式)   log2()
        int n = (int) (Math.log10(capacity - 1) / Math.log10(2)) + 1;
        //右移n为
        capacity = 1 << n;
        array = (E[]) new Object[capacity];
    }

    /**
     *  head和tail都只用来计数,不作为索引，真正作为索引的是head%array.length
     *  eg: arr.length =3
     *  [ 0 1 2 ]
     *  //继续加tail=3,明显越界了，用tail%arr.length来作为索引
     *  3%3=0  所以需要更新的是 arr[0]
     */

    /**
     * 向队列尾插入值
     *
     * @param value 待插入值
     * @return 插入成功返回true，插入失败返回false
     */
    @Override
    public boolean offer(E value) {
        //先判断队列是否满了
        if (isFull()) {
            return false;
        }
        //在尾指针处添加
        array[tail & (array.length - 1)] = value;
        //更新尾指针指向
        tail++;
        return true;
    }

    /**
     * 从队列头获取值，并移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E poll() {
        //先判断队列为不为空
        if (isEmpty()) {
            return null;
        }
        //不为空，拿到队头元素
        E value = array[head & (array.length - 1)];
        //再更新head指针
        head++;
        return value;
    }

    /**
     * 从队列头获取值，不移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E peek() {
        //先判断队列是否为空
        if (isEmpty()) {
            return null;
        }
        //不为空返回队头
        return array[head & (array.length - 1)];
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回ture, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 检查队列是否满了
     *
     * @return 满了返回true, 没满返回false
     */
    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p & (array.length - 1)];
                p++;
                return value;
            }
        };
    }
}
