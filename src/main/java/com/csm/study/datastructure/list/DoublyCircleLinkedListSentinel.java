package com.csm.study.datastructure.list;

import java.util.Iterator;

//双向环形链表（带哨兵）
public class DoublyCircleLinkedListSentinel implements Iterable<Integer> {

    //双向环形链表的遍历
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            //从头哨兵的的下一个开始遍历
            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                //当p回到头哨兵时结束
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    //定义节点
    private static class Node {
        Node prev;
        int value;
        Node next;
        //构造方法

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    //定义哨兵对象
    private Node sentinel = new Node(null, -1, null);

    //构造方法中初始化双向环形链表
    public DoublyCircleLinkedListSentinel() {
        //刚开始没有节点时，首尾指针都指向自己
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 添加到第一个（当双向环形链表时也适用此时a和b都是哨兵节点）
     *
     * @param value
     */
    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 删除第一个
     */
    public void removeFirst() {
        Node removedNode = sentinel.next;
        Node a = sentinel;
        //特殊情况 当双向环形链表中只有哨兵节点时
        if (removedNode == sentinel) {
            throw new IllegalArgumentException("非法的，不能删除哨兵节点");
        }
        Node b = removedNode.next;
        a.next = b;
        b.prev = a;
    }

    public void removeLast() {
        //双向环形链表找到最后一个元素
        Node removedNode = sentinel.prev;
        //特殊情况，当双向环形链表中只有哨兵时，不能把哨兵给删了
        if (removedNode == sentinel) {
            throw new IllegalArgumentException("非法的，不能删除哨兵节点");
        }
        Node a = removedNode.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
    }

    /**
     * 根据值删除（多个相同的值，删除第一个）
     *
     * @param value 目标值
     */
    public void removeByValue(int value) {
        Node removed = findByValue(value);
        //如果没找到就什么也不做
        if (removed == null) {
            return;//不用删
        }
        Node a = removed.prev;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
    }

    /**
     * 根据目标值找到节点并返回
     *
     * @param value 目标值
     * @return
     */
    private Node findByValue(int value) {
        //从哨兵的下一个开始遍历
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
        }
        return null;
    }



}
