package com.csm.study.list.merge_two_list;

import com.csm.study.datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 合并多个有序链表leetcode23
 * 把问题分解为两个链表
 */
public class E07Leetcode23 {
    /**
     * 合并两个有序链表
     *
     * @param p1 链表1
     * @param p2 链表2
     * @return 新链表的头节点
     */
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);//注意右边界lists.length会越界
    }

    /**
     * @param lists 包含多个链表的数组
     * @param i     链表数组的左边界
     * @param j     链表数组的右边界
     * @return
     */
    private ListNode split(ListNode[] lists, int i, int j) {
        //递归结束条件i==j
        //当i==j时说明此时已经拆分分到只有单个链表了
        if (i == j) {
            //返回单个链表
            return lists[i];
        }
        //求数组中点
        int m = i + ((j - i) >>> 1);
        //拆分左边
        ListNode left = split(lists, i, m);
        //拆分右边
        ListNode right = split(lists, m + 1, j);//注意right左边界从m+1开始，因为left的右边界已经到m了
        //合并左右
        return mergeTwoLists(left, right);
    }

    public static void main(String[] args) {
        ListNode old1 = new ListNode().of(1, 2, 2, 4, 6, 7, 9);
        ListNode old2 = new ListNode().of(1, 2, 3, 3, 5, 8, 10, 11, 15);
        ListNode old3 = new ListNode().of(1, 3, 4, 5, 6, 10, 12, 13, 18);
        ListNode old4 = new ListNode().of(16, 21, 22, 24);
        ListNode[] lists = {old1, old2, old3, old4};
        System.out.println(new E07Leetcode23().mergeKLists(lists));
    }
}
