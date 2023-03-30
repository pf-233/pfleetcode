package com.pf.leetcode.mid;

public class MidSearchTemplate {

    public static void main(String[] args) {
        MidSearchTemplate midSearchTemplate = new MidSearchTemplate();
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;
        System.out.println(midSearchTemplate.lowBound4(nums, target));
    }
    public int searchInsert(int[] nums, int target) {
        //return lowBound(nums, target);
        // return lowBound2(nums, target);
        // return lowBound3(nums, target);
        return lowBound4(nums, target);
    }

    // 循环不变式，当条件成立时进行循环，不成立时跳出循环
    // 闭区间
    private int lowBound(int[] nums, int target) {
        // 闭区间[left, right]
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        //循环不变式 区间中没有值的时候跳出循环
        while (left <= right) {
            mid = left + (right - left) / 2;
            // 删去[origin left, mid]未确定区间 [mid+1, right]
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 删去[mid, origin right]未确定区间[left, mid - 1]
                right = mid - 1;
            }
        }
        return left;
    }

    // 左闭右开区间 未确定区间 [left, right)
    private int lowBound2(int[] nums, int target) {
        // 左闭右开区间[left, right)
        int left = 0;
        int right = nums.length;
        int mid = 0;
        //循环不变式 区间中没有值的时候跳出循环
        while (left < right) {
            mid = left + (right - left) / 2;
            // 删去[origin left, mid]  未确定 [mid + 1, right]
            if (nums[mid] < target) {
                left = mid + 1; //[mid + 1, right)
            } else {
                // 删去[mid, origin right]  未确定 [left, mid)  所以right = mid
                right = mid; //[left, mid)
            }
        }
        return left;
    }


    // 左开右开区间 (left, right)
    private int lowBound3(int[] nums, int target) {
        // 左开右开区间（left, right)
        int left = -1;
        int right = nums.length;
        int mid = 0;
        //循环不变式 区间中没有值的时候跳出循环
        while (left < right - 1) {
            mid = left + (right - left) / 2;
            // 删去[origin left, mid]  未确定 (mid, right)
            if (nums[mid] < target) {
                left = mid; //(mid, right)
            } else {
                // 删去[mid, origin right]  未确定 (left, mid)  所以right = mid
                right = mid;  //(left, mid)
            }
        }
        return right;
    }

    // 左开右闭区间 (left, right]
    private int lowBound4(int[] nums, int target) {
        // 左开右闭区间（left, right]
        int left = -1;
        int right = nums.length - 1;
        int mid = 0;
        //循环不变式 区间中没有值的时候跳出循环
        while (left < right) {
            mid = left + (right - left + 1) / 2;
            // 删去[origin left, mid]  未确定 (mid, right]
            if (nums[mid] < target) {
                left = mid; //(mid, right]
            } else {
                // 删去[mid, origin right]  未确定 (left, mid - 1]  所以right = mid - 1
                right = mid - 1;  //(left, mid)
            }
        }
        // return right + 1;
        return left + 1;
    }
}
