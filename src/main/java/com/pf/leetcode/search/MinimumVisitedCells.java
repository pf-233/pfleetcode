package com.pf.leetcode.search;

import com.pf.leetcode.utils.ArrayUtils;
import com.pf.leetcode.utils.InputOutputUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * author：panf
 * date：3/22/2024
 * Description:
 */
public class MinimumVisitedCells {

    public static void main(String[] args) {
        MinimumVisitedCells minimumVisitedCells = new MinimumVisitedCells();
        String arr;
        arr = "[[2,1,0],[1,0,0]]";
        arr = "[[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]";
        arr = "[[2, 3, 0, 0, 1, 0, 1, 2, 1], [0, 1, 3, 3, 0, 0, 2, 2, 0]]";
        arr = "[[1, 3, 1, 3, 3, 2], [1, 1, 0, 3, 2, 2], [1, 3, 2, 0, 0, 3], [0, 2, 1, 0, 3, 1], [2, 0, 2, 0, 3, 0], [3, 0, 0, 2, 3, 0], [3, 2, 1, 2, 0, 0]]";
        System.out.println(minimumVisitedCells.minimumVisitedCells(InputOutputUtil.get2DimensionArray(arr)));
    }
//  0  1  2  3  4  5
//[[1, 3, 1, 3, 3, 2]   0
// [1, 1, 0, 3, 2, 2]   1
// [1, 3, 2, 0, 0, 3]   2
// [0, 2, 1, 0, 3, 1]   3
// [2, 0, 2, 0, 3, 0]   4
// [3, 0, 0, 2, 3, 0]   5
// [3, 2, 1, 2, 0, 0]]  6
    int MAX = (int)1e6;
    public int minimumVisitedCells(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], MAX);
        dp[0][0] = 1;
        List<int[]> list = new LinkedList();
        list.add(new int[]{0, 0});
        int[] thisRowMaxCol = new int[n];
        int[] thisColMaxRow = new int[m];
        Arrays.fill(thisRowMaxCol, -1);
        Arrays.fill(thisColMaxRow, -1);
        thisColMaxRow[0] = 0;
        thisRowMaxCol[0] = 0;
        while (!list.isEmpty()) {
            list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            List<int[]> next = new LinkedList();
            for (int[] temp : list) {
                int i = temp[0];
                int j = temp[1];
                int nextStep = Math.min(j + grid[i][j], m - 1);
                int k = Math.max(thisRowMaxCol[i], j) + 1;
                for (; k <= nextStep; k++) {
                    if (dp[i][k] == MAX) {
                        dp[i][k] = dp[i][j] + 1;
                        System.out.println(String.format("(%s, %s)  --> (%s, %s) dp[i][k]: %s", i, j, i, k, dp[i][k]));
                        next.add(new int[]{i, k});
                    }
                }
                thisRowMaxCol[i] = Math.max(thisRowMaxCol[i], nextStep);

                nextStep = Math.min(i + grid[i][j], n - 1);
                k = Math.max(thisColMaxRow[j], i) + 1;
                for (; k <= nextStep; k++) {
                    if (dp[k][j] == MAX) {
                        dp[k][j] = dp[i][j] + 1;
                        System.out.println(String.format("(%s, %s)  --> (%s, %s) dp[k][j]: %s", i, j, k, j, dp[k][j]));
                        next.add(new int[]{k, j});
                    }
                }
                thisColMaxRow[j] = Math.max(thisColMaxRow[j], nextStep);
                if (dp[n - 1][m - 1] < MAX) return dp[n - 1][m - 1];
            }
            list = next;
        }
        return -1;
    }
}
