package com.csm.study.list.remove_elements;

import com.csm.study.datastructure.ListNode;

/**
 * 删除有序链表的重复元素--重复元素一个不留
 */
public class E05Leetcode82 {
    /**
     * 删除有序链表中重复的元素--重复元素一个不留
     * 注意是 有序链表
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        //链表节点数<2时（当链表为空或者链表只有1个元素时，直接返回头节点）
        if (head == null || head.next == null) {
            return head;
        }
        //链表节点数>=2时
        //定义一个哨兵节点
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2, p3;
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null && p3.val == p2.val) {

                }
                //一直找到值不重复的p3
                p1.next = p3;
            } else {
                //如果找不到 p1 p2 p3（全部后移，p2和p3在while循环中移动了）
                p1 = p1.next;
            }

        }

        //跳出循环return head;
        return s.next;
    }

    /**
     * 递归删除有序链表中的重复元素--重复元素一个不留
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {

        //链表节点数<2时
        if (head == null || head.next == null) {
            return head;
        }

        //链表节点数>=2时
        ListNode p = head;
        //当p和p.next的val相等时
        if (p.val == p.next.val) {
            //定义一个x来接收p.next.next
            ListNode x = p.next.next;
            //跳过x.val和p.val相等的节点
            while (x != null && x.val == p.val) {
                x = x.next;
            }
            //返回第一个值不相等的节点的递归结果
            return deleteDuplicates1(x);
        } else {
            //如果p.val和p.next.val不相等
            //那么将p.next指向p.next的递归结果
            p.next = deleteDuplicates1(p.next);
            //返回
            return p;
        }

    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(2, null);
        ListNode o4 = new ListNode(2, o5);
        ListNode o3 = new ListNode(2, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        System.out.println(new E05Leetcode82().deleteDuplicates(o1));
    }
}
