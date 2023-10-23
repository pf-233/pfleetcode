package com.pf.leetcode.dp;

import java.util.Arrays;

/**
 * author：11413
 * date：10/12/2023
 * Description:
 */
public class FindSquare {
    public static void main(String[] args) {
        int[][] matrix;
        FindSquare findSquare = new FindSquare();
//        matrix = new int[][] {
//                {1,0,1},
//        {0,0,1},
//        {0,0,1}
//        };
        matrix = new int[][] {
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 0, 1, 1, 1}
        };

        int[] ans = findSquare.findSquare(matrix);
        Arrays.stream(ans).forEach(System.out::println);
    }

    public int[] findSquare(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] cntr = new int[r][c];
        int[][] cntc = new int[r][c];
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    continue;
                }
                cntr[i][j] = (j == c - 1 ? 0 : cntr[i][j + 1]) + 1;
            }
        }

        for (int i = c - 1; i >= 0; i--) {
            for (int j = r - 1; j >= 0; j--) {
                if (matrix[j][i] == 1) {
                    continue;
                }
                cntc[j][i] = (j == r - 1 ? 0 : cntc[j + 1][i]) + 1;
            }
        }

        int[] ans = null;
        int[][] dp = new int[r][c];
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    continue;
                }
                int min = Math.min(cntr[i][j], cntc[i][j]);
                if (i < r - 1 && j < c - 1) {
                    min = Math.min(min, dp[i + 1][j + 1] + 1);
                }
                dp[i][j] = min;
                if (ans == null) {
                    ans = new int[] {i, j, min};
                } else if (ans[2] <= min) {
                    ans[0] = i;
                    ans[1] = j;
                    ans[2] = min;
                }
            }
        }
        return ans == null ? new int[0] : ans;
    }
}
