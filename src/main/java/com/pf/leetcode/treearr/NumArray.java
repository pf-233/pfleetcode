package com.pf.leetcode.treearr;

public class NumArray {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }
    int[] arrs;
    int[] originals;
    public NumArray(int[] nums) {
        int n = nums.length;
        originals = new int[n];
        arrs = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int index, int val) {
        int n = arrs.length;
        int diff = val - originals[index];
        originals[index] = val;
        index++;
        while (index < n) {
            arrs[index] += diff;
            index += lowbit(index);
        }
    }

    private int query(int index) {
        int ans = 0;
        while (index > 0) {
            ans += arrs[index];
            index -= lowbit(index);
        }
        return ans;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}
