package com.csm.study.datastructure;

import com.csm.study.list.SinglyLinkedSentinel;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@ToString
@NoArgsConstructor
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode of(int... elements){
        if (elements==null){
            return null;
        }
        ListNode s = new ListNode(-1, null);
        ListNode p=s;
        for (int i = 0; i < elements.length; i++) {
            ListNode node = new ListNode(elements[i], null);
            p.next=node;
            p=node;
        }
        return s.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while (p != null) {
            sb.append(p.val);
            if (p.next != null) {
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
