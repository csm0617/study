package com.csm.study.blocking_queue;

import com.csm.study.datastructure.queue.blocking_queue.SingleLockBlockingQueue;

public class TestSingleLockBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        SingleLockBlockingQueue<String> queue = new SingleLockBlockingQueue<>(3);
        Thread t1 = new Thread(() -> {

            try {
                System.out.println(System.currentTimeMillis() + " begin");
                queue.offer("任务1");
                System.out.println(queue);
                queue.offer("任务2");
                System.out.println(queue);
                queue.offer("任务3");
                System.out.println(queue);
                queue.offer("任务4", 5000);
                System.out.println(queue);
                System.out.println(System.currentTimeMillis() + " end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "生产者");
        t1.start();

        Thread.sleep(2000);
        queue.poll();
    }
}
