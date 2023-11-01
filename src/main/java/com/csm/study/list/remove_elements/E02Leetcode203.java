package com.csm.study.list.remove_elements;

import com.csm.study.datastructure.ListNode;

public class E02Leetcode203 {

    /**
     * @param head -链表头
     * @param val  -目标值
     * @return 删除后的链表头
     */
    public ListNode removeElements(ListNode head, int val) {
        //哨兵节点（没有哨兵节点时删除第一元素会很麻烦）
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = p1.next;
        while (p2 != null) {
            if (p2.val == val) {
                //删除
                p1.next = p2.next;
                //p2向后平移
                p2 = p2.next;
            } else {
                //没找到
                //p1和p2都往后平移
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        //返回
        return s.next;
    }

    /**
     * @param head -链表头
     * @param val  -目标值
     * @return 删除后的链表头
     */
    public ListNode removeElements1(ListNode head, int val) {
        //哨兵节点（没有哨兵节点时删除第一元素会很麻烦）
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p2.val == val) {
                //删除
                p1.next = p2.next;
                //p2向后平移
//                p2 = p1.next;
            } else {
                //没找到
                //p1和p2都往后平移
                p1 = p1.next;
//                p2 = p1.next;
            }
        }
        //返回
        return s.next;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(6, null);
        ListNode o4 = new ListNode(6, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(new E02Leetcode203().removeElements(o1, 6));
    }
}
