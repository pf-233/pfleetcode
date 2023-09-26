package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contest359 {

    public static void main(String[] args) {
        Contest359 contest359 = new Contest359();
        int n;
        List<List<Integer>> offers;
        n = 5;
        offers = Arrays.asList(Arrays.asList(0,0,1), Arrays.asList(0,2,2), Arrays.asList(1,3,2));
        System.out.println(contest359.maximizeTheProfit(n, offers));
    }
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // 第几间房子[1, n]; 0是哨兵
        int[] dp = new int[n + 1];
        Collections.sort(offers, (a, b) -> a.get(0) == b.get(0) ? a.get(1) - b.get(1) : a.get(0) - b.get(0));
        int top = 1;
        for (List<Integer> offer : offers) {
            int start = offer.get(0);
            int end = offer.get(1);
            int gold = offer.get(2);
            // [start, end]  所以得到前面的start - 1个房子的最大值， 对应dp[start];
            while (top <= start + 1) {
                dp[top] = Math.max(dp[top], dp[top - 1]);
                top++;
            }
            dp[end + 1] = Math.max(dp[end + 1], dp[start] + gold);
        }
        return dp[n];
    }
}
