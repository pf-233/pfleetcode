package com.pf.leetcode.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FlipChess {
    public static void main(String[] args) {
        FlipChess flipChess = new FlipChess();
        String[] chessboard;
        chessboard = new String[] {"....X.","....X.","XOOO..","......","......"};
        System.out.println(flipChess.flipChess(chessboard));
    }
    int[][] direction = new int[][] {
            {1,0},{-1,0},{1,1},{-1,1},{0, 1},{1,-1},{-1,-1},{0, -1}
    };

    int[][] grid;
    int row;
    int col;
    Map<Character, Integer> map = new HashMap() {
        {
            put('X', 1);
            put('O', 0);
            put('.', -1);
        }
    };

    public int flipChess(String[] chessboard) {
        row = chessboard.length;
        col = chessboard[0].length();
        grid = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = map.get(chessboard[i].charAt(j));
            }
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == -1)
                    max = Math.max(max, bfs(i, j));
            }
        }
        return max;
    }

    private int bfs(int r, int c) {
        int[][] grids = new int[row][col];
        for (int i = 0; i < row; i++)
            grids[i] = Arrays.copyOf(grid[i], col);
        int cnt = 0;
        LinkedList<int[]> queue = new LinkedList();
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.removeFirst();
            for (int i = 0 ; i < direction.length; i++) {
                LinkedList<int[]> next = new LinkedList();
                int nextr = now[0] + direction[i][0];
                int nextc = now[1] + direction[i][1];
                while (nextr >= 0 && nextr < row && nextc >= 0 && nextc < col && grids[nextr][nextc] == 0) {
                    next.add(new int[] {nextr, nextc});
                    nextr += direction[i][0];
                    nextc += direction[i][1];
                }
                if (nextr >= 0 && nextr < row && nextc >= 0 && nextc < col && grids[nextr][nextc] == 1) {
                    for (int[] nextArr : next) {
                        grids[nextArr[0]][nextArr[1]] = 1;
                    }
                    queue.addAll(next);
                    cnt += next.size();
                }
            }
        }
        return cnt;
    }
}
