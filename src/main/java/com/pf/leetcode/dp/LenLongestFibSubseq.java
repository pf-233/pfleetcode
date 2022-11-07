package com.pf.leetcode.dp;

import java.util.*;

public class LenLongestFibSubseq {

    public static void main(String[] args) {
        LenLongestFibSubseq lenLongestFibSubseq = new LenLongestFibSubseq();
        int[] arr = new int[] {2,4,5,6,7,8,11,13,14,15,21,22,34};
        System.out.println(lenLongestFibSubseq.lenLongestFibSubseq(arr))
    }
    public int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> mm = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            mm.put(arr[i], i);
        }
        int max = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                int n1 = i;
                int n2 = j;
                Integer tmp = mm.getOrDefault(arr[n1] + arr[n2], -1);
                int count = 2;
                while (tmp > -1) {
                    count++;
                    n1 = n2;
                    n2 = tmp;
                    tmp = mm.getOrDefault(arr[n1] + arr[n2], -1);
                }
                if (count > 2 && count > max) {
                    max = count;
                }
            }
        }
        return max;
    }
}
