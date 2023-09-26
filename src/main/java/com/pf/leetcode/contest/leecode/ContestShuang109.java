package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContestShuang109 {


    public static void main(String[] args) {
        ContestShuang109 contestShuang109 = new ContestShuang109();
        int n;
        int x;
//        n = 10;
//        x = 2;
        n = 4;
        x = 1;
        System.out.println(contestShuang109.numberOfWays(n, x));

//        int[] nums;
//        int x;
//        nums = new int[]{2,3,6,1,9,2};
//        x = 5;
//        System.out.println(contestShuang109.maxScore(nums, x));
    }

    int mode = (int) 1e9 + 7;
    long[][] memo;
    int[] pow;
    public int numberOfWays(int n, int x) {
        pow = new int[n + 1];
        Arrays.fill(pow, n + 10);
        memo = new long[n + 1][n + 1];
        for (int i = 0; i < pow.length ; i++) {
            pow[i] = (int)Math.pow(i, x);
            if (pow[i] >= n) {
                break;
            }
        }
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1L);
        }

        return (int) dfs(n, 0);
    }

    private long dfs(int n, int ind) {
        if (memo[n][ind] != -1) {
            return memo[n][ind];
        }
        if (n == 0) {
            return 1;
        }
        long ans = 0L;
        for (int i = ind + 1; i <= n && pow[i] <= n; i++) {
            ans += dfs(n - pow[i], i);
            ans %= mode;
        }
        return memo[n][ind] = ans % mode;
    }

    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        int oddOrEven = nums[0] & 1;
        int next = (oddOrEven + 1) & 1;
        dp[0][oddOrEven] = nums[0];
        dp[0][next] = -1L << 50;
        for (int i = 1; i < n; i++) {
            oddOrEven = nums[i] & 1;
            next = (oddOrEven + 1) & 1;
            dp[i][oddOrEven] = Math.max(dp[i - 1][oddOrEven], dp[i - 1][next] - x) + nums[i];
            dp[i][next] = dp[i - 1][next];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

}
