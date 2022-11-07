package com.pf.leetcode.other;

import java.util.TreeSet;

public class MaxSumSubmatrix {
    public static void main(String[] args) {
        MaxSumSubmatrix submatrix = new MaxSumSubmatrix();
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] sum = new int[m];
            for (int j = i; j < n; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int q = 0; q < m; q++) {
                    sum[q] += matrix[j][q];
                }
                int s = 0;
                for (int val : sum) {
                    s += val;
                    Integer tmp = set.ceiling(s - k);
                    if (tmp != null) {
                        res = Math.max(tmp, res);
                    }
                    set.add(s);
                }
            }
        }
        return res;
    }
}
