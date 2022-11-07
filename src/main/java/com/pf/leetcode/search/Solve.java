package com.pf.leetcode.search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solve {
    public static void main(String[] args) {
        Solve solve = new Solve();
        solve.solve(new char[][] {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        });
        System.out.println("aa");
    }
    static int[][] dis = new int[][] {
            {-1, 0},
            {1,0},
            {0,-1},
            {0,1}
    };

    public void solve(char[][] board) {
        if(board == null) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        boolean[][] vis = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'O' && !vis[i][j]) {
                    bfs(i, j, board, vis);
                }
            }
        }
    }

    private void bfs(int nowRow, int nowCol, char[][] board, boolean[][] vis) {
        int row = board.length;
        int col = board[0].length;
        Queue<Integer> que = new LinkedList();
        que.offer(nowRow * col + nowCol);
        vis[nowRow][nowCol] = true;
        Stack<Integer> visedStack = new Stack();
        boolean isChange = true;
        while(!que.isEmpty()) {
            int now = que.poll();
            visedStack.push(now);
            int tmpRow = now / col;
            int tmpCol = now % col;
            if(tmpRow == 0 || tmpRow == row - 1 || tmpCol == 0 || tmpCol == col - 1) {
                isChange = false;
            }
            for(int i = 0; i < dis.length; i++) {
                int tmpRow1 = tmpRow + dis[i][0];
                int tmpCol1 = tmpCol + dis[i][1];
                if(tmpRow1 < 0 || tmpRow1 >= row || tmpCol1 < 0 || tmpCol1 >= col) {
                    continue;
                } else if(!vis[tmpRow1][tmpCol1] && board[tmpRow1][tmpCol1] == 'O'){
                    que.offer(tmpRow1 * col + tmpCol1);
                    vis[tmpRow1][tmpCol1] = true;
                }
            }
        }
        if(isChange) {
            while(!visedStack.isEmpty()) {
                int now = visedStack.pop();
                int tmpRow = now / col;
                int tmpCol = now % col;
                board[tmpRow][tmpCol] = 'X';
            }
        }

    }
}
