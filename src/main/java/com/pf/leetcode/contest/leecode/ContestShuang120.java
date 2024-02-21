package com.pf.leetcode.contest.leecode;

public class ContestShuang120 {
    public static void main(String[] args) {
        ContestShuang120 contest = new ContestShuang120();
        int[] nums;
//        nums = new int[]{8,7,6,6};
//        nums = new int[] {6,5,7,8};
        nums = new int[]{6,7,5}; //5  7,5  6,7,5  6,7
        System.out.println(contest.incremovableSubarrayCount(nums));
    }

    public int incremovableSubarrayCount(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int pre = 0;
        while (left < n && nums[left] > pre) {
            pre = nums[left++];
        }
        // 原来就是递增的
        if (left == n) {
            return (1 + n) * n / 2;
        }
        // 这里[0, left - 1] 递增 [right + 1, n - 1] 递增 如果交叉说明整个都是递增直接上面结束了。所以肯定是left - 1 < right + 1
        // right 最大也是n-2
        pre = Integer.MAX_VALUE;
        while (right >= 0 && nums[right] < pre) {
            pre = nums[right--];
        }

        // 全部删光 右侧全部删光 左侧全部删光
        ans = 1 + (n - 1 - right - 1 + 1) + (left - 1 - 0 + 1);
        for (int i = 0, j = right + 1; i < left && j < n; i++) {
            while (j < n && nums[i] >= nums[j]) {
                j++;
            }
            // 右侧全部删光已经计算
            ans += n - j;
        }
        return ans;
    }
}
