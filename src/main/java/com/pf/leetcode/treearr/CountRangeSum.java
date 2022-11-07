package com.pf.leetcode.treearr;

import java.util.Arrays;

public class CountRangeSum {

    public static void main(String[] args) {
        CountRangeSum countRangeSum = new CountRangeSum();
        int[] nums = new int[] {
//                -2,5,-1
                2147483647,-2147483648,-1,0
        };
        int lower = -1;
        int upper = 0;
        System.out.println(countRangeSum.countRangeSum(nums, lower, upper));
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (long) nums[i - 1];
        }
        long[] arr = Arrays.copyOf(sum, sum.length);
        Arrays.sort(arr);

        int[] bit = new int[sum.length + 1];
        int res = 0;
        for(int i = 0; i < sum.length; i++) {
            int lo = findDown(arr, sum[i] - upper);
            int up = findUp(arr, sum[i] - lower);
            res += query(bit, up) - query(bit, lo);
            int now = findUp(arr, sum[i]);
            update(bit,1,now);
        }
        return res;
    }

    void update(int[] bit, int val, int index) {
        int len = bit.length;
        if(index == 0) {
            return;
        }
        while(index < len) {
            bit[index] += val;
            index += lowbit(index);
        }
    }

    int query(int[] bit, int index) {
        int sum = 0;
        while(index > 0) {
            sum += bit[index];
            index -= lowbit(index);
        }
        return sum;
    }

    int lowbit(int x) {
        return x & (-x);
    }
    //找到大于target的第一个值是位于数组的第几个（长度）
    // <= upper 的个数
    int findUp(long[] arr, long target) {
        int right = arr.length;
        int left = 0;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    //找到小于target的第一个值是位于数组的第几个（长度）
    // < lower的个数
    int findDown(long[] arr, long target) {
        int right = arr.length;
        int left = 0;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
