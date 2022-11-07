package com.pf.leetcode.search;

public class MaximumRequests {

    public static void main(String[] args) {
        int n = 3;
        int[][] requests = new int[][] {
                {0,0},
                {1,2},
                {2,1}
        };
        MaximumRequests maximumRequests = new MaximumRequests();
        System.out.println(maximumRequests.maximumRequests(n, requests));
    }
    public int maximumRequests(int n, int[][] requests) {
        int[] counts = new int[n];
        return dfs(0, requests, counts, 0);
    }

    private int dfs(int deep, int[][] requests, int[] counts, int res) {
        if (deep >= requests.length) {
            int i = 0;
            for (; i < counts.length; i++) {
                if (counts[i] != 0) {
                    break;
                }
            }
            return i == counts.length ? res : 0;
        }
        int noUse = dfs(deep + 1, requests, counts, res);
        counts[requests[deep][0]]--;
        counts[requests[deep][1]]++;
        int use = dfs(deep + 1, requests, counts, res + 1);
        counts[requests[deep][0]]++;
        counts[requests[deep][1]]--;
        return Math.max(use, noUse);
    }
}
