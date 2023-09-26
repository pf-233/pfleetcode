package com.pf.leetcode.contest.leecode;

public class Contest344 {
    int n;
    int[] cost;
    int addCnt;
    public int minIncrements(int n, int[] cost) {
        this.n = n;
        this.addCnt = 0;
        this.cost = cost;
        return addCnt;
    }

    private int dfs(int nowNode) {
        if (nowNode > n) {
            return 0;
        }
        int left = dfs(2 * nowNode);
        int right = dfs(2 * nowNode + 1);

        this.addCnt += Math.abs(left - right);
        return 0;
    }

    public static void main(String[] args) {

    }
}
