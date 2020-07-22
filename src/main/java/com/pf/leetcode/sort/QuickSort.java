package com.pf.leetcode.sort;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * 快速排序
 *
 * @author pf
 * @date 2020-03-31 09:43
 **/
public class QuickSort {
    static Random random = new Random();

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
        qsort(nums, 0, nums.length - 1);
        return nums;
    }

    static void qsort(int[] copy, int start, int end){
        if(start >= end){
            return;
        }
        int split = part(copy, start, end);
        qsort(copy, start, split - 1);
        qsort(copy, split + 1, end);
    }

    static int part(int[] copy, int start, int end){
        int ind = random.nextInt(end - start + 1)  + start;
        swap(copy, ind, end);
        int left = start - 1;
        for(int i = start; i < end; i++){
            if (copy[i] < copy[end]){
                swap(copy, i, ++left);
            }
        }
        swap(copy, end, ++left);
        return left;
    }
    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
