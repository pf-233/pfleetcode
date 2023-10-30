package com.pf.leetcode.contest.leecode;

import java.util.Arrays;

/**
 * author：panf
 * date：10/29/2023
 * Description:
 */
public class Contest369 {

    public static void main(String[] args) {
        Contest369 contest369 = new Contest369();
//        int[] nums1 = null;
//        int[] nums2 = null;
//        nums1 = new int[]{2,0,2,0};
//        nums2 = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
//        System.out.println(contest369.minSum(nums1, nums2));

        int[] nums;
        int k;
        //  ans = 2;
//        nums = new int[]{1,2,6,9};
//        k = 8;
        nums = new int[] {13,34,0,13,9,19};
        k = 82;
        System.out.println(contest369.minIncrementOperations(nums, k));
    }

    public long minIncrementOperations(int[] nums, int k) {
        long cnt = 0L;
        int len = nums.length;
        //改变后 0 不大于k  1 >= k;
        // 0  i - 2 >= k   1 i - 1 >= k  2 本身>=k
        long[][] dp = new long[len][3];
        dp[0] = new long[]{0, 0, Math.max(k - nums[0], 0)};
        dp[1] = new long[]{0, dp[0][1], Math.max(k - nums[1], 0)};
        for (int i = 2; i < len; i++) {
            // i0 是i-2 >= k   i1 = i-1>=k
            dp[i][0] = dp[i - 2][2];
            dp[i][1] = dp[i - 1][2];
            // i-1能到的最小值 ， 此时是i-3=k, i - 2 = k ,i - 1 = k
            long can1 = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2]));
            // 等到1，和2的最小值   + 上这次的数据
            dp[i][2] = Math.max(0, k - nums[i]) + can1;
        }
        return Math.min(Math.min(dp[len - 1][0], dp[len - 1][1]), dp[len - 1][2]);
    }

    public long minSum(int[] nums1, int[] nums2) {
        long[] array1 = new long[] { Arrays.stream(nums1).filter(e -> e == 0).count(),  Arrays.stream(nums1).sum()};
        long[] array2 = new long[] { Arrays.stream(nums2).filter(e -> e == 0).count(),  Arrays.stream(nums2).sum()};
        if (array1[0] < array2[0]) {
            long[] temp = array1;
            array1 = array2;
            array2 = temp;
        }
        if (array1[0] == 0 && array2[0] == 0) {
            return array1[1] == array2[1] ? array1[1] : -1;
        } else if (array2[0] == 0) {
            return array1[0] + array1[1] <= array2[1] ? array2[1] : -1;
        } else {
            return Math.max(array1[0] + array1[1], array2[0] + array2[1]);
        }
    }
}
