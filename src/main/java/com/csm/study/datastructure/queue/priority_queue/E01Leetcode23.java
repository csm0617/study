package com.csm.study.datastructure.queue.priority_queue;

import com.csm.study.datastructure.list.structure.ListNode;

public class E01Leetcode23 {

    public ListNode mergeKLists(ListNode[] lists) {
        //1.将链表的头节点加入到小堆顶
        MinHeap heap = new MinHeap(lists.length);
        for (ListNode h : lists) {
            if (h != null) {
                heap.offer(h);
            }
        }
        //2.创建用来接收的合并数组链表的哨兵节点
        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        //3.不断从堆顶移出最小的元素，加入到新链表的尾部，直到堆为空
        while (!heap.isEmpty()) {
            //从堆顶移出元素
            ListNode min = heap.poll();
            //向新链表的尾部加入
            t.next = min;
            t = min;
            //如果min所在的链表没有走到结束，就把min的下一个节点加入到最小堆中。
            if (min.next != null) {
                heap.offer(min.next);
            }
        }
        return s.next;
    }

    public static void main(String[] args) {
        ListNode old1 = new ListNode().of(1, 2, 2, 4, 6, 7, 9);
        ListNode old2 = new ListNode().of(1, 2, 3, 3, 5, 8, 10, 11, 15);
        ListNode old3 = new ListNode().of(1, 3, 4, 5, 6, 10, 12, 13, 18);
        ListNode old4 = new ListNode().of(16, 21, 22, 24);
        ListNode[] lists = {old1, old2, old3, old4};
        System.out.println(new E01Leetcode23().mergeKLists(lists));
    }


}
