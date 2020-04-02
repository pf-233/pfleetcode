package com.pf.leetcode;

import java.util.Stack;

/**
 * @author pf
 * @date 2020-03-15 11:48
 **/
public class Migong {
    public static void main(String[] args) {
//        int left = 3;
//        int right = 5;
//        System.out.println(left + (right-left)/2);
//        System.out.println(left + (right-left)>>1);
//        // int[][] matrix = new int[][]{
//        //     {1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}
//        // };
//        int[][] matrix = new int[][]{
//                {0,1},{1,1}
//        };
//        System.out.println(maxAreaOfIsland(matrix));
//
        Stack stack = new Stack();

        int[] nums=new int[]{
                17595,45408,24290,47096,69315,76769
        };
        sumFourDivisors(nums);
    }

    public static int sumFourDivisors(int[] nums) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=sum(nums[i]);
        }
        return sum;
    }

    public static int sum(int num){
        int count=2;
        int[] temp=new int[]{1, num, 0,0};
        int sum = 1+num;
        for(int i=2;i*i<num;i++){
            if(num%i==0){
                temp[2]=i;
                temp[3]=num/i;
                count+=2;
                sum+=i+num/i;
            }
            if(count>4){
                return 0;
            }
        }
        return count==4 ? sum : 0;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = n>0 ? grid[0].length : 0;
        if(n==0){
            return 0;
        }
        boolean[][] visitOver = new boolean[n][m];
        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //访问过就跳过
                if(visitOver[i][j]){
                    continue;
                }
                if(grid[i][j]==1){
                    visitOver[i][j]= true;
                    max= Math.max(find(visitOver, grid, i, j, n, m)+1, max);
                }
            }
        }
        return max;
    }

    public static int find(boolean[][] visitOver, int[][] grid, int i, int j, int n, int m){
        int left=0;
        int right=0;
        int up=0;
        int down=0;
        //下边可以走  且 没走过
        if(i<n-1 && grid[i+1][j]==1 && visitOver[i+1][j]== false){
            visitOver[i+1][j]=true;
            down = find(visitOver, grid, i+1, j, n, m) + 1;
        }
        if(i > 1 && grid[i-1][j]==1 && visitOver[i-1][j]== false){
            //上边可以走  且 没走过
            visitOver[i-1][j]=true;
            up = find(visitOver, grid, i-1, j, n, m) + 1;
        }
        if(j > 1 && grid[i][j-1]==1 && visitOver[i][j-1]== false){
            //左边可以走  且 没走过
            visitOver[i][j-1]=true;
            left = find(visitOver, grid, i, j-1, n, m) + 1;
        }
        if(j < m-1 && grid[i][j+1]==1 && visitOver[i][j+1]== false){
            //右边可以走  且 没走过
            visitOver[i][j+1]=true;
            right = find(visitOver, grid, i, j+1, n, m) + 1;
        }
        return left+right+up+down;
    }
}
