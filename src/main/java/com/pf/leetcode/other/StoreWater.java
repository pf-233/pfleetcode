package com.pf.leetcode.other;

import java.util.Arrays;

public class StoreWater {

    public static void main(String[] args) {
        StoreWater storeWater = new StoreWater();
        int[] bucket;
        bucket = new int[] {9,0,1};
        int[] vat;
        vat = new int[] {0,2,2};
        System.out.println(storeWater.storeWater(bucket, vat));
    }

    public int storeWater(int[] bucket, int[] vat) {
        int max = Arrays.stream(vat).max().getAsInt();
        if (max == 0) {
            return 0;
        }
        int min = max;
        for (int i = 1; i <= max && i <= min; i++) {
            int cnt = 0;
            for (int j = 0; j < vat.length; j++) {
                cnt += Math.max(0, (vat[j] + i - 1) / i - bucket[j]);
            }
            min = Math.min(min, cnt + i);
        }
        return min;
    }
}
