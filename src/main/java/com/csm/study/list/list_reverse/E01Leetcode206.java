package com.csm.study.list.list_reverse;

import com.csm.study.datastructure.ListNode;

/**
 * 链表反转（通过构造一个）
 */
public class E01Leetcode206 {

    /**
     * 方法1
     * 原链表的遍历的结果以头插的方式插入新链表
     *
     * @param o1
     * @return
     */
    public ListNode reverseList1(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }

    /**
     * 方法二
     * 链表反转：原链表从头部移除，新链表从头部添加
     *
     * @param head
     * @return
     */

    public ListNode reverseList2(ListNode head) {
        list list1 = new list(head);
        list list2 = new list(null);
        while (true) {
            ListNode first = list1.removeFirst();
            //当first为空时说明list1已经被移除完了
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    /**
     * 方法三
     * 链表反转：先递归找到最后一个节点，作为头节点返回
     * 在每次归的时候，将 当前节点 的 下一个节点的next指针 ，指向当前节点（p.next.next->p） 5->4->5
     * 再将p->null 避免环形指向 5->4->null
     *
     * @param p
     * @return
     */
    public ListNode reverseList3(ListNode p) {
        //p的next指向空了，说明p就是最后一个节点
        if (p == null || p.next == null) {
            return p;
        }
        //递的时候找到最后一个节点
        ListNode last = reverseList3(p.next);
        //归的时候p.next.next  也就是当前节点p的下一个节点的next指向p
        //eg[ 1 2 3 4 5] 当p.next==null时候递归return p了，能走到p.next.next = p时，p指向的是4,p.next指向的是5
        //此时5->4 , 4->5 循环了，所以每层 归 的 时候 4->null ,也就是让p->null
        p.next.next = p;//这两行代码将相邻两个节点逆序
        p.next = null;
        //将最后一个节点作为头指针返回
        return last;
    }

    /**
     * 方法四
     * 将o1和n1都指向旧链表的头部，定义o2来指向旧链表的第二个元素
     * 不断更新o2,将o2从旧链表中移到新链表的头部n1加入
     * 直到o2为null，说明旧链表走到头了
     *
     * @param o1 旧链表头指针
     * @return n1新链表头指针
     */
    private ListNode reverseList4(ListNode o1) {
        //边界情况：当链表中没有元素，或者只有一个元素时
        if (o1 == null || o1.next == null) {
            //直接返回头节点
            return o1;
        }
        //o1始终指向旧链表的头，定义o2来指向旧链表的第2个元素
        ListNode o2 = o1.next;
        //将新链表的头部n1也指向旧链表的头部o1
        ListNode n1 = o1;
        while (o2 != null) {
            //在旧链表中移除o2
            o1.next = o2.next;
            //在n1头部加入o2
            o2.next = n1;
            //并将n1指向头部
            n1 = o2;
            //更新o2为旧链表的第二个元素
            o2 = o1.next;
        }
        return n1;
    }

    /**
     * 方法五
     * 方法二的面向过程的实现
     * @param o1
     * @return
     */
    private ListNode reverseList(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        ListNode n1 = null;
        while (o1 != null) {
            //记录o1的下一位置
            ListNode o2 = o1.next;
            //将o1.next指向n1（o1插入n1的头部）
            o1.next = n1;
            //再将n1指向 o1 （更新头指针）
            n1 = o1;
            //更新o1指向o2记录的位置
            o1 = o2;
        }
        return n1;
    }

    //容器类
    static class list {
        ListNode head;

        public list(ListNode head) {
            this.head = head;
        }

        //从头部添加
        public void addFirst(ListNode first) {
            //head==null时也成立，头部添加就是 将新加节点的next指向head，再将head指向新加的节点
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {

            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }
    }


    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        //重写了toString方法，遍历打印链表
        System.out.println(o1);
        E01Leetcode206 e01Leetcode206 = new E01Leetcode206();
        ListNode n1 = e01Leetcode206.reverseList(o1);
        System.out.println(n1);
    }


}
