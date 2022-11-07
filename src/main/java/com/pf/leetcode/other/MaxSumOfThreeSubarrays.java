package com.pf.leetcode.other;

public class MaxSumOfThreeSubarrays {

    public static void main(String[] args) {
        MaxSumOfThreeSubarrays maxSumOfThreeSubarrays = new MaxSumOfThreeSubarrays();
        int[] nums = new int[]{1,2,1,2,6,7,5,1};
        int k = 2;
        maxSumOfThreeSubarrays.maxSumOfThreeSubarrays(nums, k);
    }
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len - k + 1];
        for (int i = 0; i < k; i++) {
            sum[0] += nums[i];
        }
        for (int i = k; i < len; i++) {
            sum[i - k + 1] = sum[i - k] + nums[i] - nums[i - k];
        }

        int[] res = new int[] {0, k, 2 * k};
        int max = 0;
        for (int i = 0; i < res.length; i++) {
            max += sum[res[i]];
        }

        int maxFirstInd = 0;
        for (int i = k; i < sum.length - k; i++) {
            for (int j = i + k; j < sum.length; j++) {
                int tmp = sum[maxFirstInd] + sum[i] + sum[j];
                if (tmp > max) {
                    res[0] = maxFirstInd;
                    res[1] = i;
                    res[2] = j;
                    max = tmp;
                }
            }
            // 这个i 不要了之后，就可以 选择 i - k + 1 到 i 这个区间。去更新前面可以选择的最大值
            if (sum[i - k + 1] > sum[maxFirstInd]) {
                maxFirstInd = i - k + 1;
            }

        }
        return res;
    }
}
