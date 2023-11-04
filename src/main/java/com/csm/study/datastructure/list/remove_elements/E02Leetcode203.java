package com.csm.study.datastructure.list.remove_elements;

import com.csm.study.datastructure.list.structure.ListNode;

public class E02Leetcode203 {

    /**
     * 方法1
     * @param head -链表头
     * @param val  -目标值
     * @return 删除后的链表头
     */
    public ListNode removeElements1(ListNode head, int val) {
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

//    /**
//     * @param head -链表头
//     * @param val  -目标值
//     * @return 删除后的链表头
//     */
//    public ListNode removeElements1(ListNode head, int val) {
//        //哨兵节点（没有哨兵节点时删除第一元素会很麻烦）
//        ListNode s = new ListNode(-1, head);
//        ListNode p1 = s;
//        ListNode p2;
//        while ((p2 = p1.next) != null) {
//            if (p2.val == val) {
//                //删除
//                p1.next = p2.next;
//                //p2向后平移
////                p2 = p1.next;
//            } else {
//                //没找到
//                //p1和p2都往后平移
//                p1 = p1.next;
////                p2 = p1.next;
//            }
//        }
//        //返回
//        return s.next;
//    }

    /**
     * 方法2
     * 递归删除值为val的节点
     * @param p 传入的节点
     * @param val 目标值
     * @return
     */
    public ListNode removeElements(ListNode p,int val){
        if (p==null){
            return null;
        }
        //当val和当前节点p.val相等的时候，返回的时下一个节点的删除结果
        if (p.val==val){
            return removeElements(p.next,val);
        }else {
            //不相等的时候
            //将当前节点的next指针指向下一个节点的删除结果，再返回
            p.next=removeElements(p.next,val);
            return p;
        }
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(6, null);
        ListNode o4 = new ListNode(6, o5);
        ListNode o3 = new ListNode(1, o4);
        ListNode o2 = new ListNode(3, o3);
        ListNode o1 = new ListNode(2, o2);
        System.out.println(new E02Leetcode203().removeElements(o1, 6));
    }
}
