package com.csm.study.list.remove_elements;

import com.csm.study.datastructure.ListNode;

/**
 * 删除有序链表的重复元素
 */
public class E04Leetcode83 {
    /**
     * 删除有序链表中重复的元素
     * 注意是 有序链表
     * @param head
     * @return
     */
    public ListNode deleteDuplicate(ListNode head) {

        //链表节点数<2时（当链表为空或者链表只有1个元素时，直接返回头节点）
        if (head == null || head.next == null) {
            return head;
        }
        //链表节点数>=2时
        //假设p1，p2为相邻两个节点
        ListNode p1 = head;
        ListNode p2;
        //p2始终是p1的下一个节点，直到p2走到链表结束为null
        while ((p2 = p1.next) != null) {
            //如果p1和p2的val值相同，那么删掉p2,进入下一个循环p2又被更新为p1下一个
            if (p1.val == p2.val) {
                p1.next = p2.next;
            } else {
                //如果不相等p1往后移动一位，p2在进入下次循环时也跟着后移一位，保持p1和p2相邻
                p1 = p1.next;
            }
        }
        //跳出循环return head;
        return head;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(2, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        System.out.println(new E04Leetcode83().deleteDuplicate(o1));
    }
}
