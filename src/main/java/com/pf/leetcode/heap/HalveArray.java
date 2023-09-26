package com.pf.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HalveArray {

    public static void main(String[] args) {
        HalveArray halveArray = new HalveArray();
//        int[] nums;
//        nums = new int[]{7,7,7,31,2};
//        System.out.println(halveArray.halveArray(nums));
//        int[] nums1 = new int[]{1,1,1,2,4,5};
//        int[] nums2 = new int[]{1,1,2,4,5};

        int[] nums1 = new int[]{1,1,1,5,4,5};
        int[] nums2 = new int[]{1,1,5,5,5};
        System.out.println(halveArray.lengthArray(nums1, nums2));
    }

    public int lengthArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        int max = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max;
    }

    public int halveArray(int[] nums) {
        long sum = 0L;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int times = 0;
        double target = 0;
        PriorityQueue<Double> que = new PriorityQueue<Double>((a, b) -> b == a ? 0 : b > a ? 1 : -1);
        for (int num : nums) {
            que.offer((double)num);
        }
        while (2 * target < sum) {
            double top = que.poll();
            top /= 2;
            target += top;
            times++;
            que.offer(top);
        }
        return times;
    }
}
