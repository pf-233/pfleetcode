package com.pf.leetcode.other;

import java.util.Arrays;

public class MaxFrequency {

    public static void main(String[] args) {
        MaxFrequency frequency = new MaxFrequency();
        System.out.println(frequency.maxFrequency(new int[]{1,4,8,13}, 5));
    }
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = 0;
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + (nums[i] - nums[i - 1]) * i;
        }

        int left = 0;
        int right = 1;
        int res = 1;
        while (left <= right && right < len) {
            if (sum[right] - sum[left] - (nums[right] - nums[left]) * left <= k) {
                res = Math.max(res, right - left + 1);
                right++;
            } else {
                left++;
            }
        }
        return res;
    }
}
