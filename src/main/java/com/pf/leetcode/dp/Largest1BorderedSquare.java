package com.pf.leetcode.dp;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Largest1BorderedSquare {

    //    public static void main(String[] args) {
//        Largest1BorderedSquare largest1BorderedSquare = new Largest1BorderedSquare();
//        int[][] grid = new int[][] {
//                {1,1,1},
//                {1,0,1},
//                {1,1,1}
//        };
//        System.out.println(largest1BorderedSquare.largest1BorderedSquare(grid));
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = new Integer(str);
        Set<Edge> set = new HashSet();
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            String s = sc.nextLine();
            String[] tmp = s.split(" ");
            set.add(new Edge(new Integer(tmp[0]), new Integer(tmp[1])));
        }

        for (int i = 0; i < n - 1; i++) {
            String s = sc.nextLine();
            String[] tmp = s.split(" ");
            Edge now = new Edge(new Integer(tmp[0]), new Integer(tmp[1]));
            if (!set.contains(now)){
                count++;
            }
        }
        System.out.println(count);
    }

    static class Edge {
        int start;
        int end;

        Edge(int x, int y){
            start = Math.min(x, y);
            end = Math.max(x, y);
        }

        @Override
        public int hashCode() {
            return start + end;
        }
        @Override
        public boolean equals(Object obj) {
            Edge edge = (Edge) obj;
            return start == edge.start && end == edge.end;
        }
    }
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        //0是前面有几个连续1  1是上面有几个连续1
        int[][][] count = new int[row][col][2];
        for (int i = 0; i < row; i++) {
            int tmp = 0;
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count[i][j][0] = tmp++;
                } else {
                    tmp = 0;
                }
            }
        }

        for (int i = 0; i < col; i++) {
            int tmp = 0;
            for (int j = 0; j < row; j++) {
                if (grid[j][i] == 1) {
                    count[j][i][1] = tmp++;
                } else {
                    tmp = 0;
                }
            }
        }
        int max = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                for (int k = 0; k <= Math.min(count[i][j][0], count[i][j][1]) && k <= i && k <= j; k++) {
                    if (count[i][j - k][1] >= k && count[i - k][j][0] >= k) {
                        max = Math.max(max, k + 1);
                    }
                }

            }
        }
        return max * max;
    }

}
