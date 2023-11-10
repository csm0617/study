package com.csm.study.datastructure.queue.blocking_queue;

/**
 * 阻塞队列接口方法定义
 * @param <E>
 */
public interface BlockingQueue<E> {
    void offer(E e) throws InterruptedException;
    boolean offer(E e,long timeout) throws InterruptedException;
    E poll() throws InterruptedException;
}
