package com.pf.leetcode.dp;

/**
 * 1277. 统计全为 1 的正方形子矩阵
 *
 * @author pf
 * @date 2020-05-08 22:07
 **/
public class CountSquares {
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//                {0,1,1,1},
//                {1,1,1,1},
//                {0,1,1,1}
//        };
//        System.out.println(countSquares(matrix));
//        System.out.println(mySqrt(2147395599));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(myPow(2.00000, -2));
    }

    public static double myPow(double x, int n) {
        double temp = x;
        boolean fu = n < 0;
        double res = 1;
        if (fu){
            //如果是 负数 Integer.MIN_VALUE 转会溢出 所以就先+1 然后把res = x先乘上这一个
            if (n == Integer.MIN_VALUE){
                res = x;
                n = 0 - (n + 1);
            } else {
                n = - n;
            }

        }

        while(n != 0){
            if((n & 1) == 1){
                res*= temp;
            }
            temp*= temp;
            n = n >>> 1;
        }
        return fu ? 1 / res : res;
    }

    public static int mySqrt(int x) {
        if(x == 0){
            return 0;
        } else if(x == 1){
            return 1;
        }
        int low = 1;
        int high = x;
        while(low < high){
            int mid = low + (high - low) / 2;
            int temp = x / mid;
            if(temp < mid){
                high = mid;
            } else if(temp == mid){
                return mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    public static int countSquares(int[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        //dp 是求最大边长的正方形
        int[][] dp = new int[row + 1][col + 1];
        int sum = 0;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                if(matrix[i - 1][j - 1] == 0){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
                //边长为3则 肯定有边长为1 和2 的正方形
                sum+= dp[i][j];
            }
        }
        return sum;
    }
}
