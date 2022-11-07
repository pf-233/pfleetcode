package com.pf.leetcode.contest.leecode;

public class Contest289 {
    public static void main(String[] args) {
        Contest289 contest289 = new Contest289();
//        contest289.digitSum("11111222223", 3);
        //8
        int[][] grid = new int[][] {
                {534,575,625,84,20,999,35},{208,318,96,380,819,102,669}
        };
        System.out.println(contest289.maxTrailingZeros(grid));
    }

    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] countRow = new int[m][n][2];
        int[][][] countCol = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count2 = getCount(grid[i][j], 2);
                int count5 = getCount(grid[i][j], 5);
                if (j == 0) {
                    countRow[i][j][0] = count2;
                    countRow[i][j][1] = count5;
                } else {
                    countRow[i][j][0] = countRow[i][j - 1][0] + count2;
                    countRow[i][j][1] = countRow[i][j - 1][1] + count5;
                }
                if (i == 0) {
                    countCol[i][j][0] = count2;
                    countCol[i][j][1] = count5;
                } else {
                    countCol[i][j][0] = countCol[i - 1][j][0] + count2;
                    countCol[i][j][1] = countCol[i - 1][j][1] + count5;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(countRow[i][j][0] + ",");
            }
            System.out.println();
        }
        System.out.println("===========");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(countRow[i][j][1] + ",");
            }
            System.out.println();
        }      System.out.println("===========");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(countCol[i][j][0] + ",");
            }
            System.out.println();
        }      System.out.println("===========");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(countCol[i][j][1] + ",");
            }
            System.out.println();
        }      System.out.println("===========");



        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = getZero(countRow, countCol, i, j);
                max = Math.max(tmp, max);
            }
        }
        return max;

    }

    private int getZero(int[][][] countRow, int[][][] countCol, int row, int col) {
        int m = countRow.length;
        int n = countRow[0].length;
        //上
        int upcount2 = countCol[row][col][0];
        int upcount5 = countCol[row][col][1];
        // 左
        int leftcount2 = col > 0 ? countRow[row][col - 1][0] : 0;
        int leftcount5 = col > 0 ? countRow[row][col - 1][1] : 0;
        //下
        int downcount2 = row > 0 ? countCol[m - 1][col][0] - countCol[row - 1][col][0] : countCol[m - 1][col][0];
        int downcount5 = row > 0 ? countCol[m - 1][col][1] - countCol[row - 1][col][1] : countCol[m - 1][col][1];
        //右
        int rightcount2 = col > 0 ? countRow[row][n - 1][0] - countRow[row][col][0] : 0;
        int rightcount5 = col > 0 ? countRow[row][n - 1][1] - countRow[row][col][1] : 0;

        //上左
        int max = 0;
        int tmpcount = Math.min(upcount2 + leftcount2, upcount5 + leftcount5);
        max = Math.max(max, tmpcount);
        // 上右
        tmpcount = Math.min(upcount2 + rightcount2, upcount5 + rightcount5);
        max = Math.max(max, tmpcount);
        // 下左
        tmpcount = Math.min(downcount2 + leftcount2, downcount5 + leftcount5);
        max = Math.max(max, tmpcount);
        // 下右
        tmpcount = Math.min(downcount2 + rightcount2, downcount5 + rightcount5);
        max = Math.max(max, tmpcount);
        return max;
    }

    private int getCount(int val, int mod) {
        int ans = 0;
        while (val % mod == 0) {
            ans++;
            val /= mod;
        }
        return ans;
    }



    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        while (s.length() > k) {
            for (int i = 0; i < s.length(); i += k) {
                String tmp = s.substring(i, Math.min(i + k, s.length()));
                int sum = 0;
                for (int j = 0; j < tmp.length(); j++) {
                    sum += tmp.charAt(j) - '0';
                }
                sb.append(sum);
            }
            s = sb.toString();
            sb.setLength(0);
        }
        return s;
    }
}
