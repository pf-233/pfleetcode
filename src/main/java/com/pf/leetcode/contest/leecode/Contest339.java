package com.pf.leetcode.contest.leecode;

import java.util.*;
public class Contest339 {
    public static void main(String[] args) {
        Contest339 contest339 = new Contest339();
        // [1,3,2,3]
// [3,5,5,1]
        int[] reward1 = new int[]{1,3,2,3};
        int[] reward2= new int[]{3,5,5,1};
        int k = 3;
        System.out.println(contest339.miceAndCheese(reward1, reward2, k));
    }

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int len = reward1.length;
        // 前i个数字，选择j个时的val
        int[][] dp = new int[len + 1][k + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < len; i++) {
            dp[i + 1][0] = dp[i][0] + reward2[i];
            for (int j = 0; j < Math.min(i + 1, k); j++) {
                // i  选了 j 个 是 i -1 选了 j - 1 个 这次选了1，   和 i个选了j 个这次选了2
                dp[i + 1][j + 1] = Math.max(dp[i][j] + reward1[i], dp[i][j + 1] + reward2[i]);
            }
        }
        return dp[len][k];
    }
}
