package com.pf.leetcode.contest.leecode;

import java.util.PriorityQueue;

public class ContestShuang102 {
    int[][] adj;
    int len;

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {0, 2, 5},
                {0, 1, 2},
                {1,2,1},
                {3,0,3}
        };
        ContestShuang102 contestShuang102 = new ContestShuang102(4, edges);
        contestShuang102.shortestPath(3, 2);
    }
    public ContestShuang102(int n, int[][] edges) {
        adj = new int[n][n];
        len  = n;
        for (int[] temp : edges) {
            adj[temp[0]][temp[1]] = temp[2];
        }
    }

    public void addEdge(int[] edge) {
        adj[edge[0]][edge[1]] = edge[2];
    }

    public int shortestPath(int node1, int node2) {
        boolean[] visit = new boolean[len];
        // [tonode, cost]
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        que.offer(new int[]{node1, 0});
        visit[node1] = true;
        int min = -1;
        while (!que.isEmpty()) {
            int[] nowArr = que.poll();
            int nowNode = nowArr[0];
            int cost = nowArr[1];
            for (int i = 0; i < len; i++) {
                if (!visit[i] && adj[nowNode][i] > 0) {
                    System.out.println("i:" + i + "dis:" + (cost + adj[nowNode][i]));
                    int nowCost = cost + adj[nowNode][i];
                    que.offer(new int[]{i, nowCost});
                    visit[i] = (i != node2) && true;
                    if (i == node2) {
                        min = min == -1 ? nowCost : Math.min(min, nowCost);
                    }
                }
            }
        }
        return min;
    }


}
