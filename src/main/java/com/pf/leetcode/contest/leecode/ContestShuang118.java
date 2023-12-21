package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * author：panf
 * date：10/28/2023
 * Description:
 */
public class ContestShuang118 {
    public static void main(String[] args) {
        ContestShuang118 contest = new ContestShuang118();
        int[] nums;
        nums = new int[]{272, 482, 115, 925, 983};
        System.out.println(contest.findMaximumLength(nums));
//        int[] prices;
//        prices = new int[] {26,18,6,12,49,7,45,45};
//        System.out.println(contest.minimumCoins(prices));
//
//        int n;
//        int m;
//        int[] hbars;
//        int[] vbars;
//        n = 2;
//        m = 3;
//        hbars = new int[]{2, 3};
//        vbars = new int[]{2, 3, 4};

//        n = 1;
//        m = 1;
//        hbars = new int[]{2};
//        vbars = new int[]{2};

//        n = 3;
//        m = 2;
//        hbars = new int[]{3,2,4};
//        vbars = new int[]{3,2};
//        System.out.println(contest.maximizeSquareHoleArea(n, m, hbars, vbars));


    }

    public int findMaximumLength(int[] nums) {
        long sum = 0L;
        Stack<Long> stack = new Stack();
        stack.push(nums[0] + 0L);
        sum = 0L;
        for (int i = 1; i < nums.length; i++) {
            if (sum < stack.peek()) {
                sum += nums[i];
            }
            if (sum >= stack.peek()) {
                stack.push(sum);
                sum = 0L;
            }
        }
        if (sum > 0) {
            stack.push(stack.pop() + sum);
        }
        return stack.size();
    }

    public int minimumCoins(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 100000005);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            // dp[i] 是之前可以白拿的最小值     i-1 是之前可以到的+这次我选择买
            int buyCost = dp[i - 1] + prices[i - 1];
            dp[i] = Math.min(dp[i], buyCost);
            // 接下来的i个我可以选择白嫖
            for (int j = 1; j <= i && i + j < dp.length; j++) {
                dp[i + j] = Math.min(dp[i + j], buyCost);
            }
        }
        return dp[len];
    }

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int hlen = hBars.length;
        int vlen = vBars.length;
        int max = 1;
        Set<Integer> lens = new HashSet();
        for (int i = 0; i < hlen; i++) {
            // 只移除i
            int start = i;
            // 如果是连续的就可以一起移除[start, end]  len = [start - 1, end + 1];
            // 最终是i
            while (i + 1 < hlen && hBars[i + 1] - hBars[i] == 1) {
                i++;
                lens.add(i - start + 2);
            }
            lens.add(i - start + 2);
            if (i != start) i--;
        }

        for (int i = 0; i < vlen; i++) {
            // 只移除i
            int start = i;
            // 如果是连续的就可以一起移除[start, end]  len = [start - 1, end + 1];
            // 最终是i
            while (i + 1 < vlen && vBars[i + 1] - vBars[i] == 1) {
                i++;
                int len = i - start + 2;
                if (lens.contains(len)) {
                    max = Math.max(max, len * len);
                }
            }
            int len = i - start + 2;
            if (lens.contains(len)) {
                max = Math.max(max, len * len);
            }
            if (i != start) i--;
        }
        return max;
    }
}
