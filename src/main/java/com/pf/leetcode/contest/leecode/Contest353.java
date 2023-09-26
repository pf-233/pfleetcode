package com.pf.leetcode.contest.leecode;

public class Contest353 {
    public static void main(String[] args) {
        Contest353 contest353 = new Contest353();
        int[] nums;
        int k;
//        nums = new int[]{2,2,3,1,1,0};
//        k = 3;
        nums = new int[]{0,45,82,98,99};
        k = 4;
        System.out.println(contest353.checkArray(nums, k));
    }
    // 第一个数只能是[0, k - 1] 内消去 nums[0];
    // 第二个数是[1, k] 内消去nums[1]
    public boolean checkArray(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int n = nums.length;
        int sum = 0;
        int[] startDown = new int[n];
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                sum -= startDown[i - k];
            }
            int need = nums[i] - sum;
            if (need < 0 || (need > 0 && i >= n - k + 1)) {
                return false;
            }
            startDown[i] = need;
            sum += need;
        }
        return true;
    }
}
