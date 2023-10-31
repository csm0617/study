package com.csm.study.list.list_reverse;

import com.csm.study.datastructure.ListNode;

/**
 * 链表反转（通过构造一个）
 */
public class E01Leetcode206 {

    /**
     * 原链表的遍历的结果以头插的方式插入新链表
     * @param o1
     * @return
     */
    public ListNode reverseList(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
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
