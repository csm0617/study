package com.csm.study.deque;

import com.csm.study.datastructure.queue.deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

public class TestLinkedListDeque {
    @Test
    public void offer(){
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerLast(3);
        deque.offerLast(4);
        deque.offerFirst(5);
        System.out.println(deque.isFull());
        deque.offerLast(6);
        deque.offerFirst(7);
        for (Integer integer : deque) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    public void poll(){
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerLast(3);
        deque.offerLast(4);
        deque.offerFirst(5);
        System.out.println(deque.pollFirst());
        for (Integer integer : deque) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque.pollLast());
        for (Integer integer : deque) {
            System.out.printf("%-4d",integer);
        }
    }

    @Test
    public void peek(){
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerLast(3);
        deque.offerLast(4);
        deque.offerFirst(5);
        for (Integer integer : deque) {
            System.out.printf("%-4d",integer);
        }
        System.out.println();
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        System.out.println(deque.peekLast());
        for (Integer integer : deque) {
            System.out.printf("%-4d",integer);
        }
    }
}
