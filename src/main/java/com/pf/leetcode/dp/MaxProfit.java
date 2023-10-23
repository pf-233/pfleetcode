package com.pf.leetcode.dp;

import java.util.Arrays;

/**
 * author：11413
 * date：10/4/2023
 * Description:
 */
public class MaxProfit {
    public static void main(String[] args) {
        int k = 2;
        int[] prices;
        prices = new int[]{3,2,6,5,0,3};
        MaxProfit maxProfit = new MaxProfit();
        System.out.println(maxProfit.maxProfit(k, prices));
    }
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 偶数不持有， 奇数持有
        int[][] dp = new int[2][2 * k + 1];
        Arrays.fill(dp[0], Integer.MIN_VALUE / 2);
        Arrays.fill(dp[1], Integer.MIN_VALUE / 2);
        dp[0][0] = 0;
        dp[0][1] = - prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int now = i & 1;
            int pre = (i + 1) & 1;
            dp[now][0] = 0;
            dp[now][1] = Math.max(dp[pre][1], dp[pre][0] - prices[i]);
            for (int j = 1; j < k; j++) {
                dp[now][2 * j] = Math.max(dp[pre][2 * j], dp[pre][2 * j - 1] + prices[i]);
                dp[now][2 * j + 1] = Math.max(dp[pre][2 * j + 1], dp[pre][2 * j] - prices[i]);
                max = Math.max(max, dp[now][2 * j]);
                max = Math.max(max, dp[now][2 * j + 1]);
            }
            max = Math.max(max, dp[pre][2 * k - 1] + prices[i]);
        }
        return max;
    }
}
