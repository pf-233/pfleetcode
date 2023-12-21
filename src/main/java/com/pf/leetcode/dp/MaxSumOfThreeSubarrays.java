package com.pf.leetcode.dp;

/**
 * author：panf
 * date：11/19/2023
 * Description:
 */
public class MaxSumOfThreeSubarrays {

    public static void main(String[] args) {
        MaxSumOfThreeSubarrays maxSumOfThreeSubarrays = new MaxSumOfThreeSubarrays();
        int[] nums = new int[]{1,2,1,2,6,7,5,1};
        for (int i : maxSumOfThreeSubarrays.maxSumOfThreeSubarrays(nums, 2)) {
            System.out.print(i + ",");
        }
    }
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int len = nums.length;
        int[] arr = new int[len + 1];
        for (int i = 0; i < len; i++) {
            arr[i + 1] = arr[i] + nums[i];
        }

        int[][] dp = new int[arr.length][4];
        int max = 0;
        for (int i = 0; i < k; i++) dp[i] = new int[]{-1, i - k, 0, 0};
        int[] preMax = dp[0];
        for (int i = k; i < arr.length; i++) {
            int now = arr[i] - arr[i - k];
            dp[i] = new int[] {preMax[1], i - k, preMax[2] + now, preMax[3] + 1};
            if (dp[i - k + 1][2] > preMax[2]) {
                preMax = dp[i - k + 1];
            }
            if (dp[max][2] < dp[i][2]) {
                max = i;
            }
        }
        int[] ans = new int[dp[max][3]];
        for (int i = dp[max][3] - 1; i >= 0 && max >= 0; i--) {
            ans[i] = max;
            max = dp[max][1];
        }
        return ans;
    }
}
