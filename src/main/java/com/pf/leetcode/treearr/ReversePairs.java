package com.pf.leetcode.treearr;

import java.util.Arrays;

public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();
        int[] nums = new int[]{
                //1,3,2,3,1
                //2,4,3,5,1
                2147483647,2147483647,2147483647,2147483647,2147483647,2147483647
        };
        System.out.println(reversePairs.reversePairs(nums));

    }

    public int reversePairs(int[] nums) {
        int len = nums.length;
        int[] cop = Arrays.copyOf(nums, len);
        Arrays.sort(cop);
        //bit 树状数组中存储的是第1大到第n大里的数据已经遍历过的值的个数
        //比如最大的已经出现了那就给bit[1]++;
        int[] bit = new int[len + 1];
        int res = 0;
        for (int i = len - 1; i >= 0; i--) {
            //有多少个数是小于等于2 * nums[i] 的
            int size = midFind(cop, (long)2 * nums[i]);
            //有多个数是大于2 * nums[i]的
            int bigNum = len - size;
            //查询之前bigNum数中1, bigNum 有几个已经被放进树状数组了
            res += query(bit, bigNum);
            //找到该数据的位置把数据放入数组中就是求得 nums[i] 是第几大的数，然后把该位置更新
            size = midFind(cop, nums[i] - 1);
            bigNum = len - size;
            update(bit, bigNum, 1);
        }
        return res;
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
        if (x == 0) {
            return;
        }
        while (x < bit.length) {
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
