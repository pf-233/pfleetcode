package com.pf.leetcode.dp;

/**
 * author：panf
 * date：10/24/2023
 * Description:
 */
public class NumRollsToTarget {
    int modulo = (int) 1e9 + 7;

    public static void main(String[] args) {
        NumRollsToTarget numRollsToTarget = new NumRollsToTarget();
        System.out.println(numRollsToTarget.numRollsToTarget(2,6,7));
    }

    int modulo = (int) 1e9 + 7;
    public int numRollsToTarget(int n, int k, int target) {
        long[][] dp = new long[n + 1][target + 1];
        dp[0][0] = 1L;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int q = j; q <= target; q++) {
                    if (dp[i - 1][q - j] >= 1) {
                        dp[i][q] += dp[i - 1][q - j];
                        dp[i][q] %= modulo;
                    }
                }
            }
        }
        return (int) dp[n][target];
    }
}
