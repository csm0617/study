package com.csm.study.datastructure.list.middle_node;

import com.csm.study.datastructure.list.structure.ListNode;

public class E08Leetcode876 {

    /**
     * 快慢指针法
     * 找到链表中间位置的节点(当链表的个数是偶数定义右边那个是中间节点)
     * eg: [1 2 3] 奇数个 2 是中间位置
     * eg: [1 2 3 4 5 6]偶数个 定义 4 为中间节点
     * @param head 链表头
     * @return 中间位置节点
     */
    //快慢指针，p2每次比p1多走1步，当p2走到链表结束（p2.next==null或者p2==null）时，p1刚好走到了中点的位置
    public ListNode middleNode(ListNode head) {
        ListNode p1 = head;//慢指针
        ListNode p2 = head;//快指针
        while (p2 != null && p2.next != null) {//p2!=null满足链表为偶数情况，p2.next != null满足链表为奇数情况
            //p1每次往后移一个
            p1=p1.next;
            //p2每次往后移两个
            p2=p2.next;
            p2=p2.next;

        }
        //结束循环时p1来到了中点
        return p1;

    }

    public static void main(String[] args) {
        ListNode o1 = new ListNode().of(1, 2, 3, 4, 5, 6);
        ListNode o2 = new ListNode().of(1, 2, 3, 4, 5, 6,7,8,9);
        System.out.println(new E08Leetcode876().middleNode(o1).val);
        System.out.println(new E08Leetcode876().middleNode(o2).val);
    }


}
