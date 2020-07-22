package com.pf.leetcode.treearr;

public class NumArray {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }
    int[] c;
    int[] nums;
    int len;
    public NumArray(int[] nums) {
        this.len = nums.length;
        this.nums = nums;
        c = new int[len + 1];
        c[0] = 0;
        for(int i = 1; i <= len; i++) {
            c[i] += nums[i - 1];
            int k = lowBit(i);
            int next = k + i;
            while(next <= len) {
                c[next] += nums[i - 1];
                next = next + lowBit(next);
            }
        }
    }

    public void update(int i, int val) {
        if(i >= len) {
            return;
        }
        int diff = val - nums[i];
        nums[i] = val;
        c[i + 1] += diff;
        int k = lowBit(i + 1);
        int next = i + 1 + k;
        while(next <= len) {
            c[next] += diff;
            next = next + lowBit(next);
        }
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        if(i < 0 || j >= len) {
            return 0;
        }
        j++;
        while (j > 0) {
            sum += c[j];
            j -= lowBit(j);
        }
        while(i > 0) {
            sum -= c[i];
            i -= lowBit(i);
        }
        return sum;
    }

    public int lowBit(int x) {
        return x & (-x);
    }
}
