package com.csm.study.datastructure.queue.blocking_queue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SingleLockBlockingQueue<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public SingleLockBlockingQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        return "BlockingQueue1{" + Arrays.toString(array) + '}';
    }

    private ReentrantLock lock = new ReentrantLock();//锁对象
    private Condition headWaits = lock.newCondition();//条件变量，配合poll()
    private Condition tailWaits = lock.newCondition();//条件变量，配合offer()

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {//可能存在poll线程在等待队列非空
        lock.lockInterruptibly();//加锁
        try {
            //队列满了就阻塞
            while (isFull()) { //while循环代 替if判断，防止虚假唤醒
                tailWaits.await();
            }
            //往队尾添加元素
            array[tail] = e;
            //更新tail指针
            if (++tail == array.length) {//超出了数组长度就归0
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒等待队列非空的poll线程
        } finally {
            lock.unlock();//解锁
        }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();//加锁
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            //队列满了就阻塞
            while (isFull()) { //while循环代 替if判断，防止虚假唤醒
                if (t <= 0) {//超时了还没唤醒就返回false
                    return false;
                }
                t = tailWaits.awaitNanos(t);//返回剩余等待唤醒时间并更新t
            }
            //往队尾添加元素
            array[tail] = e;
            //更新tail指针
            if (++tail == array.length) {//超出了数组长度就归0
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒等待队列非空的poll线程
            return true;
        } finally {
            lock.unlock();//解锁
        }
    }

    @Override
    public E poll() throws InterruptedException {//可能存在等待队列不为满的offer线程
        lock.lockInterruptibly();//加锁
        try {
            while (isEmpty()) {
                headWaits.wait();
            }
            E e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailWaits.signal();//唤醒正在等待添加的offer线程
            return e;
        } finally {
            lock.unlock();
        }
    }
}
