package com.pf.leetcode.tree;

public class SortedListToBST {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(-10);
        ListNode listNode1 = new ListNode(-3);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3= new ListNode(5);
        ListNode listNode4 = new ListNode(9);
        SortedListToBST sortedListToBST = new SortedListToBST();
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(sortedListToBST.sortedListToBST(listNode));
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        return toBst(head);
    }

    TreeNode toBst(ListNode head) {
        if(head == null) {
            return null;
        } else if(head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode first = head;
        ListNode pre = head;
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        while(fastPoint.next != null) {
            pre = slowPoint;
            slowPoint = slowPoint.next;
            if(fastPoint.next.next == null) {
                break;
            } else {
                fastPoint = fastPoint.next.next;
            }
        }
        TreeNode subRoot = new TreeNode(slowPoint.val);
        pre.next = null;
        subRoot.left = toBst(head);
        subRoot.right = toBst(slowPoint.next);
        return subRoot;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
