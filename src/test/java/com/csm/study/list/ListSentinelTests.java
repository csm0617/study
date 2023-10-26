package com.csm.study.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ListSentinelTests {

    /**
     * 测试头插法节点插入
     */

    @Test
    public void testFirst(){
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
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
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
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
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(3));
//        System.out.println(list.get(5));

    }

    @Test
    @DisplayName("在索引位置插入")
    public void testInsert(){
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.insert(4,5);
        //特殊情况头插法
        list.insert(0,0);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    @DisplayName("测试RemoveFirst")
    public void testRemoveFirst(){
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        System.out.println("==========before remove===========");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.removeFirst();
        System.out.println("==========after remove===========");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.removeFirst();
        System.out.println("=================================");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.removeFirst();
        System.out.println("=================================");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.removeFirst();
        System.out.println("=================================");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        //此时链表为空，再调用removeFirst，会抛异常
        list.removeFirst();
        System.out.println("=================================");
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }


    @Test
    @DisplayName("测试remove")
    public void testRemove(){
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
//        list.remove(0);

        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.remove(3);
        System.out.println("======================");
        for (Integer integer : list) {
            System.out.println(integer);
        }

        list.remove(0);
    }

    @Test
    @DisplayName("测试remove2")
    public void testRemove2(){
        SinglyLinkedSentinel list = new SinglyLinkedSentinel();
        list.addLast(1);//调用addFirst方法
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.remove2(3);

        for (Integer integer : list) {
            System.out.println(integer);
        }
        list.remove(0);
        System.out.println("======================");
        for (Integer integer : list) {
            System.out.println(integer);
        }

//        list.remove(6);
    }

}
