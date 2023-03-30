package com.pf.leetcode.dp;

import java.util.*;

class FlipChess {
    static int[][] dis = new int[][] {
            {1,0},
            {1,1},
            {0,1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1}
    };

    public static void main(String[] args) {
        FlipChess flipChess = new FlipChess();
        String[] strings = new String[] {
                "..XOO...",
                "...O....",
                "..O.....",
                ".X......",
                "........",
                "........",
                "........",
                "........"
        };
        System.out.println(flipChess.flipChess(strings));
    }

    public int flipChess(String[] chessboard) {
        int row = chessboard.length;
        int col = chessboard[0].length();

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int[][] chs = getChs(chessboard);
                chs[i][j] = 2;
                LinkedList list = new LinkedList<>();
                list.add(new int[]{i,j});
                max = Math.max(max, bfs(chs, list));
            }
        }
        return max;
    }

    int bfs(int[][] chs, LinkedList<int[]> pre) {
        int row = chs.length;
        int col = chs[0].length;
        LinkedList<int[]> res = new LinkedList();
        for (int[] pre1 : pre) {
            for (int d = 0; d < dis.length; d++) {
                int nextr = pre1[0] + dis[d][0];
                int nextc = pre1[1] + dis[d][1];
                List<int[]> tmp = new LinkedList();
                while (nextr >= 0 && nextr < row && nextc >= 0 && nextc < col) {
                    if (chs[nextr][nextc] == 1) {
                        tmp.add(new int[]{nextr, nextc});
                        nextr += dis[d][0];
                        nextc += dis[d][1];
                    } else if (chs[nextr][nextc] == 0) {
                        break;
                    } else {
                        for (int[] add : tmp) {
                            chs[add[0]][add[1]] = 2;
                        }
                        res.addAll(tmp);
                        break;
                    }
                }
            }
        }
        int sum = res.size();
        if (res.size() > 0) {
            sum += bfs(chs, res);
        }
        return sum;
    }


    int[][] getChs(String[] chessboard) {
        int row = chessboard.length;
        int col = chessboard[0].length();
        int[][] chs = new int[row][col];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                char ch = chessboard[j].charAt(i);
                if (ch == '.') {
                    chs[j][i] = 0;
                } else if (ch == 'O') {
                    chs[j][i] = 1;
                } else {
                    chs[j][i] = 2;
                }
            }
        }
        return chs;
    }
}

