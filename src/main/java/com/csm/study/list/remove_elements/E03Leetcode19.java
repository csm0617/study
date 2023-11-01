package com.csm.study.list.remove_elements;

import com.csm.study.datastructure.ListNode;

/**
 * 删除倒数第n个节点
 */
public class E03Leetcode19 {

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        /**
         * 直接调用会有问题
         * 当删除的是第一个节点时1，他的前一个节点p为null，没删除
         */
//        recursion(head,n);
        //定义一个哨兵节点,这样就不会在删除头的时候出错
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }

    /**
     * 快慢指针法
     * ListNode p1 = s; //p1快指针
     * ListNode p2 = s; //p2慢指针
     * 让p1先走n+1个距离，再让p1和p2一起移动直到p1走到null，p2就走到了倒数n+1的位置
     * 删除倒数第n个节点只需要将 p2.next = p2.next.next;
     * @param head
     * @param n 需要删除的倒数第n个位置
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 直接调用会有问题
         * 当删除的是第一个节点时1，他的前一个节点p为null，没删除
         */
//        recursion(head,n);
        //定义一个哨兵节点,这样就不会在删除头的时候出错
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s; //p1快指针
        ListNode p2 = s; //p2慢指针
        //循环主要是让快指针p1先走n+1个距离(为什么不是n?因为倒数n的前一个节点方便做删除操作)
        for (int i = 0; i < n + 1; i++) {
            p1 = p1.next;
        }
        //当快指针p1走到null时，慢指针p2走到了倒数第n+1个位置
        //当p1先走n个距离后p1和p2一起平移
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        //跳出while时，慢指针p2走到了倒数第n+1的位置
        //删掉 倒数位置第n个节点
        p2.next = p2.next.next;
        return s.next;
    }


    /**
     * 删除倒数第n个节点
     *
     * @param p
     * @param n
     * @return
     */
    private int recursion(ListNode p, int n) {
        //把p==null的时候位置定义为0
        if (p == null) {
            return 0;
        }
        int nth = recursion(p.next, n);//下一个节点的位置
        //可以在这里打印当前节点的值和下一个节点的倒数位置
//        System.out.println(p.val+" "+nth);
        //如果下一个节点的位置和n相等
        if (nth == n) {
            //删除下一个节点(就是把当前p.next的指针指向 p的下下个节点)
            p.next = p.next.next;
        }

        return nth + 1;//返回当前节点的位置
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        System.out.println(new E03Leetcode19().removeNthFromEnd(o1, 1));
    }

}
