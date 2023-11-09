package com.pf.leetcode.easy;

import java.util.Arrays;

/**
 * author：panf
 * date：11/9/2023
 * Description:
 */
public class ThirdMax {

    public static void main(String[] args) {
        ThirdMax thirdMax = new ThirdMax();
        System.out.println(thirdMax.thirdMax(new int[]{2,2,3,1}));
    }
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 3) {
            return nums[n - 1];
        }
        int cnt = 0;
        int max = nums[n - 1];
        int i = n - 1;
        int pre = nums[i];
        for (; i >= 0 && cnt < 3; ) {
            pre = nums[i];
            while (i >= 0 && nums[i] == pre) {
                i--;
            }
            cnt++;
        }
        return cnt == 3 ? pre : max;
    }
}
