package com.csm.study.list;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表
 */
public class SinglyLinkedList implements Iterable<Integer> {//整体

    /**
     * 当一个结构中整体和部分时包含关系，一般采用内部类的方式去定义内部，通常用private来修饰避免对外暴露
     */

    private Node head = null;//（头指针），（头节点），（不放数据的哑元节点），（哨兵节点）

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head;

            @Override
            public boolean hasNext() {//是否有下一个元素,为false时，停止迭代
                return p != null;
            }

            @Override
            public Integer next() {//返回当前值，并指向下一个元素
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }


    /**
     * 节点类
     */
    private static class Node {//部分
        int value;
        //java中指针就是引用
        Node next;

        //给构造方法
        public Node(int value, Node node) {
            this.value = value;
            this.next = node;
        }

    }


    //------------------------------------头插法添加节点------------------------------------
    public void addFirst(int value) {
        //1.当链表为空head->node1
        //头指针指向新建的节点
//        head = new Node(value, null);
        //2.当链表不为空时
        //将新建的节点的next指向头指针指向的位置，再将head指向node2下面一句话做了两件事
        //node2->node1, head->node2->node1
        //--------------------------------
        //head刚开始就为null，所以当链表为空时也适用
        head = new Node(value, head);
    }

    //------------------------------------头插法添加节点------------------------------------


    //------------------------------------尾插法添加节点------------------------------------
    //1.先找到最后一个节点
    private Node findLast() {
        //如果链表为空直接返回null
        if (head == null) {
            return null;
        }
        Node p = null;
        //如果链表不为空，找到最后一个节点的指针返回
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    //2.在最后一个节点上加入新元素
    public void addLast(int value) {
        Node last = findLast();
        //如果last==null，也就是说链表为空
        if (last == null) {
            //用头插法插入第一个元素即可
            addFirst(value);
            //插完后直接return否则会继续执行下面的代码插入两个相同元素
            return;
        }
        //将最后一个元素的next指针指向新加的节点
        last.next = new Node(value, null);
    }


    //------------------------------------尾插法添加节点------------------------------------


    //start---------------------------------------遍历链表的三种方式--------------------------------------------

    /**
     * 遍历链表
     */
    public void loop() {
        Node p;//定义遍历指针
        //将头指针指向head指向的位置
        p = head;
        //遍历条件
        while (p != null) {
            System.out.println(p.value);
            //更新p指针，指向下一个节点，直到链表尾部，p为空
            p = p.next;
        }
    }

    //start---------------------------遍历打印链表lambada改进版--------------------------

    /**
     * 遍历链表
     */
    public void loop1(Consumer<Integer> consumer) {
        Node p;//定义遍历指针
        //将头指针指向head指向的位置
        p = head;
        //遍历条件
        while (p != null) {
            consumer.accept(p.value);
            //更新p指针，指向下一个节点，直到链表尾部，p为空
            p = p.next;
        }
    }

    //end---------------------------遍历for循环打印链表lambada改进版--------------------------

    /**
     * 遍历链表
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    //end---------------------------------------遍历链表的三种方式--------------------------------------------

    //  ------------start-------------    获取索引为i的的节点的值   ---------------------------

    /**
     * 找到索引为i的节点
     *
     * @param index
     * @return ！！！这里补充一下，为什么不把索引存放在节点中，
     * 因为链表节点在内存中不是连续的，
     * 如果插入或者删除某一节点还要修改保存的索引，那么将会很麻烦
     */
    private Node findNode(int index) {
        int i = 0;
        //for循环中的初始条件可以时多个的情况只能是同一种类型，否则可以将i=0定义在循环中，但是迭代条件可以是多个
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    /**
     * 获取索引为i的节点的值
     *
     * @param index
     * @return
     */
    public int get(int index) {
        Node node = findNode(index);
        //没找到就抛异常
        if (node == null) {
            illegalIndex(index);
        }
        return node.value;

    }

    private static void illegalIndex(int index) {
        throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    //  ------------end-------------    获取索引为i的的节点   ---------------------------

    //--------------start-------------     向索引位置添加节点    ----------------------------

    /**
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        //直接index-1有问题，如果index是0，应该是插入到头节点的后面
        if (index == 0) {
            addFirst(value);
            //别忘了return否则还要继续执行下面的代码
            return;
        }

        //找到插入位置的前一个节点
        Node pre = findNode(index - 1);

        //如果前一个位置的节点为空，抛出索引异常
        if (pre == null) {
            illegalIndex(index);
        }
//        //找到了就将pre.next指针指向的位置挂到新加的节点上
//        Node node = new Node(value, pre.next);
//        //再将pre.next指向新加的node
//        pre.next=node;
        pre.next = new Node(value, pre.next);
    }

    //--------------end-------------     向索引位置添加节点    ----------------------------


    //--------------start-------------     删除链表的第一个节点    ----------------------------

    /**
     * 删除链表的第一个节点，不需要传参和返回值
     */
    public void removeFirst() {
        //如果链表为空的情况就抛异常
        if (head == null) {
            illegalIndex(0);
        }
        //否则将head指向head.next（原本头节点head指向的就是第一个节点，head.next指向的是第二个节点）
        head = head.next;
    }
    //--------------end-------------     删除链表的第一个节点     ----------------------------


}
