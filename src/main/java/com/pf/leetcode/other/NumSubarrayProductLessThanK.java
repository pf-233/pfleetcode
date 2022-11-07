package com.pf.leetcode.other;

public class NumSubarrayProductLessThanK {
    public static void main(String[] args) {
        NumSubarrayProductLessThanK subarrayProductLessThanK = new NumSubarrayProductLessThanK();
        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(new int[]{ 57,44,92,28,66,60,37,33,52,38,29,76,8,75,22}, 18));
//        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));

//        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(new int[]{1,1,1}, 2));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
        }
        return ret;
    }
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int ans = 0;
        long res = 1;
        int min = nums.length - 1;
        while (left < nums.length) {
            if (right < nums.length) {
                res *= nums[right];
            }
            while (res >= k && left < Math.min(right, min)) {
                // 当前区间是[left, right] 不满足，但是当[left, right - 1]的时候是满足的(left,left+1...right-1)
                ans += (Math.min(right, min) - 1 - left) + 1;
                res /= nums[left];
                left++;
            }
            // 当left == right 时还>= k 是不加的
            if (res >= k && left == right) {
                res = 1;
                left++;
                right++;
                continue;
            } else if(right < nums.length){
                // res < k 时且right没到最后就++
                right++;
            } else {
                // res < k 时且right到最后就ans + 个数
                ans += Math.min(right, min) - left + 1;
                // 减小left
                left++;
            }
        }
        return ans;
    }
}
