package com.pf.leetcode.dp;

/**
 * author：panf
 * date：2023/12/26
 * Description:
 */
public class MaxStudents {

    int m;
    int n;

    public static void main(String[] args) {
        MaxStudents maxStudents = new MaxStudents();
        char[][] seats = new char[][]{
                {'#','.','#','#','.','#'},
                {'.','#','#','#','#','.'},
                {'#','.','#','#','.','#'}

        };
        System.out.println(maxStudents.maxStudents(seats));
    }
    public int maxStudents(char[][] seats) {
        m = seats.length;
        n = seats[0].length;
        int row = (1 << m) + 1;
        int col = 1 << n;
        int[][] dp = new int[row][col];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < col; j++) {
                int cnt = Integer.bitCount(j);
                if (checkRow(i, j, seats)) {
                    for (int k = 0; k < col; k++) {
                        if (i == 0 || checkTwoRow(i, j, k, seats))
                            dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][k] + cnt);
                    }
                }
                max = Math.max(max, dp[i + 1][j]);
            }
        }
        return max;
    }

    private boolean checkTwoRow(int i, int j, int k, char[][] seats) {
        if (!checkRow(i - 1, k, seats)) {
            return false;
        }
        return ((j >> 1) & k) == 0 && ((j << 1) & k) == 0;
    }

    private boolean checkRow(int i, int j, char[][] seats) {
        int ind = n - 1;
        int pre = 0;
        while (j > 0) {
            int mode = j & 1;
            if ((seats[i][ind] == '#' && mode == 1 ) || (mode == 1 && pre == 1)) {
                return false;
            }
            ind--;
            j >>= 1;
            pre = mode;
        }
        return true;
    }
}
