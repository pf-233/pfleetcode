package com.pf.leetcode.mid;

/**
 * author：panf
 * date：11/21/2023
 * Description:
 */
public class MinDeletion {

    public static void main(String[] args) {
        MinDeletion minDeletion = new MinDeletion();
        int[] nums;
        nums = new int[] {4,0,2,0,1,0,3,5,5,9,0,9,8};
        System.out.println(minDeletion.minDeletion(nums));
    }

    public int minDeletion(int[] nums) {
        int len = nums.length;
        int deleteCnt = 0;
        for (int i = 1; i < len; i++) {
            int now = i - deleteCnt;
            if ((now & 1) == 1 & nums[i] == nums[i - 1]) {
                deleteCnt++;
            }
        }
        deleteCnt += (len - deleteCnt) & 1;
        return deleteCnt;
    }
}
