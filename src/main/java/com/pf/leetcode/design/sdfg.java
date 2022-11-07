package com.pf.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class sdfg {

    public static void main(String[] args) {
        sdfg sdfg = new sdfg(1);
        sdfg.put(2,1);
        sdfg.get(2);
    }
    private int capacity;
    private int size;
    private Map<Integer, Node> nodesMap;
    private Node first;
    private Node end;

    public sdfg(int capacity) {
        this.capacity = capacity;
        size = 0;
        nodesMap = new HashMap(capacity);
        first = new Node(-1,-1);
        end = new Node(-1,-1);
        first.next = end;
        end.pre = first;
    }

    public int get(int key) {
        Node tmp = nodesMap.get(key);
        if(tmp == null) {
            return -1;
        }
        removeNode(tmp);
        addNode(tmp);
        return tmp.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }
        Node tmp = nodesMap.get(key);
        if(tmp != null) {
            tmp.val = value;
            removeNode(tmp);
            addNode(tmp);
        } else {
            Node newNode = new Node(key, value);
            if(size == capacity) {
                removeNode(end.pre);
            }
            addNode(newNode);
        }
    }

    private void addNode(Node addNode) {
        nodesMap.put(addNode.key, addNode);
        Node next = first.next;
        first.next = addNode;

        addNode.next = next;
        next.pre = addNode;
        size++;
    }

    private void removeNode(Node rmNode) {
        nodesMap.remove(rmNode.key);
        Node pre = rmNode.pre;
        Node next = rmNode.next;

        pre.next = next;
        next.pre = pre;
        size--;
    }

    static class Node {
        private int val;
        private int key;
        private Node pre;
        private Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
