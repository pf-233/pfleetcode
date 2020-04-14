package com.pf.leetcode.other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LFU缓存
 *
 * @author pf
 * @date 2020-04-05 13:54
 **/
class LFUCache {
    private Map<Integer, Node> map;
    private Map<Integer, Node> plMap;

    int limit;
    int count;
    int minpl;

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(0);
//        lfuCache.put(1,1);
//        lfuCache.put(2,2);
//        System.out.println(lfuCache.get(1));
//        lfuCache.put(3,3);
//        System.out.println(lfuCache.get(2));
//        System.out.println(lfuCache.get(3));
//        lfuCache.put(4,4);
//        System.out.println(lfuCache.get(1));
//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));

//        lfuCache.put(3,1);
//        lfuCache.put(2,1);
//        lfuCache.put(2,2);
        lfuCache.put(0,0);
        System.out.println(lfuCache.get(0));
    }
    public LFUCache(int capacity) {
        map= new HashMap(capacity);
        plMap= new HashMap(capacity);
        limit=capacity;
        count=0;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null){
            return -1;
        }
        node.pl++;
        //更新当前节点前后节点的位置
        if(node.pre!=null){
            node.pre.next=node.next;
        } else {
            //他是头节点 把下一个节点当头节点
            plMap.put(node.pl-1, node.next);
            //如果次数没有节点就把最小节点更新
            if(node.next==null && minpl == node.pl-1){
                minpl++;
            }
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        //获取新的位置的头节点
        Node plOldFirstNode = plMap.get(node.pl);
        if(plOldFirstNode!=null){
            plOldFirstNode.pre=node;
        }
        node.pre=null;
        node.next=plOldFirstNode;
        plMap.put(node.pl, node);
        return node.val;
    }

    public void put(int key, int value) {
        if (limit == 0){
            return;
        }
        Node node = map.get(key);
        if(node!=null){
            node.val=value;
            get(key);
        } else {
            node = new Node(key,value);
            //打到限制把最小的次数那个末尾去掉
            if(limit == count){
                //获取头节点
                Node plOldFirstNode = plMap.get(minpl);
                if(plOldFirstNode.next==null){
                    plMap.put(minpl,null);
                }
                while(plOldFirstNode.next!=null){
                    plOldFirstNode = plOldFirstNode.next;
                }
                if (plOldFirstNode.pre!=null){
                    plOldFirstNode.pre.next=null;
                }
                map.remove(plOldFirstNode.key);
                count--;
            }
            count++;
            minpl=1;
            //获取新的位置的头节点
            Node plOldFirstNode = plMap.get(minpl);
            if(plOldFirstNode!=null){
                plOldFirstNode.pre=node;
            }
            node.next=plOldFirstNode;
            plMap.put(minpl, node);
            map.put(key, node);
        }
    }

    class Node{
        private Integer key;
        private Integer val;
        private Integer pl;
        private Node next;
        private Node pre;
        Node(int key,int val){
            this.key=key;
            this.val = val;
            this.pl=1;
        }
    }
}