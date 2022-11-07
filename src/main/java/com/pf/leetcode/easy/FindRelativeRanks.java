package com.pf.leetcode.easy;

import java.util.Arrays;

public class FindRelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        Integer[] rank = new Integer[len];
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            rank[i] = i;
        }
        Arrays.sort(rank, (a, b) -> score[b] - score[a]);

        for(int i = 0; i < len; i++) {
            switch(i) {
                case 0: res[rank[i]] = "Gold Medal"; break;
                case 1: res[rank[i]] = "Silver Medal"; break;
                case 2: res[rank[i]] = "Bronze Medal"; break;
                default: res[rank[i]] = i + 1 + "";
            }
        }
        return res;
    }
}
