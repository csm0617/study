package com.csm.study.datastructure.deque;

import java.util.Iterator;

/**
 * 基于循环数组实现双端队列
 *
 * @param <E> 队列中元素的类型
 * @ tail停下来的位置不会存储值，会浪费一个位置
 */
public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {
    /*
        h - head
        t - tail
        刚开始h和t都指向0
                t   h
        0   1   2   3
        a   b       c

        offerLast(a)    先添加元素  再tail++
        offerLast(b)
        offerFirst(c)   先head-- ,再添加元素(不移动ab，让head 指向 a 的前一个位置，因为是环形数组，只需要head换算后保证索引有效索引)

        pollFirst()     先获取要移除的值，再head++
        pollLast()      先tail-- 再获取要移除的值

        当head == tail时 为空

        当head~tail的距离是 数组的长度-1时 数组满了(浪费一个位置不存储数据)

     */


    /*
    eg:
    初始状态：
                h
                t
        0   1   2   3
     向尾部插入元素a  先offerLast(a) 再tail++ :
                h
                    t
        0   1   2   3
                a
     再向尾部插入元素b 先offerLast(a) 再tail++ :
              h
        t
        0   1   2   3
                a   b
     */

    /**
     * 求+1之后的正确索引
     *
     * @param i      当前指针
     * @param length 数组的长度
     * @return 正确的索引
     */
    static int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    /*
    eg:
    初始状态：
            h
            t
        0   1   2   3
     向头部插入元素a  先head--   再offerFirst(a) :
        h
            t
        0   1   2   3
        a
     再向头部插入元素b 先head--   再offerFirst(a) :
                    h
            t
        0   1   2   3
        a           b
     */

    /**
     * 求-1之后的正确索引
     *
     * @param i      当前的指针
     * @param length 数组长度
     * @return 正确的索引
     */
    static int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }

    E[] array;
    int head;
    int tail;

    @SuppressWarnings("all")
    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity + 1];//因为tail会浪费一个空间所以数组在实际创建的时候多预留一个位置；
    }

    /**
     * 从双端队列的队头插入
     *
     * @param value 待插入元素
     * @return 插入成功返回true, 失败返回false
     */
    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        //先--找到正确索引再赋值
        head = dec(head, array.length);
        array[head] = value;
        return true;
    }

    /**
     * 从双端队列的队尾插入
     *
     * @param value 待插入元素
     * @return 插入成功返回true, 失败返回false
     */
    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        //先赋值，再++更新下一个tail
        array[tail] = value;
        tail = inc(tail, array.length);
        return false;
    }

    /**
     * 从双端队列的队头获取元素，并移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        //先获取要移除元素的值，再++
        E value = array[head];
        array[head] = null;//因为是引用类型的数组，所以移除元素后要手动释放
        head = inc(head, array.length);
        return value;
    }

    /**
     * 从双端队列的队头获取元素，并移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        //先--，再获取要移除元素的值
        tail = dec(tail, array.length);
        E value = array[tail];
        array[tail] = null;//因为是引用类型的数组，所以移除元素后要手动释放
        return value;
    }

    /**
     * 从双端队列的队头获取元素，不移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E peekFirst() {
        return array[head];
    }

    /**
     * 从双端队列的队尾获取值，不移除
     *
     * @return 双端队列的队头元素
     */
    @Override
    public E peekLast() {
        return array[dec(tail--, array.length)];
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空返回true, 不为空返回false
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /*
    ----第一种情况，刚开始head和tail都是0，向队列的尾部添加3个元素后队列满了(尾部添加tail是先添加元素再++移动位置)
        h
                    t
        0   1   2   3
        a   b   c
        tail > head
        tail - head = length - 1
    ----第二种情况，刚开始head和tail都是0,向队列的头部添加3个元素后队列满了(头部添加head是先--移动位置再添加元素)
            h
        t
        0   1   2   3
           c    b    a
        head > tail
        tail - head = length - 1
     */

    /**
     * 判断队列是否满了
     *
     * @return 满了返回true, 没满返回false
     */
    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == array.length - 1;
        } else if (head > tail) {
            return head - tail == 1;
        }
        return false;
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
                E value = array[p];
                p = inc(p, array.length);
                return value;
            }
        };
    }
}
