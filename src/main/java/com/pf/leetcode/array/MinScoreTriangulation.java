package com.pf.leetcode.array;

import java.util.*;

public class MinScoreTriangulation {
       // 先枚举3个点构成三角形，然后剩下的被切分的区域就就所剩余的子问题，递归的切分他们。
    // 可以发现3角形的一条边可以把区域切分成连续的一个区块
    int[][] memo = null;
    public int minScoreTriangulation(int[] values) {
        int len = values.length;
        memo = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return minScoreTriangulation(values, 0, len - 1);
    }

    private int minScoreTriangulation(int[] values, int start, int end) {
        if (end - start + 1 < 3) {
            return 0;
        }
        if (memo[start][end] != -1) {
            return memo[start][end];
        }
        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++) {
            min = Math.min(min, minScoreTriangulation(values, start, i) + minScoreTriangulation(values, i, end) + values[start] * values[i] * values[end]);
        }
        memo[start][end] = min;
        return min;
    }
}
