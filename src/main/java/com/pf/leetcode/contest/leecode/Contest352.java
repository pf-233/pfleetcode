package com.pf.leetcode.contest.leecode;

import java.util.HashMap;
import java.util.Map;

public class Contest352 {

    public static void main(String[] args) {
        Contest352 contest352 = new Contest352();
//        int[] nums = new int[]{2,3,4,5};
//        int threshold = 4;
//        System.out.println(contest352.longestAlternatingSubarray(nums, threshold));
        int[] nums = null;
//        nums = new int[]{5,4,2,4};
//        nums = new int[]{42,41,42,41,41,40,39,38};
        nums = new int[]{94,95,96,96,97,98,99,100,100};
        System.out.println(contest352.continuousSubarrays(nums));
    }
    public long continuousSubarrays(int[] nums) {
        long paris = 0L;
        Map<Integer, Integer> index = new HashMap();
        int max = nums[0] + 2;
        int min = nums[0] - 2;
        for (int l = 0, r = 0; l < nums.length; l++) {
            while (r < nums.length && nums[r] <= max  && nums[r] >= min) {
                index.put(nums[r], r);
                max = Math.min(max, nums[r] + 2);
                min = Math.max(min, nums[r] - 2);
                r++;
            }

            if (r == nums.length) {
                long len = r - l;
                paris += (len + 1) * len / 2;
                break;
            }
            int nextl = l;
            for (Integer key : index.keySet()) {
                if (key < nums[r] - 2 || key > nums[r] + 2) {
                    nextl = Math.max(index.get(key) + 1, nextl);
                }
            }

            while (l < nextl) {
                paris += r - l;
                l++;
            }
            l--;
            Map<Integer, Integer> index2 = new HashMap();
            max = nums[r] + 2;
            min = nums[r] - 2;
            for (Integer key : index.keySet()) {
                if (index.get(key) >= nextl && Math.abs(nums[r] - key) <= 2) {
                    index2.put(key, index.get(key));
                    max = Math.min(max, key + 2);
                    min = Math.max(key - 2, min);
                }
            }
            index = index2;
        }
        return paris;
    }

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxlen = 0;
        System.out.println("============");
        for (int l = 0; l < nums.length; l++) {
            if (nums[l] == 1 || nums[l] > threshold) {
                continue;
            }
            maxlen = Math.max(maxlen, 1);
            for (int r = l + 1; r < nums.length; r++) {
                if (nums[r] == nums[r - 1] || nums[r] > threshold) {
                    break;
                }
                System.out.println(nums[r]);
                maxlen = Math.max(maxlen, r - l + 1);
            }
        }
        return maxlen;
    }
}
