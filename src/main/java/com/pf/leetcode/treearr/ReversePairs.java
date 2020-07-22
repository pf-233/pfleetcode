package com.pf.leetcode.treearr;

import java.util.Arrays;

public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();


    }

    public int reversePairs(int[] nums) {
        int len = nums.length;
        int[] cop = Arrays.copyOf(nums, len);
        Arrays.sort(cop);
        int[] bit = new int[len + 1];
        int res = 0;
        for (int i = 0; i < len; i++) {
            int size = midFind(cop, 2 * nums[i]);
            res += query(bit, i + 1);
        }
        return 0;
    }

    /**
     * 找到数组内小于target的数字的个数
     * 第一个大于target的数字的索引就是该个数
     * @param arr
     * @param target
     * @return
     */
    public int midFind(int[] arr,  long target) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     * 更新树状数组的值
     * @param bit
     * @param x
     * @param val
     */
    private void update(int[] bit, int x, int val) {
        while (x <= bit.length) {
            bit[x] += val;
            x += lowbit(x);
        }
    }

    /**
     * 查询树状数组的和
     * @param bit
     * @param x
     * @return
     */
    private int query(int[] bit, int x) {
        int sum = 0;
        while (x > 0) {
            sum += bit[x];
            x -= lowbit(x);
        }
        return sum;
    }
    /**
     * 计算偏移
     * @param x
     * @return
     */
    int lowbit(int x) {
        return x & (-x);
    }
}
