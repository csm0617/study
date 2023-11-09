package com.csm.study.priority_queue;

import com.csm.study.datastructure.queue.priority_queue.Entry;
import com.csm.study.datastructure.queue.priority_queue.PriorityQueue2;
import com.csm.study.datastructure.queue.priority_queue.PriorityQueue4;
import org.junit.jupiter.api.Test;

public class TestPriorityQueue4 {
    @Test
    public void offer(){
        PriorityQueue4<Entry> queue = new PriorityQueue4<>(5);
        queue.offer(new Entry("task1",4));
        queue.offer(new Entry("task2",5));
        queue.offer(new Entry("task3",2));
        queue.offer(new Entry("task4",1));
        queue.offer(new Entry("task5",3));
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }
}
