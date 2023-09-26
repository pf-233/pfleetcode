package com.pf.leetcode.contest.leecode;

import java.util.Arrays;

public class Contest349 {

    public static void main(String[] args) {
        Contest349 contest349 = new Contest349();
        int[] nums;
        int x;
        int ans;
        nums = new int[]{20,1,15};
        x = 5;
        ans = 13;
        nums = new int[]{15,150,56,69,214,203};
        x = 42;
        ans = 298;
        System.out.println(contest349.minCost1(nums, x));

    }

    public long minCost1(int[] nums, int x) {
        long min = Long.MAX_VALUE;
        long sum = 0L;
        long step = 0L;
        int len = nums.length;
        int[] changeNums = Arrays.copyOf(nums, len);;
        for (int diff = 0; diff < len; diff++) {
            sum = 0L;
            for (int i = 0; i < len; i++) {
                changeNums[i] = Math.min(changeNums[i], nums[(i + diff) % len]);
                sum += changeNums[i];
            }
            min = Math.min(min, sum + step);
            step += x;
        }
        return min;
    }


    public long minCost(int[] nums, int x) {
        long min = Long.MAX_VALUE;
        boolean[] vis = new boolean[nums.length];
        long sum = 0;
        long mincost = nums[0];
        int minInd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < mincost) {
                minInd = i;
                mincost = nums[i];
            }
        }
        long changeCost = mincost + x;
        for (int diff = 0; diff < nums.length; diff++) {
            long thisCost = cost(nums, diff, vis);
            min = Math.min(min, thisCost + sum);
            sum +=  x + mincost;
            vis[(minInd - diff + nums.length) % nums.length] = true;
        }
        return min;
    }

    private long cost(int[] nums, int diff, boolean[] vis) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int ind = (i - diff + nums.length) % nums.length;
            if (vis[ind]) {
                continue;
            }
            sum += nums[i];
        }
        return sum;
    }
}
