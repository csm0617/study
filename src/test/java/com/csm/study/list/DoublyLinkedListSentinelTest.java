package com.csm.study.list;

import com.csm.study.datastructure.list.DoublyLinkedListSentinel;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListSentinelTest {
    @Test
    void addFirst() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    @Test
    void removeFirst() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeFirst();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeFirst();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeFirst();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeFirst();
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    void addLast(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    void removeLast(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        System.out.println("=================");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("=================");
        list.removeLast();
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
    @Test
    void insert(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("===================");
        list.insert(2,666);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("===================");
        list.insert(0,111);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("===================");
        list.insert(6,777);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    void remove(){
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println("===============");
        list.remove(4);
        for (Integer integer : list) {
            System.out.println(list);
        }
//        System.out.println("===============");
//        list.remove(0);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//        System.out.println("===============");
//        list.remove(-1);
//        for (Integer integer : list) {
//            System.out.println(list);
//        }
        System.out.println("===============");
        list.remove(3);
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
}
