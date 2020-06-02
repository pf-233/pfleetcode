package com.pf.leetcode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pf
 * @date 2020-05-25 13:38
 **/
public class LRUCache {

    Node first;
    Node last;
    int capacity;
    int count;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.first = new Node(-1, -1);
        this.last = new Node(-1, -1);
        first.next = last;
        last.pre = first;
        map = new HashMap();
    }

    public int get(int key) {
        Node now = map.get(key);
        if(now == null){
            return -1;
        } else {
            Node pre = now.pre;
            Node next = now.next;
            if(next == last){
                return now.val;
            } else {
                pre.next = next;
                pre = last.pre;
                pre.next = now;
                now.pre = pre;
                now.next = last;
            }
        }
        return now.val;
    }

    public void put(int key, int value) {
        Node temp = map.get(key);
        //覆盖
        if(temp != null){
            temp.val = value;
            Node pre = temp.pre;
            Node next = temp.next;
            pre.next = next;
            next.pre = pre;

            pre = last.pre;
            pre.next = temp;
            temp.next = last;
            last.pre = temp;
            return;
        }

        if(capacity > count){
            Node remove = first.next;
            //容量为0
            if(remove == last){
                return;
            }
            Node next = remove.next;
            next.pre = first;
            first.next = next;
            count--;
            map.remove(remove.key);
        }
        count++;

        Node now = new Node(value, key);
        map.put(key, now);
        Node pre =last.pre;
        pre.next = now;
        now.pre = pre;
        now.next = last;
        last.pre = now;
    }



    static class Node {
        Node pre;
        Node next;
        int val;
        int key;

        Node(int val, int key){
            this.val = val;
            this.key = key;
        }
    }
}
