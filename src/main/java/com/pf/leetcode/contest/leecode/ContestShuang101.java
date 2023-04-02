package com.pf.leetcode.contest.leecode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class ContestShuang101 {
    public static void main(String[] args) {
        ContestShuang101 contestShuang101 = new ContestShuang101();
        int[] arr = new int[]{2,5,5,7};
        System.out.println(contestShuang101.makeSubKSumEqual(arr, 3));
    }

    public long makeSubKSumEqual(int[] arr, int k) {
        long ans = 0L;
        int len = arr.length;
        int[] vis = new int[len];
        for (int i = 0; i < len; i++) {
            if (vis[i] != 0) {
                continue;
            }
            List<Integer> list = new LinkedList();
            int count = 0;
            int ind = i;
            while (vis[ind] == 0) {
                vis[ind] = 1;
                list.add(arr[ind]);
                count++;
                ind = (ind + k) % len;
            }
            list.sort(new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return a - b;
                }
            });
            int base = list.get(count / 2);
            for (int temp : list) {
                ans += Math.abs(temp - base);
            }
        }
        return ans;
    }
}