package com.pf.leetcode.dp;

import java.util.Arrays;

public class MinimumIncompatibility {

    public static void main(String[] args) {
        MinimumIncompatibility minimumIncompatibility = new MinimumIncompatibility();
        int[] nums;
        int k;
        nums = new int[]{1,2,1,4};
        k = 2;
        System.out.println(minimumIncompatibility.minimumIncompatibility(nums, k));
    }
    int[] memo;
    int[] nums;
    int k;
    public int minimumIncompatibility(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int[] cnt = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
            if (cnt[nums[i]] > k) {
                return -1;
            }
        }
        Arrays.sort(nums);

        memo = new int[1 << nums.length];
        Arrays.fill(memo, -1);
        return minimumIncompatibility(0);
    }

    private int minimumIncompatibility(int state) {
        // 小于-1是后面不可达直接结束
        if (memo[state] != -1 || memo[state] < -1) {
            return memo[state];
        }
        if (state == (1 << nums.length) - 1) {
            return 0;
        }
        int choiceState = ((1 << nums.length) - 1) & (~state);
        int min = Integer.MAX_VALUE;
        for (int i = choiceState; i > 0 ; i = (i - 1) & choiceState) {
            if (Integer.bitCount(i) != k) {
                continue;
            }
            int pre = -1;
            boolean cal = true;
            int start = Integer.MAX_VALUE;
            int end = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((1 << j & i) == 0) {
                    continue;
                }
                if (pre == nums[j]) {
                    cal = false;
                    break;
                }
                pre = nums[j];
                start = Math.min(start, nums[j]);
                end = Math.max(end, nums[j]);
            }
            if (!cal) {
                continue;
            }
            int diff = end - start;
            int next = minimumIncompatibility(state | i);
            if (next < -1) {
                continue;
            }
            min = Math.min(min, diff + next);
        }
        return memo[state] = min == Integer.MAX_VALUE ? -2 : min;
    }
}
