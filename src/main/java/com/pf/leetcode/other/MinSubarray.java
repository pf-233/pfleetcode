package com.pf.leetcode.other;

import java.util.HashMap;
import java.util.Map;

public class MinSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,4,2};
        int p = 6;
//        int[] nums = new int[]{6,3,2,5};
//        int p = 9;
//        int[] nums = new int[]{1,2,3};
//        int p = 7;
        System.out.println(new MinSubarray().minSubarray(nums, p));
    }

    public int minSubarray(int[] nums, int p) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = (preSum[i] + nums[i]) % p;
        }

        int needReduce = preSum[nums.length];
        if (needReduce == 0) {
            return 0;
        }

        Map<Integer, Integer> indexMap = new HashMap();
        int minLen = nums.length;

        for (int i = 0; i < preSum.length; i++) {
            indexMap.put(preSum[i], i);
            int needCutSum = (preSum[i] - needReduce + p) % p;
            if (indexMap.containsKey(needCutSum)) {
                minLen = Math.min(minLen, i - indexMap.get(needCutSum));
            }

        }
        return minLen >= nums.length ? -1 : minLen;
    }
}
