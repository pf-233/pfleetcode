package com.pf.leetcode.design;

public class Rate1 {

    int[] sum;
    int max;
    public Rate1(int[] w) {
        int len = w.length;
        sum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + w[i];
        }
        max = sum[len];
    }

    public int pickIndex() {
        int rate = (int) (Math.random() * (max + 1));
        return midfind(rate);
    }

    public int midfind(int targer) {
        int left = 0;
        int right = sum.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (sum[mid] < targer) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
