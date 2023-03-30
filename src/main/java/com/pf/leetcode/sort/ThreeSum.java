package com.pf.leetcode.sort;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int nagCount = 0;
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nagCount++;
            } else if (nums[i] == 0) {
                zeroCount++;
            }
        }
        if (nagCount == 0 && zeroCount < 4) {
            return new LinkedList();
        }

        Set<Integer> set = new HashSet();
        List<List<Integer>> list = new LinkedList();
        set.add(nums[0]);
        for (int i = 1; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(0 - nums[i] - nums[j])) {
                    List<Integer> temp = new ArrayList(3);
                    temp.add(0 - nums[i] - nums[j]);
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    list.add(temp);
                }

                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j++;
                }
            }
            set.add(nums[i]);
            while (i + 1 < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return list;
    }
}
