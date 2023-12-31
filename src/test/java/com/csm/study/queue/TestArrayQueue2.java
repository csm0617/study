package com.csm.study.queue;

import com.csm.study.datastructure.queue.ArrayQueue2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestArrayQueue2 {
    @Test
    @DisplayName("测试环形数组队列添加")
    public void testOffer(){
        ArrayQueue2<Integer> queue = new ArrayQueue2<Integer>(3);
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
        ArrayQueue2<Integer> queue = new ArrayQueue2<Integer>(3);
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
        ArrayQueue2<Integer> queue = new ArrayQueue2<Integer>(3);
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
