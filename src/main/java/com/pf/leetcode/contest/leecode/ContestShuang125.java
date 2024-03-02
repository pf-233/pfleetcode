package com.pf.leetcode.contest.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ContestShuang125 {
    public static void main(String[] args) {
        ContestShuang125 contest = new ContestShuang125();
        int[][] edges;
        int signalSpeed;
        edges = new int[][] {
        {0,1,1},
        {1,2,5},
        {2,3,13},
        {3,4,9},
        {4,5,2}
        };
        signalSpeed = 1;
        Arrays.stream(contest.countPairsOfConnectableServers(edges, signalSpeed)).forEach(e -> System.out.println(e));
    }

    List<int[]>[] edarr;
    int signalSpeed;
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        int[] ans = new int[n];
        edarr = new ArrayList[n];
        this.signalSpeed = signalSpeed;
        Arrays.setAll(edarr, e -> new ArrayList());
        for (int i = 0; i < edges.length; i++) {
            edarr[edges[i][0]].add(new int[] {edges[i][1], edges[i][2]});
            edarr[edges[i][1]].add(new int[] {edges[i][0], edges[i][2]});
        }
        for (int i = 0; i < n; i++) {
            ans[i] = cal(i);
        }
        return ans;
    }

    private int cal(int ind) {
        int beforeCnt = 0;
        int ans = 0;
        for (int[] node : edarr[ind]) {
            int nowCnt = dfs(node[0], node[1], ind);
            ans += nowCnt * beforeCnt;
            beforeCnt += nowCnt;
        }
        return ans;
    }

    private int dfs(int ind, int sum, int parent) {
        int ans = sum % signalSpeed == 0 ? 1 : 0;
        for (int[] next : edarr[ind]) {
            if (next[0] == parent) continue;
            ans += dfs(next[0], next[1] + sum, ind);
        }
        return ans;
    }

    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> que = new PriorityQueue();
        for (int i = 0; i < nums.length; i++) {
            que.offer(nums[i] + 0L);
        }
        int ans = 0;
        while (que.size() > 1 && que.peek() < k) {
            long min = que.poll();
            long max = que.poll();
            que.offer(min * 2 + max);
            ans++;
        }
        return ans;
    }
}
