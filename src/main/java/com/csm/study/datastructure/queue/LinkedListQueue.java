package com.csm.study.datastructure.queue;

import com.csm.study.datastructure.queue.structure.Queue;

import java.util.Iterator;

/**
 * 单向环形带哨兵的链表 实现队列
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {


    private static class Node<E> {
        E value;
        Node next;

        //构造方法
        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

    }

    //--------------初始化--------------
    //创建哨兵，并将head指向哨兵
    private Node<E> head = new Node<>(null, null);
    //将尾tail也指向哨兵
    private Node<E> tail = head;
    //节点数
    private int size;
    //队列容量（默认最大）
    private int capacity=Integer.MAX_VALUE;
    //构造方法来初始化构成环形（上一步head和tail都指向哨兵了）
    {
        tail.next = head;
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    public LinkedListQueue() {
    }

    /**
     * 向队列尾插入值
     *
     * @param value 待插入值
     * @return 插入成功返回true，插入失败返回false
     */
    @Override
    public boolean offer(E value) {
        //先判断队列是否满了
        if (isFull()){
            return false;
        }
        //加入节点
        //因为是环形链表，且因为队列中新加的节点都是在尾添加，所以尾部节点的next应该指向head
        Node<E> added = new Node<>(value, head);
        //从队列尾添加
        tail.next = added;
        //更新队列的尾指针指向新加节点
        tail = added;
        size++;
        return true;
    }

    /**
     * 从队列头获取值，并移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        //从队头移除first
        head.next=first.next;
        //当队列中只有一个元素的时候，从队头移除元素后
        //还需要
        //将尾指针指向head(指向哨兵)(恢复到空队列初始状态)
        if (first.next==head){
            tail.next=head;
        }
        size--;
        return first.value;
    }

    /**
     * 从队列头获取值，不移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    @Override
    public E peek() {
        //先判空
        if (isEmpty()){
            return null;
        }
        //head指针的下一个就是对头元素
        Node<E> first = head.next;
        return first.value;
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回ture, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        // 因为刚开始时队列为空，头尾指针都指向哨兵节点,head==tail
        // 当有元素加入队列时，尾指针开始移动这时头尾指针才不相等
        return head==tail;
    }

    /**
     * 检查队列是否满了
     *
     * @return 满了返回true, 没满返回false
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                //环形链表，p走到头就结束遍历
                return p != head;
            }

            @Override
            public E next() {
                //记录value
                E value = p.value;
                //p继续后移
                p = p.next;
                return value;
            }
        };
    }
}
