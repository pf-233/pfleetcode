package com.pf.leetcode.contest.leecode;

/**
 * author：panf
 * date：3/31/2024
 * Description:
 */
public class Contest391 {

    public static void main(String[] args) {
        Contest391 contest = new Contest391();
        int[] nums;
        nums = new int[]{ 0,1,1,1};
        System.out.println(contest.countAlternatingSubarrays(nums));
        System.out.println("test");
    }

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0L;
        int left = 0;
        int right = 0;
        int len = nums.length;
        int pre = -1;
        while (right < len) {
            if (nums[right] != pre) {
                pre = nums[right];
                right++;
            }
            else {
                while (left < right) {
                    ans += right - left;
                    left++;
                }
                pre = -1;
            }
        }
        while (left < right) {
            ans += right - left;
            left++;
        }
        return ans;
    }


}
