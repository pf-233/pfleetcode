package com.pf.leetcode.treearr;

import java.util.*;

public class CountSmaller {
    private static String NU = "None";

//    public static void main(String[] args) {
//        CountSmaller countSmaller = new CountSmaller();
//        int[] nums = new int[] {
//                5,2,6,1
//        };
//        System.out.println(countSmaller.countSmaller(nums));
//    }
    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList();
        }
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);
        int[] bit = new int[arr.length + 1];
        LinkedList<Integer> list = new LinkedList();
        for(int i = arr.length - 1; i >= 0; i--) {
            int len = find(arr, nums[i]);
            int count = query(bit, len);
            update(bit, 1, len + 1);
            list.addFirst(count);
        }
        return list;
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
    //找到小于target的第一个值是位于数组的第几个（长度）
    // < nums[i]的个数
    int find(int[] arr, int target) {
        int right = arr.length;
        int left = 0;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

}
