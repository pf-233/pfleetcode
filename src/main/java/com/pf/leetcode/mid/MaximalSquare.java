package com.pf.leetcode.mid;

/**
 * 最大正方形
 *
 * @author pf
 * @date 2020-05-08 13:08
 **/
public class MaximalSquare {
    public static void main(String[] args) {
        MaximalSquare maximalSquare = new MaximalSquare();
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'},
        };
//        char[][] matrix = new char[][]{
//                {'1'}
//        };
        System.out.println(maximalSquare.maximalSquare(matrix));;
    }
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] count = initCount(matrix);

        int low = 1;
        int high = row > col ? col  : row;

        while(low <= high){
            int mid = (low + high) / 2;
            int s = mid * mid;
            boolean flag = false;
            for(int i = mid; i <= row; i++){
                for(int j = mid; j <= col; j++){
                    if(getSum(count, i, j, mid) == s){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
            if(flag){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high * high;
    }

    public int[][] initCount(char[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] count = new int[row + 1][col + 1];
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                count[i][j] = count[i - 1][j] + count[i][j - 1] - count[i - 1][j - 1] + (matrix[i - 1][j - 1] == '0' ? 0 : 1);
            }
        }
        return count;
    }

    public int getSum(int[][] count, int row, int col, int len){
        return count[row][col] - count[row - len][col] - count[row][col - len] + count[row - len][col - len];
    }
}
