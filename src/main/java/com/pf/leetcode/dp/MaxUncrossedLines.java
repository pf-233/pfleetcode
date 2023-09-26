package com.pf.leetcode.dp;

public class MaxUncrossedLines {
    public static void main(String[] args) {
        MaxUncrossedLines maxUncrossedLines = new MaxUncrossedLines();
        int[] nums1 = new int[] {1,4,2};
        int[] nums2 = new int[] {1,2,4};
        System.out.println(maxUncrossedLines.maxUncrossedLines(nums1, nums2));
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j] + 1, dp[i + 1][j]);
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j], dp[i + 1][j]);
                }
            }
        }
        return dp[len][len];
    }
}
