package com.pf.leetcode.entity;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public static void main(String[] args) {
        int a = -1<<31;
        System.out.println(a<<1);
    }

    public static ListNode create(int[] vals) {
        ListNode sentinel = new ListNode(1);
        ListNode pre = sentinel;
        for (int val : vals) {
            pre.next = new ListNode(val);
            pre = pre.next;
        }
        return  sentinel.next;
    }
}