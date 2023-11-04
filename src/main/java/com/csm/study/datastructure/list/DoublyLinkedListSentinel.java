package com.csm.study.datastructure.list;

import java.util.Iterator;

/**
 * 双向链表（带哨兵）
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {

    static class Node {
        Node prev;//上一个节点指针
        int value;//值
        Node next;//指向下一个节点指针

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node head;//头部哨兵
    private Node tail;//尾部哨兵

    //初始化双链表,头尾哨兵
    // head.next->tail
    //tail.pre->head
    public DoublyLinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 根据双向链表的索引找到节点
     *
     * @param index 链表索引
     * @return 找到返回节点，找不到返回null
     */

    private Node findNode(int index) {
        //哨兵的存在就是为了处理临界的情况
        //i=-1时找的是哨兵
        int i = -1;
        //从头哨兵开始查找，直到p到尾哨兵了就停止
        //p!=tail可以改成p.next!=null
        // 因为如果p.next==null说明此时p已经到达尾哨兵tail的位置了
        for (Node p = head; p != tail; p = p.next, i++) {
            if (index == i) {
                return p;
            }
        }
        return null;
    }

    /**
     * 在双向链表索引位置加入节点
     *
     * @param index 索引
     * @param value 插入的值
     */

    public void insert(int index, int value) {
        //找到插入索引位置的前一个节点
        Node prev = findNode(index - 1);
        //插入的位置的前一个可能会为空
        //eg:[ 0 1 2 3 4 ]
        // 插入索引为6 肯定出异常，
        // 插入索引为0,prev不为null是头哨兵节点，
        // 插入位置为4，next不为null是尾哨兵节点
        if (prev == null) {
            illegalIndex(index);
        }
//        Node next = findNode(index);  //也可以通过findNode找但是会多一次遍历
        Node next = prev.next;
        Node inserted = new Node(prev, value, next);
        //现在的指向关系 node.prev->prev  ,node.next->next
        //将prev的next指针->node  ,next的prev指针—>node
        prev.next = inserted;
        next.prev = inserted;
    }

    /**
     * 双向链表（带哨兵）头插法从头部插入
     *
     * @param value
     */

    public void addFirst(int value) {
        insert(0, value);
    }

    /**
     * 删除双向链表（带哨兵）索引位置的节点
     *
     * @param index
     */
    public void remove(int index) {
        Node prev = findNode(index - 1);
        //如果删除的索引的prev找不到抛异常
        // eg:索引位置[-1 0 1 2 3 4 5 6]
        //头哨兵的索引在-1 ，尾哨兵在6  链表实际元素[0 1 2 3 4 5 ]
        //给出index=8，那么prev==null
        if (prev == null) {
            illegalIndex(index);
        }

        Node removeNode = prev.next;
        //如果删除的是尾哨兵那么抛异常
        //eg:给出Index=6根据索引的范围，prev来到元素为5的位置，此时删除的是尾哨兵
        if (removeNode == tail) {
            illegalIndex(index);
        }
        Node next = removeNode.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * 双向链表（带哨兵）移除链表第一个元素
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     * 双向链表（带哨兵）尾插法从尾部添加
     *
     * @param value
     */
    public void addLast(int value) {
        Node last = tail.prev;
        Node node = new Node(last, value, tail);
        last.next = node;
        tail.prev = node;
    }

    public void removeLast() {
        Node removeNode = tail.prev;
        //存在特殊情况，当链表只有两个元素时
        //那么tail.prev就是头哨兵节点了，不能把头哨兵节点也删了
        if (removeNode == head) {
            illegalIndex(0);
            return;
        }
        Node prev = removeNode.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    private static void illegalIndex(int index) {
        throw new IllegalArgumentException(String.format("index [%d] 不合法 /n", index));
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            //迭代器从头哨兵的下一个开始遍历
            Node p = head.next;
            @Override
            public boolean hasNext() {
                //循环条件
                return p!=tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p=p.next;
                return value;
            }
        };
    }
}
