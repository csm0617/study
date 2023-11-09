package com.csm.study.priority_queue;

import com.csm.study.datastructure.queue.priority_queue.Entry;
import com.csm.study.datastructure.queue.priority_queue.PriorityQueue1;
import org.junit.jupiter.api.Test;

public class TestPriorityQueue1 {
    @Test
    public void offer(){
        PriorityQueue1<Entry> queue1 = new PriorityQueue1<Entry>(5);
        queue1.offer(new Entry("task1",4));
        queue1.offer(new Entry("task2",5));
        queue1.offer(new Entry("task3",2));
        queue1.offer(new Entry("task4",1));
        queue1.offer(new Entry("task1",3));
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());

    }
}
