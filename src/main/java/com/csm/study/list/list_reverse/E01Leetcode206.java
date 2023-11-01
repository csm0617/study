package com.csm.study.list.list_reverse;

import com.csm.study.datastructure.ListNode;

/**
 * 链表反转（通过构造一个）
 */
public class E01Leetcode206 {

    /**
     * 方法1
     * 原链表的遍历的结果以头插的方式插入新链表
     *
     * @param o1
     * @return
     */
    public ListNode reverseList1(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }

    /**
     * 方法二
     * 链表反转：原链表从头部移除，新链表从头部添加
     * @param head
     * @return
     */

    public ListNode reverseList(ListNode head) {
        list list1 = new list(head);
        list list2 = new list(null);
        while (true){
            ListNode first = list1.removeFirst();
            //当first为空时说明list1已经被移除完了
            if (first==null){
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    //容器类
    static class list {
        ListNode head;

        public list(ListNode head) {
            this.head = head;
        }

        //从头部添加
        public void addFirst(ListNode first) {
            //head==null时也成立，头部添加就是 将新加节点的next指向head，再将head指向新加的节点
            first.next = head;
            head = first;
        }

        public ListNode removeFirst(){

            ListNode first = head;
            if (first!=null){
                head = first.next;
            }
            return first;
        }
    }


    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        //重写了toString方法，遍历打印链表
        System.out.println(o1);
        E01Leetcode206 e01Leetcode206 = new E01Leetcode206();
        ListNode n1 = e01Leetcode206.reverseList(o1);
        System.out.println(n1);
    }

}
