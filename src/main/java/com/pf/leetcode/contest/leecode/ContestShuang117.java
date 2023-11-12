package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * author：panf
 * date：10/28/2023
 * Description:
 */
public class ContestShuang117 {
    public static void main(String[] args) {
        ContestShuang117 contest117 = new ContestShuang117();
//        System.out.println(contest117.distributeCandies(3,3));

//        int[][] values = new int[][] {
//                {8,5,2},
//                {6,4,1},
//                {9,7,3}
//        };
//        System.out.println(contest117.maxSpending(values));

        System.out.println(contest117.stringCount(4));
    }

    int mode = (int)1e9 +7;
    long[][][][] dp;
    public int stringCount(int n) {
        if (n < 4) {
            return 0;
        }
        dp = new long[n + 1][2][3][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long ans = 1L;
        // 4个以上肯定可以包含leet 那么先选这4个。其他的 n-4 个随便选   （重复的算一个所以有问题）
        // 1. n-4 里面没有leet 字母那么就是 26-3 里面随便选，这个不会重复  (n-4)^23 *


        // i 是第几个   这里还要选几个l e t
        // 我只要选满1个l 2个e 1个t就好了0,1 0,1,2 0,1  2*3*2=12个状态
        return (int)(cal(n, 1, 2, 1) % mode);
    }

    private long cal(int n, int lc, int ec, int tc) {
        int sum = lc + ec + tc;
        if (n == 0) {
            return sum == 0 ? 1L : 0L;
        }
        if (dp[n][lc][ec][tc] != -1) {
            return dp[n][lc][ec][tc];
        }


        if (sum > n) {
            return dp[n][lc][ec][tc] = 0L;
        }
        // 刚好只剩4个
        // if (sum == n && sum == 4) {
        //     return dp[n][lc][ec][tc] = 12L;
        // }
        // 随便选一个其他的
        long ans = (cal(n - 1, lc, ec, tc) * 23) % mode;
        ans += cal(n - 1, Math.max(0, lc - 1), ec, tc) % mode;
        ans += cal(n - 1, lc, Math.max(0, ec - 1), tc) % mode;
        ans += cal(n - 1, lc, ec, Math.max(tc - 1, 0)) % mode;
        ans %= mode;
        return dp[n][lc][ec][tc] = ans;
    }
// "eelt"  83943898
// "eetl"  823173261
// "elet"
// "elte"
// "etel"
// "etle"
// "leet"
// "lete"
// "ltee"
// "teel"
// "tele"
// "tlee"
    public long maxSpending(int[][] values) {
        int m = values.length;
        int n = values[0].length;
        long cost = 0L;
        PriorityQueue<int[]> que = new PriorityQueue<int[]>(Comparator.comparingInt(a -> values[a[0]][a[1]]));
        for (int i = 0; i < m; i++) {
            que.offer(new int[]{i, n - 1});
        }

        int day = 1;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int r = now[0];
            int c = now[1];
            cost += day++ * values[r][c];
            if (--c >= 0) {
                now[1] = c;
                que.offer(now);
            }
        }
        return cost;
    }

    public long distributeCandies(int n, int limit) {
        if (3L * limit < n) {
            return 0;
        }
        long ans = 0L;
        for (int i = 0; i <= limit; i++) {
            int n2 = Math.max(n - i - limit, 0);
            int n3 = Math.min(limit, n - i - n2);
            // (n - i - limit, limit), (n - i - limit + 1, limit - 1), ... (limit, n - i - limit)
            // (0, 0, 3) ，(0, 1, 2) ，(0, 2, 1) ，(0, 3, 0) ，(1, 0, 2) ，(1, 1, 1) ，(1, 2, 0) ，(2, 0, 1) ，(2, 1, 0) 和 (3, 0, 0)
            if (n2 >= 0 && n2 <= n3) {
                ans += Math.abs(n3 - n2) + 1;
            }
        }
        return ans;
    }
}
