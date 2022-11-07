package com.pf.leetcode.link;

import com.pf.leetcode.entity.ListNode;

public class ReorderList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        new  ReorderList().reorderList(l1);
        System.out.println(l1);
    }
    public void reorderList(ListNode head) {
        ListNode f1 = head;
        ListNode f2 = head.next;
        while (f2 != null) {
            f1 = f1.next;
            f2 = f2.next;
            if (f2 != null) {
                f2 = f2.next;
            }
        }

        ListNode tail = resver(f1.next);
        f1 = head;
        while (f1 != null) {
            ListNode next = f1.next;
            f1.next = tail;
            ListNode next2 = tail.next;
            tail.next = next;
            f1 = next;
            tail = next2;
        }
    }

    ListNode resver(ListNode node) {
        ListNode pre = null;
        ListNode now = node;
        while (now != null) {
            ListNode next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }
}
