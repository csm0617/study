package com.csm.study.list.is_palindrome;

import com.csm.study.datastructure.ListNode;

/**
 * 判断回文链表
 * 要求空间复杂度O(1) ,时间复杂度O(n)
 */
public class E09LeetCode234 {
    /**
     * 1.先找到中间节点
     * 2.再将中间节点的后半部分反转
     * 3.将后半部分和前半部分比较
     */

    /**
     * 1.找到链表得中间节点
     *
     * @param head
     * @return
     */
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head; //快指针
        ListNode p2 = head; //慢指针
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;

    }

    /**
     * 2.反转链表中间节点及以后得节点
     *
     * @param o1
     * @return
     */
    private ListNode reverse(ListNode o1) {
        ListNode n1 = null;
        //以头插的方式逐步将o1加入到n11
        while (o1 != null) {
            //为了将o1脱离，先记录o1的next
            ListNode o2 = o1.next;
            //头插加入n1
            o1.next = n1;
            //更新n1始终指向头部
            n1 = o1;
            //再把记录的n1更新
//            o1 = o2.next;        ！！！！！！！！这里错了
            o1 = o2;
        }
        return n1;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode middleNode = middleNode(head);
        ListNode reverse = reverse(middleNode);
        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode o1 = new ListNode().of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ListNode middle = new E09LeetCode234().middleNode(o1);
        System.out.println(middle);
        ListNode n1 = new E09LeetCode234().reverse(middle);
        System.out.println(n1);
        ListNode o2 = new ListNode().of(1, 2, 2, 4, 5, 4, 2, 2, 1);
        System.out.println(new E09LeetCode234().isPalindrome(o2));


    }
}
