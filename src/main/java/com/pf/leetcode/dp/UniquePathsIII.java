package com.pf.leetcode.dp;

import java.util.Arrays;

public class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        int[][] grid;
        grid = new int[][] {{0,1},{2,0}};
        System.out.println(uniquePathsIII.uniquePathsIII(grid));
    }
    int[] start;
    int[][] index;
    int[][] memo;
    int obstacle;
    int n;
    int m;
    int[][] grid;
    int[][] direction = new int[][]{
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        index = new int[n][m];
        memo = new int[n * m][1 << (n * m)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                index[i][j] = i * m + j;
                Arrays.fill(memo[index[i][j]], -1);
                if (grid[i][j] == 1) {
                    start = new int[]{i, j};
                } else if (grid[i][j] == -1) {
                    obstacle |= 1 << index[i][j];
                }
            }
        }
        return dfs(start[0], start[1], 1 << index[start[0]][start[1]]);
    }

    private int dfs(int x, int y, int state) {
        if (memo[index[x][y]][state] != -1) {
            return memo[index[x][y]][state];
        }
        if (grid[x][y] == 2) {
            return memo[index[x][y]][state] = (state | obstacle) == memo[0].length - 1 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; i < direction.length; i++) {
            int nx = x + direction[i][0];
            int ny = y + direction[i][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m
                    && (1 << index[nx][ny] & state) == 0
                    && grid[nx][ny] != -1) {
                ans += dfs(nx, ny, 1 << index[nx][ny] | state);
            }
        }
        return memo[index[x][y]][state] = ans;
    }
}
