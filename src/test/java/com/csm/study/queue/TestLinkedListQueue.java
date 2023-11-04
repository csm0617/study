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
}
