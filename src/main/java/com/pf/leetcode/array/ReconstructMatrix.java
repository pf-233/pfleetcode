package com.pf.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReconstructMatrix {
    public static void main(String[] args) {
        ReconstructMatrix reconstructMatrix = new ReconstructMatrix();
        int upper;
        int lower;
        int[] colsum;
        upper = 2;
        lower = 1;
        colsum = new int[]{1,1,1};
        System.out.println(reconstructMatrix.reconstructMatrix(upper,lower, colsum));
    }
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int sum = Arrays.stream(colsum).sum();
        if (sum != upper + lower) {
            return new LinkedList();
        }
        int len = colsum.length;
        List<List<Integer>> list = new ArrayList(2);
        list.add(new ArrayList(len));
        list.add(new ArrayList(len));
        for (int i = 0; i < len; i++) {
            int row1 = 0;
            int row2 = 0;
            if (colsum[i] == 2) {
                upper--;
                lower--;
                row1 = 1;
                row2 = 1;
            }
            list.get(0).add(i, row1);
            list.get(1).add(i, row2);
        }

        for (int i = 0; i < len; i++) {
            if (colsum[i] == 1) {
                if (upper > 0) {
                    list.get(0).set(i, 1);
                    upper--;
                } else {
                    list.get(1).set(i, 1);
                }
            }
        }
        return list;
    }
}
