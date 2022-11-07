package com.pf.leetcode.contest;

import java.util.*;

public class Test1 {
    int start;
    int end;
    Map<Integer, Node> nodeMap;

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        int[][] edges = new int[][]{
                {0,1},
                {1,2},
                {0,2}
        };

        System.out.println(test1.maxProbability(3, edges, new double[]{0.5,0.5,0.2},0,2));
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        nodeMap = new HashMap();
        this.start = start;
        this.end = end;
        Map<String, Edge> map = new HashMap();
        for(int i = 0; i < n; i++){
            Node temp = new Node(i);
            nodeMap.put(i, temp);
        }
        for(int i = 0; i < edges.length; i++){
            int pa = Math.max(edges[i][0], edges[i][1]);
            int pb = Math.min(edges[i][0], edges[i][1]);
            Edge tmp = new Edge(pa, pb, succProb[i]);
            Edge has = map.getOrDefault(pa +","+  pb, tmp);
            if(has.pro <= tmp.pro){
                map.put(pa +","+  pb, tmp);
            }
        }
        for(Edge ed : map.values()){
            Node temp = nodeMap.get(ed.pa);
            temp.adjEdges.add(ed);
            temp = nodeMap.get(ed.pb);
            temp.adjEdges.add(ed);
        }

        return bfs();
//        return dfs(temp, vis, 1);
    }

    double bfs(){
        List<Integer> list;
        Node now = nodeMap.get(start);
        now.pro = 1;
        TreeSet<Node> treeSet = new TreeSet<>((a,b)->  a.pro < b.pro ? 1 : -1);
        treeSet.add(now);
        while (!treeSet.isEmpty()){
            Node temp = treeSet.pollFirst();
            for(int i = 0; i < temp.adjEdges.size(); i++){
                Edge tempEdge = temp.adjEdges.get(i);
                int to = tempEdge.pa == temp.val ? tempEdge.pb : tempEdge.pa;
                Node next = nodeMap.get(to);
                if (tempEdge.pro * temp.pro > next.pro){
                    treeSet.remove(next);
                    next.pro = tempEdge.pro * temp.pro;
                    treeSet.add(next);
                }
            }
        }
        return nodeMap.get(end).pro;
    }
    double dfs(Node temp, int[] vis,  double pro){
        if(vis[temp.val] == 1){
            return 0.0;
        }
        if(temp.val == end){
            return pro;
        }
        vis[temp.val] = 1;
        double maxPro = 0;
        for(int i = 0; i < temp.adjEdges.size(); i++){
            int to = temp.adjEdges.get(i).pa == temp.val ? temp.adjEdges.get(i).pb : temp.adjEdges.get(i).pa;
            Node next = nodeMap.get(to);
            maxPro = Math.max(maxPro, dfs(next, vis, pro * temp.adjEdges.get(i).pro));
        }
        vis[temp.val] = 0;
        return maxPro;
    }

    class Node {
        private int val;
        private List<Edge> adjEdges;
        private double pro;
        Node(int val){
            this.val = val;
            pro = 0;
            adjEdges = new ArrayList();
        }
    }

    class Edge {
        private int pa;
        private int pb;
        private double pro;
        Edge(int a, int b, double p){
            pa = a;
            pb = b;
            pro = p;
        }
    }

}
