package com.pf.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

public class CountSmaller {
    public static void main(String[] args) {
        CountSmaller smaller = new CountSmaller();
        System.out.println(smaller.countSmaller(new int[]{5,2,6,1}));
        System.out.println(smaller.countSmaller(new int[]{}));
    }
    int[] res;
    int[] sort;
    int len;
    int[] nums;
    public List<Integer> countSmaller(int[] nums) {
        len = nums.length;
        this.nums = nums;
        res =  new int[len];
        sort = new int[len];
        for(int i = 0; i < len; i++){
            sort[i] = i;
        }
        gb(0, len - 1);
        List<Integer> list = new ArrayList(len);
        for(int i = 0; i < len; i++){
            list.add(res[i]);
        }
        return list;
    }

    void gb(int start, int end){
        if(start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        gb(start, mid);
        gb(mid + 1, end);
        int[] temp = new int[end - start + 1];
        int ind = 0;
        int left = start;
        int right = mid + 1;
        while(left <= mid && right <= end){
            System.out.println(left + "," + end);
            if(nums[sort[left]] <= nums[sort[right]]){
                res[sort[left]] += right - mid - 1;
                temp[ind++] = sort[left++];
            } else {
                temp[ind++] = sort[right++];
            }
        }
        if (left <= mid){
            res[sort[left]] += end - mid;
            temp[ind++] = sort[left++];
        }
        while(left <= mid){
            temp[ind++] = sort[left];
            res[sort[left++]] += end - mid;
        }
        while(right <= end){
            temp[ind++] = sort[right++];
        }
        for(int i = 0; i <= end - start; i++){
            sort[start + i] = temp[i];
        }
    }

}
