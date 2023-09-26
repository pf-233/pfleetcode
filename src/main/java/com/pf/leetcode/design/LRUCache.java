package com.pf.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));;       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));;       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println( cache.get(1));;       // 返回 -1 (未找到)
        System.out.println(cache.get(3));;       // 返回  3
        System.out.println(cache.get(4));;       // 返回  4
    }

    int capacity;
    Node head;
    Node end;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        end = new Node(-1, -1);
        head.next = end;
        end.pre = head;
        map = new HashMap();
    }

    public int get(int key) {
        Node now = map.get(key);
        if (now == null) {
            return -1;
        }
        removeNode(now);
        addFirst(now);
        return now.value;
    }

    public void put(int key, int value) {
        Node now = map.get(key);
        if (now != null) {
            now.value = value;
            removeNode(now);
            addFirst(now);
            // print();
            return;
        }

        Node node = new Node(key, value);
        if (map.size() < capacity){
            addFirst(node);
        } else {
            Node last = end.pre;
            removeNode(last);
            addFirst(node);
        }
        // print();
    }

    private void print() {
        Node temp = head;
        while (temp != null) {
            // System.out.print(temp.value +",");
            temp = temp.next;
        }
        System.out.println();
    }

    private void addFirst(Node now) {
        now.pre = head;
        now.next = head.next;
        head.next.pre = now;
        head.next = now;
        map.put(now.key, now);
    }

    private void removeNode(Node now) {
        now.pre.next = now.next;
        now.next.pre = now.pre;
        map.remove(now.key);
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node next;
        Node(){}

        Node(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }
//
//    private int cap;
//    private int top;
//    private Map<Integer, Node> valMap;
//    private Node head;
//    private Node end;


//    public LRUCache(int capacity) {
//        cap = capacity;
//        top = 0;
//        valMap = new HashMap(capacity);
//        head = new Node(-1, -1);
//        end = new Node(-1, -1);
//        head.next = end;
//        end.pre = head;
//    }
//
//    public int get(int key) {
//        Node now = valMap.get(key);
//        if(now == null) {
//            return -1;
//        }
//        update(key, now);
//        return now.val;
//    }
//
//    public void put(int key, int value) {
//        Node tmp = valMap.get(key);
//        if(tmp != null) {
//            tmp.val = value;
//        } else if (top < cap) {
//            tmp = new Node(key, value);
//            valMap.put(key, tmp);
//            insert(key, tmp);
//        } else if (top == cap) {
//            tmp = new Node(key, value);
//            remove(end.pre.key, end.pre);
//            insert(key, tmp);
//        }
//    }
//
//    private void insert(int key, Node now) {
//        if (now == head.next || top == cap) {
//            return;
//        }
//        Node next = head.next;
//        next.pre = now;
//        now.next = next;
//
//        now.pre = head;
//        head.next = now;
//        valMap.put(key, now);
//        top++;
//    }
//
//    private void remove(int key, Node now) {
//        if(top == 0) {
//            return;
//        }
//        Node pre = now.pre;
//        Node next = now.next;
//        pre.next = next;
//        next.pre = pre;
//        valMap.remove(key);
//        top--;
//    }
//
//    private void update(int key, Node now) {
//        remove(key,now);
//        insert(key,now);
//    }
//
//
//    static class Node {
//        Node pre;
//        Node next;
//        int val;
//        int key;
//
//        Node(int key, int val) {
//            this.val = val;
//            this.key = key;
//        }
//    }
}
