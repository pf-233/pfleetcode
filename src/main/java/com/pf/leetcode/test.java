package com.pf.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author pf
 * @date 2020-03-22 11:42
 **/
public class test {

    public static void main(String[] args) {
//        int[][] grid = new int[][]{
//                {2,4,3},
//                {6,5,2}
//        };
//        [[2],[2],[2],[2],[2],[2],[6]]
//        int[][] grid = new int[][]{
//                {2},
//                {2},
//                {6}
//        };
//        String a = null;
//        System.out.println(hasValidPath(grid));
//        Arrays.sort();
//        System.out.println(longestPrefix("level"));
//        int[]A=new int[]{
//                3,2,1,2,1,7
//        };
//        minIncrementForUnique(A);


        /**
         *  在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
         *
         *         每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
         *
         *         请你返回最终形体的表面积。
         *
         * [[2]]
         * 输出：10
         *
         * [[1,2],[3,4]]
         * 输出：34
         *
         * [[1,0],[0,2]]
         * 16
         *
         */

    }

    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int sum = n*n*2;
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                sum+=grid[i][j]*4;
                if (i>1){
                    sum-=grid[i-1][j];
                }
                if (j>1){
                    sum-=grid[i][j-1];
                }
            }
        }
        return sum;
    }

    public static int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int count=0;
        for(int i=1;i<A.length;i++){
            if(A[i]<=A[i-1]){
                count+=A[i-1]-A[i]+1;
                A[i]=A[i-1]+1;
            }
        }
        return count;
    }

    public static String longestPrefix(String s) {
        int a1=0;
        int a2=s.length();
        int len = s.length();
        String maxStr = "";
        for(int i=0;i<s.length()-1;i++){

            if(s.charAt(i)==s.charAt(s.length()-1)){
                String temp = s.substring(0,i+1);
                String temp1 = s.substring(len-1-i, len);
                if(temp.equals(temp1)){
                    maxStr = temp1;
                }
            }
        }
        return maxStr;
    }

    public static boolean hasValidPath(int[][] grid) {
        Stack<int[]> stack =new Stack();
        stack.push(new int[]{0,0});

        int row = grid.length;
        int col = grid[0].length;
        int[][] map = new int[row][col];
        while(!stack.empty()){
            int[] next = stack.pop();
            int x =next[0];
            int y=next[1];
            if(x==row-1 && y==col-1){
                return true;
            }
            if(map[x][y]==1){
                return false;
            }
            map[x][y]=1;

// 1 表示连接左单元格和右单元格的街道。
// 2 表示连接上单元格和下单元格的街道。
// 3 表示连接左单元格和下单元格的街道。
// 4 表示连接右单元格和下单元格的街道。
// 5 表示连接左单元格和上单元格的街道。
// 6 表示连接右单元格和上单元格的街道。
            if(grid[x][y] == 1){
                right(stack,grid,x,y,map,row,col);
                left(stack,grid,x,y,map,row,col);

            } else if(grid[x][y] == 2){
                down(stack,grid,x,y,map,row,col);
                up(stack,grid,x,y,map,row,col);
            } else if(grid[x][y] == 3){
                left(stack,grid,x,y,map,row,col);
                down(stack,grid,x,y,map,row,col);
            } else if(grid[x][y] == 4){
                right(stack,grid,x,y,map,row,col);
                down(stack,grid,x,y,map,row,col);
            } else if(grid[x][y] == 5){
                left(stack,grid,x,y,map,row,col);
                up(stack,grid,x,y,map,row,col);
            } else if(grid[x][y] == 6){
                up(stack,grid,x,y,map,row,col);
                right(stack,grid,x,y,map,row,col);
            }

        }
        return false;
    }


    // 1 表示连接左单元格和右单元格的街道。
// 2 表示连接上单元格和下单元格的街道。
// 3 表示连接左单元格和下单元格的街道。
// 4 表示连接右单元格和下单元格的街道。
// 5 表示连接左单元格和上单元格的街道。
// 6 表示连接右单元格和上单元格的街道。
    public static void left(Stack stack, int[][] grid, int x, int y, int[][] map, int row, int col) {
        //往左

        if (y - 1 >= 0 && map[x][y-1] == 0) {
            if (grid[x][y-1] == 1 || grid[x][y-1] == 4 || grid[x][y-1] == 6) {
                stack.push(new int[]{x, y-1});
            }
        }
    }

    public static void right(Stack stack, int[][] grid, int x, int y, int[][] map , int row, int col) {
        //往右
        if (y + 1 < col && map[x][y+1] == 0) {
            if (grid[x][y+1] == 1 || grid[x][y+1] == 3 || grid[x][y+1] == 5) {
                stack.push(new int[]{x, y+1});
            }
        }
    }

    public static void up(Stack stack, int[][] grid, int x, int y, int[][] map , int row, int col) {
        //往上
        if (x - 1 >= 0 && map[x - 1][y] == 0) {
            if (grid[x - 1][y] == 2 || grid[x - 1][y] == 3 || grid[x - 1][y] == 4) {
                stack.push(new int[]{x - 1, y});
            }
        }
    }

    public static void down(Stack stack, int[][] grid, int x, int y, int[][] map , int row, int col) {
        //往下
        if (x + 1 < row && map[x + 1][y] == 0) {
            if (grid[x + 1][y] == 2 || grid[x + 1][y] == 5 || grid[x + 1][y] == 6) {
                stack.push(new int[]{x + 1, y});
            }
        }


    }

}
