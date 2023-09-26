package com.pf.leetcode.dp;

import java.util.Arrays;

public class MinDifficulty {
    public static void main(String[] args) {
        MinDifficulty minDifficulty = new MinDifficulty();
        int[] jobDifficulty = null;
        jobDifficulty = new int[]{6,5,4,3,2,1};
        int d = 2;
        System.out.println(minDifficulty.minDifficulty(jobDifficulty, d));
    }
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (jobDifficulty.length < d) {
            return -1;
        }
        int[][] dp = new int[len][d];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int max = 0;
        //全部都是第一天
        for (int i = 0; i < len; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[i][0] = max;
        }
        // 第二个开始
        for (int i = 1; i < len; i++) {
            // 第二天开始
            for (int j = 1; j < d; j++) {
                max = 0;
                // k 是当前天的开始值， j 是因为要保证前面有j个数构成j天
                for (int k = i; k >= j; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[k - 1][j - 1]);
                }
            }
        }
        return dp[len - 1][d - 1];
    }
}
