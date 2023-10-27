package com.csm.study.list;

import org.junit.jupiter.api.Test;

public class DoublyCircleLinkedListSentinelTest {
    @Test
    void addFirst(){
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }


}
