package com.csm.study.datastructure.queue.blocking_queue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁阻塞队列offer和poll用的都是同一把锁，出队和入队不能并发执行
 * 让出队和入队各拥有一把锁
 *
 * @param <E>
 */
public class DoubleLockBlockingQueue<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();//为了防止指令交错,把size改为原子整数类

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
        int c;//记录添加前元素的个数
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
            c = size.getAndIncrement();//size++，后加加，c先记录添加前的元素个数，size再+1
            //如果添加一个元素，以后还有位置可以添加，就由当前offer线程去唤醒其他的offer线程
            if (c + 1 < array.length) {
                tailWaits.signal();
            }
        } finally {
            tailLock.unlock();//解锁
        }
        /*
        背景--双端阻塞队列性能问题：
           虽然上一个版本解决了死锁问题，但是还存在性能问题，例如
               假设队列此时为空，当有三个offer线程offer1,offer2,offer3和三个poll线程时poll1,poll2,poll3
               offer线程结束会唤醒其中一个poll线程，但是要唤醒就先必须拿到headLock锁，所以会导致其他所有的poll线程全部阻塞
               导致出现性能问题
               为了解决这个问题
               0->1                 1->2                 2->3
               offer1               offer2               offer3
               记录从元素个数0->1的 这个线程获得headLock锁去唤醒poll线程    （其他offer线程在元素个数不为0的时候不再获得headLock锁）
               当其中一个poll线程获得锁后，取走队头元素，判断取走之前元素个数 如果元素个数 >1 说明还有富足得元素可以被取走
               那么剩下的线程由当前线程唤醒： poll1    ->    poll2   ->       poll3
                                  被offer线程唤醒    由poll1级联通知     由poll2级联通知
         */
        if (c == 0) { //只让 从元素个数0->1的 这个线程获得锁
            //唤醒正在等待添加的poll线程，需要先获得headLock锁，(headWaits必须和它的锁一起使用否则会报错)
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
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
        } finally {
            tailLock.unlock();//解锁
        }
        //唤醒正在等待添加的poll线程，需要先获得headLock锁，(headWaits必须和它的锁一起使用否则会报错)
        try {
            headWaits.signal();
        } finally {
            headLock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {//可能存在等待队列不为满的offer线程
        int c;//记录取走前元素的个数
        headLock.lockInterruptibly();//加锁
        E e;
        try {
            while (isEmpty()) {
                headWaits.wait();
            }
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            c = size.getAndDecrement();//size--，后减减，先记录取走之前元素的个数，再size-1
            //有多的元素可以取走，就唤醒其他的poll线程
            if (c > 1) {
                headWaits.signal();
            }

        } finally {
            headLock.unlock();
        }
        //唤醒正在等待添加的offer线程，需要先获得tailLock锁，(tailWaits必须和它的锁一起使用否则会报错)
        //满  -> 不为满
        /*
            和offer的背景介绍相同，只由队列从满到不满的poll线程获得tailLock锁,再唤醒offer线程
            （其他的offer线程，根据是否有足够的空间来添加来判断是否被唤醒的offer线程级联通知唤醒）
                   offer1     ->        offer2         ->          offer3
               被poll线程唤醒           offer1级联通知             offer2级联通知
         */
        if (c == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    public static void main(String[] args) throws InterruptedException {
        DoubleLockBlockingQueue<String> queue = new DoubleLockBlockingQueue<String>(3);
        queue.offer("任务1");

        new Thread(() -> {
            try {
                System.out.println("poll_1: " + queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll_1").start();
        new Thread(() -> {
            try {
                queue.offer("元素");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer");
    }

}
