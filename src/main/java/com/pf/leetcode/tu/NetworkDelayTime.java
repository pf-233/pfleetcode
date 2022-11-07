package com.pf.leetcode.tu;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] vis = new int[N + 1];
        Node[] nodes = new Node[N + 1];
        for(int i = 0; i < N; i++){
            nodes[i + 1] = new Node(i + 1);
        }
        for(int i = 0; i < times.length; i++){
            Edge edge = new Edge(times[i][0], times[i][1], times[i][2]);
            nodes[times[i][0]].adjes.add(edge);
        }

        PriorityQueue<Node> que = new PriorityQueue<Node>((a, b) -> a.time - b.time);
        que.offer(nodes[K]);
        vis[K] = 1;

        int max = 0;
        int visCount = 1;
        while(!que.isEmpty()){
            Node nowNode = que.poll();
            for(Edge edge : nowNode.adjes){
                Node tempNode = nodes[edge.to];
                if(vis[edge.to] == 0){
                    vis[edge.to] = 1;
                    visCount++;
                    que.offer(tempNode);
                    tempNode.time = nowNode.time + edge.time;
                    // System.out.println("vis: 0  "+tempNode.no + " : " + tempNode.time);
                } else {
                    tempNode.time = Math.min(nowNode.time + edge.time, tempNode.time);
                    //  System.out.println("vis: 1  "+tempNode.no + " : " + tempNode.time);
                }
            }

        }
        int res = -1;
        if(visCount == N) {
            for(int i = 1; i <= N; i++){
                res = Math.max(res, nodes[i].time);
            }
        }
        // System.out.println("==============" + res);
        return res;
    }

    class Node {
        int no;
        int time;
        List<Edge> adjes;
        Node(int no){
            this.no = no;
            time = 0;
            adjes = new ArrayList();
        }
    }

    class Edge {
        int time;
        int from;
        int to;
        Edge(int from, int to, int time){
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}
