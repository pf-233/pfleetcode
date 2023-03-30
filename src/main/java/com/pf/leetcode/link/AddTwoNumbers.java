package com.pf.leetcode.link;

import com.pf.leetcode.entity.ListNode;

public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l12.next = l13;
        l1.next = l12;

        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode res = addTwoNumbers.addTwoNumbers(l1, l2);
        System.out.println(res);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode res = addNumbers(l1, l2);
        if (res.val > 9) {
            res = new ListNode(res.val / 10, res);
        }
        return res;
    }

    ListNode addNumbers(ListNode l1, ListNode l2) {
        ListNode tempNode = null;
        ListNode res = null;
        int val = 0;
        if (l1.next == null && l2.next == null) {
            res = new ListNode(l1.val + l2.val);
        } else if (l1.next != null && l2.next != null) {
            tempNode = addNumbers(l1.next, l2.next);
            val = l1.val + l2.val + tempNode.val / 10;
            tempNode.val %= 10;
            res = new ListNode(val, tempNode);
        } else if (l1.next == null) {
            res = addNumbers(l1, l2.next);
        } else {
            res = addNumbers(l1.next, l2);
        }
        return res;
    }
}
