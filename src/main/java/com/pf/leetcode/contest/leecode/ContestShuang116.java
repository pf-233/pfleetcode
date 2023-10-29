package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.List;

/**
 * author：panf
 * date：10/28/2023
 * Description:
 */
public class ContestShuang116 {
    public static void main(String[] args) {
        ContestShuang116 contestShuang116 = new ContestShuang116();
//        System.out.println(contestShuang116.minChanges("1001"));
        System.out.println(contestShuang116.lengthOfLongestSubsequence(Arrays.asList(1,2,3,4,5), 9));
    }

    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (Integer num : nums) {
            for (int i = target; i >= 0; i--) {
                if (i - num >= 0 && dp[i - num] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - num] + 1);
                }
            }
        }
        return dp[target];
    }
    public int minChanges(String s) {
        int cnt = 0;
        int pre = 5;
        int precnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int now = s.charAt(i) - '0';
            if (pre != now) {
                if (precnt % 2 == 1) {
                    cnt++;
                    precnt = 0;
                } else {
                    precnt = 1;
                }
            } else {
                precnt++;
            }
            pre = now;
        }
        return cnt;
    }
}
