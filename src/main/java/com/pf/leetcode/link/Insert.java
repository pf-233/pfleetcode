package com.pf.leetcode.link;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Insert {
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(3);
        node.next = node2;
        node2.next = node3;
        node3.next = node;
        System.out.println(new Insert().insert(node, 4).val);
    }
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node tmp = new Node(insertVal);
            tmp.next = tmp;
            return tmp;
        }
        Node now = head;
        Node next = head.next;
        if (now == next) {
            now.next = new Node(insertVal, now);
            return head;
        }
        Set<Node> visSet = new HashSet();
        // 没出现过的就说明没循环
        Node maxNode = now;
        while (!visSet.contains(now)) {
            // 如果在前和后中间就直接插入
            if (now.val <= insertVal && next.val >= insertVal) {
                now.next = new Node(insertVal, next);
                return head;
            }
            // 加入访问
            visSet.add(now);
            // 节点后移
            now = next;
            next = now.next;
            maxNode = maxNode.val < now.val ? now : maxNode;
        }
        // 没结束说明全都小于插入点，这个时候插入到最大后面
        next = maxNode.next;
        maxNode.next = new Node(insertVal, next);

        return head;
    }
}

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};