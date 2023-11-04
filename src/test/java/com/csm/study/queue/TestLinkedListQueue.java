package com.csm.study.queue;

import com.csm.study.datastructure.queue.LinkedListQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestLinkedListQueue {
    @Test
    @DisplayName("队列尾部加入")
    public void testOffer(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    @DisplayName("移除队列的头元素")
    public void poll(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Integer remove = queue.poll();
        System.out.println(remove);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    @DisplayName("获取队头元素")
    public void peek(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Integer first = queue.peek();
        System.out.println(first);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
    }
    @Test
    @DisplayName("队列是否满了")
    public void isFull(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }

    }
}
