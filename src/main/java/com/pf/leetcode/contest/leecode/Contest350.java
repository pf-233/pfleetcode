package com.pf.leetcode.contest.leecode;

import java.util.Arrays;

public class Contest350 {
    public static void main(String[] args) {
        Contest350 contest350 = new Contest350();
        int[] nums;
        nums = new int[]{2,3,6};
        System.out.println(contest350.specialPerm(nums));
    }
    int mode = (int)1e9 + 7;
    long ans = 0L;
    long[][] memo;
    int[] nums;
    // 与什么想关联
    int[] next;
    public int specialPerm(int[] nums) {
        ans = 0L;
        Arrays.sort(nums);
        this.nums = nums;
        int len = nums.length;
        int state = 1 << nums.length;
        memo = new long[nums.length][state];
        next = new int[len];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1L);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j)
                    continue;
                if (nums[i] % nums[j] == 0 ||  nums[j] % nums[i] == 0) {
                    next[i] |= 1 << j;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            ans += dfs(i, 1 << i);
            ans %= mode;
        }
        return (int) ans;
    }

    private long dfs(int now, int state) {
        if (memo[now][state] != -1L) {
            return memo[now][state];
        }
        if (state == ((1 << nums.length) - 1)) {
            return 1;
        }
        long ans = 0L;
        for (int i = 0; i < nums.length; i++) {
            // 当前不在里面且now的下一个可以是这个
            int step = 1 << i;
            if ((step & state) == 0 && (next[now] & step) > 0) {
                ans += dfs(i, state | (1 << i));
                ans %= mode;
            }
        }
        return memo[now][state] = ans;
    }
}
