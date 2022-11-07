package com.pf.leetcode.search;


import java.util.LinkedList;
import java.util.Queue;

public class NumIslands {
    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        char[][] grid = new char[][] {
                {'1', '0','1','1','0','1','1'}
        };
        System.out.println(numIslands.numIslands(grid));

    }
    int[][] dis = new int[][] {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) {
            return 0;
        }
        int col = grid[0].length;
        boolean[][] vis = new boolean[row][col];

        int count = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    bfs(grid, vis, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private  void bfs(char[][] grid, boolean[][] vis, int indr, int indc) {
        int row = grid.length;
        int col = grid[0].length;

        Queue<Integer> que = new LinkedList();
        que.offer(indr * row + indc);
        vis[indr][indc] = true;
        while(!que.isEmpty()) {
            int tmp = que.poll();
            int tmpr = tmp / row;
            int tmpc = tmp % row;
            for(int i = 0; i < 4; i++) {
                int nextr = tmpr + dis[i][0];
                int nextc = tmpc + dis[i][1];
                if (nextr >= 0 && nextr < row && nextc >= 0 && nextc < col && !vis[nextr][nextc] && grid[nextr][nextc] == '1') {
                    que.offer(nextr * row + nextc);
                    vis[nextr][nextc] = true;
                }
            }
        }
        new String(new char[2], 1, 2);
    }
}
