package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Contest202 {

    public static void main(String[] args) {
        Contest202 test = new Contest202();
        System.out.println(test.minDays(56));
        System.out.println(test.maxDistance(new int[]{79,74,57,22}, 4));
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 0;
        int right = position[position.length - 1];
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(check(position, m, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private boolean check(int[] arr, int m, int k) {
        int len = arr.length;
        int left = arr[0];
        m--;
        for(int i = 1; i < len; i++) {
            if(arr[i] - left >= k) {
                m--;
                left = arr[i];
            }
        }
        return m <= 0;
    }

    Map<Integer, Integer> map;


    public int minDays(int n) {
        map = new HashMap();
        map.put(1,1);
        map.put(2,2);
        map.put(3,2);
        return dg(n);
    }

    int dg(int n) {
        if(map.get(n) != null){
            return map.get(n);
        }

        int tmp = n;
        tmp = Math.min(tmp, dg(n / 2) + (n % 2 == 0 ? 1 : 2));
        tmp = Math.min(tmp, dg(n / 3) + (n % 3 == 0 ? 1 : n % 3 + 1));
        map.put(n, tmp);
        return tmp;
    }
}
