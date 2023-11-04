package com.csm.study.list.has_cycle;

import com.csm.study.datastructure.ListNode;

/**
 * leetcode141题 找到环的入口
 * 龟兔赛跑算法，也被称为Floyd's Tortoise and Hare Algorithm
 * 是一种用于检测链表中是否存在环的快慢指针算法。
 */
public class E11Leetcode142 {
    public ListNode detectCycle(ListNode head) {
        ListNode h = head;//兔  快指针
        ListNode t = head;//龟  慢指针
        while (h != null && h.next != null) {
            h = h.next.next; //兔子走两步
            t = t.next; //乌龟走一步
            if (h == t) {//如果乌龟和兔子相遇说明存在环
                //从第一次相遇开始，乌龟回到起点，兔子保持原地不动
                t = head;
                //乌龟和兔子各走一步，直到再次相遇就是环的入口
                while (true) {
                    //特殊情况只有一个大环（乌龟回到起点的时候已经是环的入口了）
                    if (t == h) {
                        return t;
                    }
                    t = t.next;
                    h = h.next;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
