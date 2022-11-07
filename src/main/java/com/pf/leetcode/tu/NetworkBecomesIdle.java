package com.pf.leetcode.tu;

import java.util.*;

public class NetworkBecomesIdle {
    int MAX = 100006;

    public static void main(String[] args) {
        NetworkBecomesIdle networkBecomesIdle = new NetworkBecomesIdle();
        int[][] edges = new int[][] {
                {0, 1},
                {1, 2}
        };
        int[] patience = new int[] {
                0,2,1
        };

        System.out.println(networkBecomesIdle.networkBecomesIdle(edges, patience));;
    }
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        Map<Integer, HashSet<Integer>> edgeMap = new HashMap();
        int[] time = new int[n];
        Arrays.fill(time, MAX);
        LinkedList<Integer> que = new LinkedList();
        HashSet<Integer> tmpSet = null;
        for (int i = 0; i < edges.length; i++) {
            tmpSet = edgeMap.getOrDefault(edges[i][0], new HashSet());
            tmpSet.add(edges[i][1]);
            edgeMap.put(edges[i][0], tmpSet);

            tmpSet = edgeMap.getOrDefault(edges[i][1], new HashSet());
            tmpSet.add(edges[i][0]);
            edgeMap.put(edges[i][1], tmpSet);
        }

        que.offer(0);
        time[0] = 0;
        HashSet<Integer> visSet = new HashSet();
        visSet.add(0);
        while (!que.isEmpty()) {
            int now = que.poll();
            tmpSet = edgeMap.get(now);
            tmpSet.removeAll(visSet);
            for (Integer next : tmpSet) {
                if (time[next] < MAX) {
                    continue;
                }
                time[next] = time[now] + 1;
                que.offer(next);
                visSet.add(next);
            }
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            int diff = time[i] * 2 - 1 ;
            int recount = diff / patience[i];
            int needTime = recount * patience[i] + time[i] * 2;
            max = Math.max(max, needTime + 1);
        }
        return max;
    }
}
