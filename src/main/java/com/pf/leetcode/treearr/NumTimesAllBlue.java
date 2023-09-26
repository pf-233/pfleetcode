package com.pf.leetcode.treearr;

import java.util.Arrays;

public class NumTimesAllBlue {

    public static void main(String[] args) {
        NumTimesAllBlue numTimesAllBlue = new NumTimesAllBlue();
        int[] flips;
        flips = new int[]{3,2,4,1,5};
        System.out.println(numTimesAllBlue.numTimesAllBlue(flips));
    }

    public int numTimesAllBlue(int[] flips) {
        int n = flips.length;
        int[] lowbit = new int[n + 1];
        boolean[] isOne = new boolean[lowbit.length];
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < flips.length; i++) {
            isOne[flips[i]] = !isOne[flips[i]];
            if (isOne[flips[i]]) {
                sum++;
                add(lowbit, 1, flips[i]);
            } else {
                sum--;
                add(lowbit, -1, flips[i]);
            }
            cnt += sum(lowbit, sum) == sum ? 1 : 0;
        }
        return cnt;
    }

    private void add(int[] lowbit, int val, int ind) {
        while (ind < lowbit.length) {
            lowbit[ind] += val;
            ind += lowbit(ind);
        }
    }

    private int sum(int[] lowbit, int ind) {
        int sum = 0;
        while (ind > 0) {
            sum += lowbit[ind];
            ind -= lowbit(ind);
        }
        return sum;
    }

    private int lowbit(int x) {
        return x & -x;
    }
}
