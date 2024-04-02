package com.pf.leetcode.dp;

import java.util.Arrays;

/**
 * author：panf
 * date：3/15/2024
 * Description:
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int[] nums;
        nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(lengthOfLIS.lengthOfLIS(nums));
    }
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
        }
        Arrays.stream(dp).forEach(System.out::println);
        return dp[n - 1];
    }
}
