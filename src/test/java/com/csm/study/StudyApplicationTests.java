package com.csm.study;

import com.csm.study.datastructure.list.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyApplicationTests {

    @Test
    public void testList(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        System.out.println("-----------while+lambada遍历--------------------");
        list.loop1(value->{
            System.out.println(value);
        });
        System.out.println("-----------for+lambada遍历--------------------");
        list.loop2(value->{
            System.out.println(value);
        });
        System.out.println("-----------iterator遍历--------------------");
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
