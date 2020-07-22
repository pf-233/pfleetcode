package com.pf.leetcode.tu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestIncreasingPath {

    public static void main(String[] args) {
        LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
        int[][] ma = new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        System.out.println(longestIncreasingPath.longestIncreasingPath(ma));
    }
    static int[][] dis = new int[][]{
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        if(row == 0){
            return 0;
        }
        int col = matrix[0].length;
        int[][] inCount = new int[row][col];
        int[][] val = new int[row][col];
        for(int i = 0; i < row; i++){
            Arrays.fill(val[i], -1);
        }
        Queue<Point> que = new LinkedList();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int count = 0;
                int now = matrix[i][j];
                for(int k = 0; k < dis.length; k++){
                    int tempRow = i + dis[k][0];
                    int tempCol = j + dis[k][1];
                    if(tempRow >= 0 && tempRow < row && tempCol >= 0 && tempCol < col){
                        if(matrix[tempRow][tempCol] > now){
                            count++;
                        }
                    }
                }
                inCount[i][j] = count;
                if(count == 0){
                    val[i][j] = 0;
                    que.offer(new Point(i, j));
                }
            }
        }
        int max = 0;
        while(!que.isEmpty()){
            Point temp = que.poll();
            int now = matrix[temp.row][temp.col];
            for(int k = 0; k < dis.length; k++){
                int tempRow = temp.row + dis[k][0];
                int tempCol = temp.col + dis[k][1];
                if(tempRow >= 0 && tempRow < row && tempCol >= 0 && tempCol < col){
                    if(matrix[tempRow][tempCol] < now){
                        if(val[tempRow][tempCol] == -1) {
                            val[tempRow][tempCol] = val[temp.row][temp.col] + 1;
                        } else {
                            val[tempRow][tempCol] = Math.max( val[tempRow][tempCol],  val[temp.row][temp.col] + 1);
                        }
                        max = Math.max(max, val[tempRow][tempCol]);
                        if(--inCount[tempRow][tempCol] == 0){
                            que.offer(new Point(tempRow, tempCol));
                        }

                    }
                }
            }
        }
        return max;
    }

    class Point {
        int row;
        int col;
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
