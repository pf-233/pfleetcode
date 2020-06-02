package com.pf.leetcode.link;

import com.pf.leetcode.entity.ListNode;

/**
 * @author pf
 * @date 2020-05-16 11:29
 **/
public class ReverseKGroup {

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
        ListNode newl = reverseKGroup(l1, 5);
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1){
            return head;
        }
        ListNode temp = null;
        ListNode start = head;
        ListNode end = head;
        ListNode headNew = null;
        ListNode pre = null;
        ListNode last = null;
        while(end != null){
            int count = 1;
            while(end != null && count < k){
                end = end.next;
                count++;
            }
            if(count < k || end == null){
                if(headNew == null){
                    return head;
                } else {
                    pre.next = start;
                    return headNew;
                }
            } else {
                last = end.next;
                reverse(start, end);

                if(pre == null){
                    headNew = end;
                    pre = start;
                } else {
                    pre.next = end;
                    pre = start;
                }
                start = last;
                end = last;
                if (last == null){
                    pre.next = null;
                }
            }
        }
        return headNew;
    }


    public static void reverse(ListNode start, ListNode end){
        ListNode node1 = start;
        ListNode node2 = node1.next;
        ListNode node3 = node2.next;
        while(node2 != end){
            node2.next = node1;
            node1 = node2;
            if(node3 == null){
                return;
            }
            node2 = node3;
            node3 = node3.next;
        }
        node2.next = node1;
    }
}
