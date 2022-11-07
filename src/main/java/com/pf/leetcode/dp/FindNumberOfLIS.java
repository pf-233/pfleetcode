package com.pf.leetcode.dp;

public class FindNumberOfLIS {
    public static void main(String[] args) {
        FindNumberOfLIS findNumberOfLIS = new FindNumberOfLIS();
        System.out.println(findNumberOfLIS.findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return len;
        }
        int[] dp = new int[len + 1];
        dp[1] = 1;
        int max = 1;
        int count = 1;
        for(int i = 2; i <= len; i++) {
            dp[i] = 1;
            for(int j = 1; j < i; j++) {
                if(nums[i - 1] > nums[j - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if(max < dp[i]) {
                        max = dp[i];
                        count = 1;
                    } else if (max == dp[j] + 1) {
                        count++;
                    }
                }
            }
        }
        System.out.println(max);
        if(max == 1) {
            return len;
        }
        return max == 1 ? len : count;
    }
}
