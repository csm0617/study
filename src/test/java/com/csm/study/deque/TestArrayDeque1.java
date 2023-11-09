package com.csm.study.deque;

import com.csm.study.datastructure.queue.deque.ArrayDeque1;
import org.junit.jupiter.api.Test;

public class TestArrayDeque1 {
    @Test
    public void offer(){
        ArrayDeque1<Integer> deque1 = new ArrayDeque1<Integer>(3);
        deque1.offerFirst(1);
        deque1.offerFirst(2);
        deque1.offerLast(3);
        //213
        System.out.println(deque1.offerFirst(4));
        for (Integer integer : deque1) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    public void poll(){
        ArrayDeque1<Integer> deque1 = new ArrayDeque1<>(7);
        deque1.offerLast(1);
        deque1.offerLast(2);
        deque1.offerLast(3);
        deque1.offerFirst(4);
        deque1.offerFirst(5);
        deque1.offerFirst(6);
        deque1.offerFirst(7);
        //7654123
        System.out.println(deque1.isFull());
        for (Integer integer : deque1) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque1.pollFirst());
        System.out.println(deque1.pollFirst());
        System.out.println(deque1.pollFirst());
        System.out.println(deque1.pollFirst());
        for (Integer integer : deque1) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque1.pollLast());
        System.out.println(deque1.pollLast());
        System.out.println(deque1.pollLast());
        System.out.println(deque1.isEmpty());
    }
    @Test
    public void peek(){
        ArrayDeque1<Integer> deque1 = new ArrayDeque1<>(7);
        deque1.offerLast(1);
        deque1.offerLast(2);
        deque1.offerLast(3);
        deque1.offerFirst(4);
        deque1.offerFirst(5);
        deque1.offerFirst(6);
        deque1.offerFirst(7);
        //7654123
        System.out.println(deque1.isFull());
        for (Integer integer : deque1) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque1.peekFirst());
        System.out.println(deque1.pollFirst());
        System.out.println(deque1.peekFirst());
        System.out.println(deque1.peekLast());
        for (Integer integer : deque1) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque1.peekLast());
        System.out.println(deque1.pollLast());
        System.out.println(deque1.peekLast());
        System.out.println(deque1.isEmpty());
    }
}
