package com.csm.study.list.merge_two_list;

import com.csm.study.datastructure.ListNode;
import com.csm.study.list.list_reverse.E01Leetcode206;

import java.util.List;

/**
 * leetcode21合并两个有序链表
 */
public class E06Leetcode21 {
    /**
     * 合并两个有序链表
     *
     * @param p1 链表1
     * @param p2 链表2
     * @return 新链表的头节点
     */
    public ListNode mergeTwoLists1(ListNode p1, ListNode p2) {
        //合并后放入新链表中，s为新链表的哨兵节点
        ListNode s = new ListNode(-1, null);
        //因为在两个链表都没走到null的时候，节点一个个加的
        //所以必须要有一个指针指向新链表的当前节点
        ListNode p = s;
        //当两个链表都没走到空时
        while (p1 != null && p2 != null) {
            //谁小谁就链入新链表
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            //更新新链表的当前指针
            p = p.next;
        }
        //当两个链表至少其中一个为空时，把不为空的链表加入到新链表的尾部
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        //返回新链表的头
        return s.next;
    }

    /**
     * 合并两个有序链表
     *
     * @param p1 链表1
     * @param p2 链表2
     * @return 新链表的头节点
     */
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        //结束递归的条件：
        //当p1或者p2其中一个走到null的时候
        //返回另外一个不为null的链表
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        if (p1.val < p2.val) {//谁小谁返回
            //在返回之前先带上，下一次递归的返回结果结果
            p1.next = mergeTwoLists(p1.next, p2);
            return p1;
        }else {
            p2.next = mergeTwoLists(p1, p2.next);
            return p2;
        }

    }

    public static void main(String[] args) {
        int[] list1 = {1, 2, 2, 4, 6, 7, 9};
        int[] list2 = {1, 2, 3, 3, 5, 8, 10, 11, 15};
        ListNode old1 = new ListNode().of(list1);
        System.out.println(old1);
        ListNode old2 = new ListNode().of(list2);
        System.out.println(old2);
        System.out.println("merge two list:");
        System.out.println(new E06Leetcode21().mergeTwoLists(old1, old2));
    }
}
