package com.csm.study.priority_queue;

import com.csm.study.datastructure.queue.priority_queue.Entry;
import com.csm.study.datastructure.queue.priority_queue.PriorityQueue2;
import org.junit.jupiter.api.Test;

public class TestPriorityQueue2 {
    @Test
    public void offer(){
        PriorityQueue2<Entry> queue1 = new PriorityQueue2<Entry>(5);
        queue1.offer(new Entry("task1",4));
        queue1.offer(new Entry("task2",5));
        queue1.offer(new Entry("task3",2));
        queue1.offer(new Entry("task4",1));
        queue1.offer(new Entry("task5",3));
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());

    }
}
