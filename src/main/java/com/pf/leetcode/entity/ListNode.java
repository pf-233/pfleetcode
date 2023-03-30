package com.pf.leetcode.entity;

public class ListNode {
    public int val;
    public ListNode next;

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
}