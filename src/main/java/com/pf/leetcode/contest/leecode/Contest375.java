package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：2023/12/10
 * Description:
 */
public class Contest375 {

    public static void main(String[] args) {
        Contest375 contest = new Contest375();
        int[] nums;
        int k;
//        nums = new int[]{1,3,2,3,3};
//        k = 2;
//        nums = new int[]{61,23,38,23,56,40,82,56,82,82,82,70,8,69,8,7,19,14,58,42,82,10,82,78,15,82};
//        k = 2;
//        System.out.println(contest.countSubarrays(nums, k));
        nums = new int[]{1,2,3,4};
        System.out.println(contest.numberOfGoodPartitions(nums));
    }

    int mode = (int) 1e9 + 7;
    public int numberOfGoodPartitions(int[] nums) {
        Map<Integer, int[]> map = new HashMap();
        int len = nums.length;
        int[] temp;
        for (int i = 0; i < len; i++) {
            temp = map.get(nums[i]);
            if (temp == null) {
                temp = new int[]{i, i};
            }
            temp[1] = i;
            map.put(nums[i], temp);
        }
         List<int[]> ans = new LinkedList();
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i;
            while (i <= right) {
                temp = map.get(nums[i]);
                right = Math.max(right, temp[1]);
                i++;
            }
            i = right;
            ans.add(new int[]{left, right});
            cnt++;
        }
        long ans1 = 1L;
        for (int i = 0; i < cnt - 1; i++) {
            ans1 *= 2;
            ans1 %= mode;
        }
        return (int) ans1;
    }

    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        List<Integer> list = new LinkedList();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                list.add(i);
            }
        }
        if (list.size() < k) {
            return 0;
        }
        long ans = 0;
        int pre = -1;
        for (int i = 0; i <= list.size() - k; i++) {
            int leftCnt = list.get(i) - pre;
            int rightConor = i + k - 1;
            int rightCnt = nums.length - list.get(rightConor);
            ans += leftCnt * rightCnt;
            pre = list.get(i);
        }
        return ans;
    }
}
