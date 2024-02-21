package com.pf.leetcode.heap;

import java.util.PriorityQueue;

/**
 * author：panf
 * date：2/5/2024
 * Description:
 */
public class MaxResult  {
    public static void main(String[] args) {
        MaxResult maxResult = new MaxResult();
        int[] nums;
        int k;
        nums = new int[]{1,-5,-20,4,-1,3,-6,-3};
        k = 2;
        System.out.println(maxResult.maxResult(nums, k));
    }
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // sum, index
        PriorityQueue<long[]> que = new PriorityQueue<long[]>((a, b) -> (b[0] - a[0]) == 0L ? 0 : b[0] - a[0] > 0L ? 1 : -1);
        que.offer(new long[]{0L + nums[0], 0L});
        for (int i = 1; i < n; i++) {
            while (que.peek()[1] + k < i) {
                que.poll();
            }
            que.offer(new long[]{que.peek()[0] + nums[i], i});
        }
        while (que.peek()[1] != n - 1) {
            que.poll();
        }
        return (int)que.poll()[0];
    }
}
