package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class Contest256 {

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> {
            if (a.length() == b.length()) {
                return - a.compareTo(b);
            } else {
                return b.length() - a.length();
            }
        });
        return nums[k - 1];
    }

    public int minSessions(int[] tasks, int sessionTime) {
        int len = tasks.length;
        int limt = (int) Math.pow(2, len);
        Arrays.sort(tasks);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            map.put(1 << i, tasks[i]);
        }
        for (int i = 1; i < limt; i++) {
            int tmp = 0;
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) == 1) {
                    tmp += tasks[j];
                }
            }
            if (tmp <= sessionTime) {
                map.put(i, tmp);
            }
        }

        int nowl = len;
        while (nowl > 0) {
            
        }
        return 1;
    }
}
