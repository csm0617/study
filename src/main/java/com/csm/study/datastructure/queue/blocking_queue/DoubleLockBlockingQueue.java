package com.csm.study.datastructure.queue.blocking_queue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁阻塞队列offer和poll用的都是同一把锁，出队和入队不能并发执行
 * 让出队和入队各拥有一把锁
 * @param <E>
 */
public class DoubleLockBlockingQueue<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public DoubleLockBlockingQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        return "BlockingQueue1{" + Arrays.toString(array) + '}';
    }

    private ReentrantLock tailLock = new ReentrantLock();//保护tail指针，不被其他的offer线程影响
    private Condition tailWaits = tailLock.newCondition();//条件变量，配合offer()，当队列满时，在tailWaits等待
    private ReentrantLock headLock = new ReentrantLock();//保护head指针，不被其他的poll线程影响
    private Condition headWaits = headLock.newCondition();//条件变量，配合poll()，当队列为空的时候在headWaits等待

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {//可能存在poll线程在等待队列非空
        tailLock.lockInterruptibly();//加锁
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
            tailLock.unlock();//解锁
        }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        tailLock.lockInterruptibly();//加锁
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
            tailLock.unlock();//解锁
        }
    }

    @Override
    public E poll() throws InterruptedException {//可能存在等待队列不为满的offer线程
        headLock.lockInterruptibly();//加锁
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
            headLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DoubleLockBlockingQueue<String> queue = new DoubleLockBlockingQueue<String>(3);
        queue.offer("任务1");
        new Thread(()->{
            try {
                queue.offer("任务二");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"offer").start();
        new Thread(()->{
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"poll").start();
    }

}
