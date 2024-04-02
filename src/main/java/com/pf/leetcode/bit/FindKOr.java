package com.pf.leetcode.bit;

/**
 * author：panf
 * date：3/6/2024
 * Description:
 */
public class FindKOr {

    public static void main(String[] args) {
        FindKOr findKOr = new FindKOr();
        int[] nums;
        int k;
        nums = new int[] {7,12,9,8,9,15};
        k = 4;
        System.out.println(findKOr.findKOr(nums, k));
    }
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        int temp = 1;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & temp) > 0) cnt++;
            }
            if (cnt >= k) ans |= temp;
            temp <<= 1;
        }
        return ans;
    }
}
