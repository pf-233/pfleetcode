package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.stream.Collectors;

public class Contest358 {
    public static void main(String[] args) {
        Contest358 contest358 = new Contest358();
        List<Integer> nums;
        int x;
        nums = Arrays.asList(4,3,2,4);
        x = 2;
        System.out.println(contest358.minAbsoluteDifference(nums, x));
    }


    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        TreeSet<Integer> set = new TreeSet();
        int min = Integer.MAX_VALUE;
        for (int i = x; i < n; i++) {
            set.add(nums.get(i - x));
            Integer high = set.ceiling(nums.get(i));
            Integer low = set.floor(nums.get(i));
            if (high != null) {
                min = Math.min(min, high - nums.get(i));
            }
            if (low != null) {
                min = Math.min(min, nums.get(i) - low);
            }
        }
        return min;
    }

}
