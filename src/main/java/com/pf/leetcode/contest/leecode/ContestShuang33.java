package com.pf.leetcode.contest.leecode;

public class ContestShuang33 {
    public static void main(String[] args) {
        ContestShuang33 contestShuang33 = new ContestShuang33();
        char[][] grid = new char[][] {
//                {'c','c','c','a'},
//                {'c','d','c','c'},
//                {'c','c','e','c'},
//                {'f','c','c','c'}
//        {'c','a','d'},
//        {'a','a','a'},
//        {'a','a','d'},
//        {'a','c','d'},
//        {'a','b','c'}
                {'a','b','b'},
                {'b','z','b'},
                {'b','b','a'}
        };
        System.out.println(contestShuang33.containsCycle(grid));
//        System.out.println(contestShuang33.rangeBitwiseAnd(1,2));
    }

    int[][] vis;
    char[][] grid;
    int n,m;
    int[][] dis = new int[][] {
            {1, 0},
            {0, 1},
            {-1,0},
            {0,-1}
    };
    public boolean containsCycle(char[][] grid) {
        boolean res = false;
        n = grid.length;
        m = grid[0].length;
        this.grid = grid;
        vis = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(vis[i][j] == 1) {
                    continue;
                }
                if(dfs(i, j, -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(int i, int j, int bi, int bj ) {
        char tmpch = grid[i][j];

        vis[i][j] = 1;
        for(int k = 0; k < dis.length; k++) {
            int tmpRow = i + dis[k][0];
            int tmpCol = j + dis[k][1];
            if(bi == tmpRow && bj == tmpCol) {
                continue;
            }
            if(tmpRow < 0 || tmpRow >= n || tmpCol < 0 || tmpCol >= m || grid[tmpRow][tmpCol] != tmpch) {
                continue;
            }
            if(vis[tmpRow][tmpCol] == 1 || dfs(tmpRow, tmpCol, i, j)) {
                return true;
            }
        }
        return false;
    }
}
