package com.csm.study.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ListTests {

    /**
     * 测试头插法节点插入
     */

    @Test
    public void testFirst(){
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

    /**
     * 测试尾插法节点加入
     */

    @Test
    public void testLast(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.loop2(value->{
            System.out.println(value);
        });
    }

    /**
     * 测试通过索引获取值
     */
    @Test
    public void testGet(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        System.out.println(list.get(3));
        System.out.println(list.get(5));
    }

}
