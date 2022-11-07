package com.pf.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int cap;
    private int top;
    private Map<Integer, Node> valMap;
    private Node head;
    private Node end;

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }
    public LRUCache(int capacity) {
        cap = capacity;
        top = 0;
        valMap = new HashMap(capacity);
        head = new Node(-1, -1);
        end = new Node(-1, -1);
        head.next = end;
        end.pre = head;
    }

    public int get(int key) {
        Node now = valMap.get(key);
        if(now == null) {
            return -1;
        }
        update(key, now);
        return now.val;
    }

    public void put(int key, int value) {
        Node tmp = valMap.get(key);
        if(tmp != null) {
            tmp.val = value;
        } else if (top < cap) {
            tmp = new Node(key, value);
            valMap.put(key, tmp);
            insert(key, tmp);
        } else if (top == cap) {
            tmp = new Node(key, value);
            remove(end.pre.key, end.pre);
            insert(key, tmp);
        }
    }

    private void insert(int key, Node now) {
        if (now == head.next || top == cap) {
            return;
        }
        Node next = head.next;
        next.pre = now;
        now.next = next;

        now.pre = head;
        head.next = now;
        valMap.put(key, now);
        top++;
    }

    private void remove(int key, Node now) {
        if(top == 0) {
            return;
        }
        Node pre = now.pre;
        Node next = now.next;
        pre.next = next;
        next.pre = pre;
        valMap.remove(key);
        top--;
    }

    private void update(int key, Node now) {
        remove(key,now);
        insert(key,now);
    }


    static class Node {
        Node pre;
        Node next;
        int val;
        int key;

        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }
}
