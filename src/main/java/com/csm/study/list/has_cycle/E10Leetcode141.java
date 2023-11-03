package com.csm.study.list.has_cycle;

import com.csm.study.datastructure.ListNode;

/**
 * leetcode141题 判断链表中是否存在环
 * 龟兔赛跑算法，也被称为Floyd's Tortoise and Hare Algorithm
 * 是一种用于检测链表中是否存在环的快慢指针算法。
 */
public class E10Leetcode141 {
    public boolean hasCycle(ListNode head) {
        ListNode h = head;//兔  快指针
        ListNode t = head;//龟  慢指针
        while (h != null && h.next != null) {
            h = h.next.next; //兔子走两步
            t = t.next; //乌龟走一步
            if (h == t) {//如果兔子追上乌龟说明存在环
                return true;
            }
        }
        //如果兔子有走到null的情况说明不存在环
        return false;
    }
}
