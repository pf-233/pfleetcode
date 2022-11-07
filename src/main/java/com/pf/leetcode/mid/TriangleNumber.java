package com.pf.leetcode.mid;

import java.util.Arrays;

public class TriangleNumber {

    public static void main(String[] args) {
        TriangleNumber triangleNumber = new TriangleNumber();
//        triangleNumber.triangleNumber(new int[]{2,2,3,4});
        triangleNumber.triangleNumber(new int[]{0,0,0});
    }
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                int limt = nums[i] + nums[j];
                res += find(limt, nums) - j - 1;
            }
        }
        return res;
    }

    int find(int target, int[] nums) {
        int left = 0;
        int right = nums.length;
        int mid = 0;
        while (left < right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
