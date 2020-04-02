package com.pf.leetcode.sort;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 快速排序
 *
 * @author pf
 * @date 2020-03-31 09:43
 **/
public class QuickSort {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{5, 2, 3, 1};
//        sortArray(nums1);
//
//        int[] nums2 = new int[]{5, 1, 1, 2, 0, 0};
//        sortArray(nums2);


//        1 <= nums.length <= 10000
//                -50000 <= nums[i] <= 50000
//        sortArray(nums3);
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] str = string.split(",");
        int[] nums = new int[str.length];
        int i=0;
        for (String s:str){
            nums[i++]=new Integer(s);
        }
        sortArray(nums);
        for (int a:nums){
            System.out.println(a);
        }
    }
    static void qSort(int[] arr,int s,int e){
        int l = s, r = e;
        if(l < r){
            int temp = arr[l];
            while(l < r){
                while(l < r && arr[r] >= temp) r--;
                if(l < r) arr[l] = arr[r];
                while(l < r && arr[l] < temp) l++;
                if(l < r) arr[r] = arr[l];
            }
            arr[l] = temp;
            qSort(arr,s,l);
            qSort(arr,l + 1, e);
        }
    }

    public static int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }


    public static void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pos = part(nums, start, end);
        sort(nums, start, pos - 1);
        sort(nums, pos + 1, end);
    }

    public static int part(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }
        int postInt = nums[end];
        int endNow = end - 1;
        while (start < endNow) {
            while (start <= endNow && nums[start] <= postInt) {
                start++;
            }
            while (endNow >= start && nums[endNow] > postInt) {
                endNow--;
            }
            if (start < endNow) {
                swap(nums, start, endNow);
            }
        }
        if (nums[start] > postInt){
            swap(nums, start, end);
        }
        return start;
    }

    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
