package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author：panf
 * date：10/22/2023
 * Description:
 */
public class Contest368 {

    public static void main(String[] args) {
        Contest368 contest368 = new Contest368();
        int[] nums = null;
//        nums = new int[]{1,1,1,3,1,2,2,2,3};
        // 4 3 2
        // 3 个  4 / 2 = 2  + 3/3 + 2/2 = 4

        nums = new int[]{10,10,10,3,1,1};
//        nums = new int[]{1,1,2,1,1,1,3,1,2,3};
    }


    int max = Integer.MAX_VALUE;
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int min = max;
        for (int i = 0; i < n; i++) {
            prefix[i] = min;
            min = Math.min(nums[i], min);
        }
        min = max;
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = min;
            min = Math.min(nums[i], min);
        }
        int ans = max;
        for (int  i = 1; i < n - 1; i++) {
            if (prefix[i] < nums[i] && suffix[i] < nums[i]) {
                ans = Math.min(ans, prefix[i] + suffix[i] + nums[i])  ;
            }
        }
        return ans == max ? -1 : ans;
    }
}
