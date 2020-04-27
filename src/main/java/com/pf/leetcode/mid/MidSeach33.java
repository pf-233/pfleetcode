package com.pf.leetcode.mid;

/**
 * @author pf
 * @date 2020-04-27 13:02
 **/
public class MidSeach33 {
    public static void main(String[] args) {
        MidSeach33 midSeach33 = new MidSeach33();
        System.out.println(midSeach33.search(new int[]{3,1}, 1));
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){
            return -1;
        }
        int left = 0;
        int right = len - 1;
        int resver = getResver(nums, left, right);

        return resver == -1 ? midFind(nums, left, right, target) : Math.max(midFind(nums, left, resver, target), midFind(nums, resver, right, target));
    }

    int getResver(int[] nums, int left, int right){
        if(left == right){
            return left > 0 && nums[left] < nums[left-1] ? left :  -1;
        }
        int mid = (left + right) / 2;
        return mid > 0 && nums[mid] < nums[mid-1] ? mid : Math.max(getResver(nums, left, mid), getResver(nums, mid + 1, right));
    }

    int midFind(int[] nums, int left, int right, int target){
        int res = -1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
