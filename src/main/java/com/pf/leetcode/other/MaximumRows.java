package com.pf.leetcode.other;

import com.pf.leetcode.utils.ArrayUtils;

/**
 * author：panf
 * date：1/4/2024
 * Description:
 */
public class MaximumRows {
    public static void main(String[] args) {
        MaximumRows maximumRows = new MaximumRows();
        int[][] matrix;
        int numSelect;
//        matrix = new int[][] {
//                {0,0,0},{1,0,1},{0,1,1},{0,0,1}
//
//        };
//        numSelect = 2;
        matrix = new int[][] {
                {1,0,0,0,0,0,0},{0,1,0,1,1,1,1},{0,0,0,1,0,0,1}
        };
        numSelect = 5;
        System.out.println(maximumRows.maximumRows(matrix, numSelect));
    }
    public int maximumRows(int[][] matrix, int numSelect) {
        int max = 1 << matrix[0].length;
        int maxRow = -1;
        for (int i = 1; i < max; i++) {
            if (Integer.bitCount(i) == numSelect) {
                maxRow = Math.max(maxRow, maximumRowsCal(matrix, i));
            }
        }
        return maxRow;
    }

    private int maximumRowsCal(int[][] matrix, int selectCol) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int j = 0;
            for (; j < m; j++) {
                sum += matrix[i][j];
                if (matrix[i][j] == 1 &&  ((selectCol & (1 << j)) == 0)) {
                    break;
                }
            }
            row += (sum == 0 || j == m) ? 1 : 0;
        }
        return row;
    }
}
