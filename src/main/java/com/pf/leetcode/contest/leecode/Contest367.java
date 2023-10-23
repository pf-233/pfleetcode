package com.pf.leetcode.contest.leecode;

/**
 * author：11413
 * date：10/15/2023
 * Description:
 */
public class Contest367 {

    public static void main(String[] args) {
        Contest367 contest367 = new Contest367();
        int[][] grid;
//        grid = new int[][] {
//                {1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000},
//                {1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000}
//        };
        grid = new int[][]{
                {1,2},
                {3,4}
        };
        grid = contest367.constructProductMatrix(grid);
        System.out.println(grid);
    }

    int mode = 12345;
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int len = n * m + 2;
        long[][] dp = new long[len][2];
        dp[0][0] = dp[len - 1][1] = 1L;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int now = i * m + j;
                dp[now + 1][0] = (dp[now][0] * grid[i][j]) % mode;
                int reverseInd = len - 1 - now;
                dp[reverseInd - 1][1] = (dp[reverseInd][1] * grid[n - 1 - i][m - 1 - j]) % mode;
            }
        }

        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int now = i * m + j;
                ans[i][j] = (int) ((dp[now][0] * dp[now + 2][1]) % mode);
            }
        }
        return ans;
    }

}
