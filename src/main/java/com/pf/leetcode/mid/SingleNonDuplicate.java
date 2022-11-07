package com.pf.leetcode.mid;

public class SingleNonDuplicate {

    public static void main(String[] args) {
        SingleNonDuplicate singleNonDuplicate = new SingleNonDuplicate();
        int[] nums = new int[]{1,2,2,3,3};
//        System.out.println(singleNonDuplicate.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        System.out.println(singleNonDuplicate.singleNonDuplicate(nums));
    }
    //3,3,7,7,10,11,11
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            mid = (left + right) / 2;
            if (mid % 2 == 1) {
                if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else if (nums[mid] != nums[mid + 1]){
                    return nums[mid];
                } else {
                    right = mid;
                }
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    left = mid + 2;
                } else if (nums[mid] != nums[mid - 1]){
                    return nums[mid];
                } else {
                    right = mid;
                }
            }
        }
        return nums[left];
    }
}
