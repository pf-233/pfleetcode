package com.pf.leetcode.contest.leecode;

import java.util.*;

public class ContestShuang127 {
    public static void main(String[] args) {
        ContestShuang127 contest = new ContestShuang127();
        int[] nums;
        int k;
//        nums = new int[]{2,1,8};
//        k = 10;
        nums = new int[] {32,1,25,11,2};
        k = 59;
        // 4
        System.out.println(contest.minimumSubarrayLength(nums, k));
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        if (k == 0) return 1;
        int min = -1;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int j = i;
            for (; j < nums.length && sum < k; j++) {
                sum |= nums[j];
            }
            int diff = j - i;
            if (sum >= k) min = min == -1 ? diff : Math.min(diff, min);
        }
        return min;
    }
}
