package com.csm.study.queue;

import com.csm.study.datastructure.queue.ArrayQueue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class TestArrayQueue {
    @Test
    @DisplayName("测试环形数组队列添加")
    public void testOffer(){
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    @DisplayName("测试环形数组队列添加")
    public void testPoll(){
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        queue.poll();
        queue.poll();
        System.out.println(queue.isEmpty());
    }

    @Test
    @DisplayName("测试环形数组队列添加")
    public void testPeek(){
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println("======");
        System.out.println(queue.peek());
        System.out.println("======");
        queue.peek();
        queue.peek();
        for (Integer integer : queue) {
            System.out.printf("%-4d",integer);
        }
    }


}
