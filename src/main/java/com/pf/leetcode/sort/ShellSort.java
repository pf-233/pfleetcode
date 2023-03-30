package com.pf.leetcode.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] nums = new int[]{465,6435,1231,73,213};
        shellSort(nums);
        Arrays.stream(nums).forEach(e -> System.out.println(e));
    }
    public static void shellSort(int[] nums) {
        int increment = nums.length;
        // execute al least once
        do {
            // 3 is up to you, + 1 is ensure we can sorted the array.
            increment = increment / 3 + 1;
            // start from [0, increment - 1]
            for (int i = 0; i < increment; i++) {
                // Insertion Sort subsequence
                for (int j = i + increment; j < nums.length; j += increment) {
                    int key = nums[j];
                    int k = j;
                    while (k > i && nums[k] < nums[k - increment]) {
                        nums[k] = nums[k - increment];
                        k -= increment;
                    }
                    if (k != j) {
                        nums[k] = key;
                    }
                }
            }
        }
        while (increment > 1);
    }
}
