package com.pf.leetcode.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FrogPosition {

    public static void main(String[] args) {
        int n = 0;
        int[][] edges = null;
        int t = 0;
        int target = 0;
        n = 7;
        edges = new int[][] {
                {1,2},{1,3},{1,7},{2,4},{2,6},{3,5}
        };
        t = 2;
        target = 4;
        System.out.println(new FrogPosition().frogPosition(n, edges, t, target));
    }
    Set<Integer>[] sets;
    int target;
    public double frogPosition(int n, int[][] edges, int t, int target) {
        sets = new HashSet[n + 1];
        Arrays.setAll(sets, e -> new HashSet());
        this.target = target;
        for (int i = 0; i < edges.length; i++) {
            sets[edges[i][0]].add(edges[i][1]);
            sets[edges[i][1]].add(edges[i][0]);
        }
        Set<Integer> vis = new HashSet();
        vis.add(1);
        return dfs(1, t, 1, vis);
    }

    private double dfs(int now, int t, double rate, Set<Integer> vis) {
        // 刚好最后一步
        if (t == 0) {
            return now == target ? rate : 0;
        }
        Set<Integer> canVisit = new HashSet();
        canVisit.addAll(sets[now]);
        canVisit.removeAll(vis);
        int nextCnt = canVisit.size();
        // 没有下一步，当前是target 就可以提前结束否则0
        if (nextCnt == 0) {
            return now == target ? rate : 0;
        }
        double ans = 0;
        for (Integer next : canVisit) {
            vis.add(next);
            ans += dfs(next, t - 1, rate * (1.0 / nextCnt), vis);
            vis.remove(next);
        }
        return ans;
    }
}
