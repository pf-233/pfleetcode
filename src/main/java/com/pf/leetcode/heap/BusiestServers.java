package com.pf.leetcode.heap;

import java.util.*;
import java.util.stream.Collectors;

public class BusiestServers {
    public static void main(String[] args) {
        BusiestServers busiestServers = new BusiestServers();
        busiestServers.busiestServers(3, new int[]{1,2,3,4,5}, new int[]{5,2,3,3,3});
    }
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Node[] nodes = new Node[k];
        TreeSet<Integer> emptyNode = new TreeSet();
        int[] counts = new int[k];
        for (int i = 0; i < k; i++) {
            nodes[i] = new Node(i, 0);
            emptyNode.add(i);
        }
        PriorityQueue<Node> que = new PriorityQueue<Node>((a, b) -> a.time - b.time);
        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            while (!que.isEmpty() && que.peek().time <= arrival[i]) {
                emptyNode.add(que.poll().server);
            }
            if (emptyNode.size() == 0) {
                continue;
            }
            Integer server = emptyNode.ceiling(i % k);
            // 循环获取
            if (server == null) {
                server = emptyNode.first();
            }
            emptyNode.remove(server);
            Node tmp = nodes[server];
            tmp.time = arrival[i] + load[i];
            que.add(tmp);
            counts[server]++;
            max = Math.max(max, counts[server]);
        }

         List<Integer> list = new LinkedList();
         for (int i = 0; i < k; i++) {
             if (counts[i] == max) {
                 list.add(i);
             }
         }
         return list;
    }

    class Node {
        int server;
        int time;

        Node(int val, int t) {
            server = val;
            time = t;
        }
    }
}
