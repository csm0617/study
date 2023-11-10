package com.csm.study.datastructure.queue.blocking_queue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
    private AtomicInteger size;//为了防止指令交错,把size改为原子整数类

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
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
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
            /*
                size++做了三件事
                1.先读取成员变量size的值 3
                2.自增 4
                3.结果写回到成员变量size 4
                因为现在offer和poll是各自拥有一把锁，是并发执行的可能存在指令交错
                在offer线程读到size为3，此时poll线程执行了，size本应该变为2，
                但是offer线程结束后会在读到3的基础上+1变成4
                为了解决这个问题将size改为原子类型
             */
            size.getAndIncrement();//size++
            //唤醒正在等待添加的poll线程，需要先获得headLock锁，(headWaits必须和它的锁一起使用否则会报错)
            headLock.lock();
            try {
                headWaits.signal();
            }finally {
                headLock.unlock();
            }
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
            /*
                size++做了三件事
                1.先读取成员变量size的值 3
                2.自增 4
                3.结果写回到成员变量size 4
                因为现在offer和poll是各自拥有一把锁，是并发执行的可能存在指令交错
                在offer线程读到size为3，此时poll线程执行了，size本应该变为2，
                但是offer线程结束后会在读到3的基础上+1变成4
                为了解决这个问题将size改为原子类型
             */
            size.getAndIncrement();//size++
            //唤醒正在等待添加的poll线程，需要先获得headLock锁，(headWaits必须和它的锁一起使用否则会报错)
            try {
                headWaits.signal();
            }finally {
                headLock.unlock();
            }
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
            size.getAndDecrement();//size--
            //唤醒正在等待添加的offer线程，需要先获得tailLock锁，(tailWaits必须和它的锁一起使用否则会报错)
            tailLock.lock();
            try {
                tailWaits.signal();
            }finally {
                tailLock.unlock();
            }
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
