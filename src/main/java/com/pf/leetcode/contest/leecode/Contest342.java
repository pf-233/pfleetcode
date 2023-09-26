package com.pf.leetcode.contest.leecode;

import java.util.Arrays;

public class Contest342 {
    public static void main(String[] args) {
        Contest342 contest342 = new Contest342();
        int[] nums = new int[]{1,1};
        contest342.minOperations(nums);
    }

    public String[] sortPeople(String[] names, int[] heights) {
        Integer[] arr = new Integer[names.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Arrays.sort(arr, (a, b) -> heights[b] - heights[a]);
        String[] ans = new String[names.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = names[arr[i]];
        }
        return ans;
    }

    // 先找出一个1然后其他的就是和1求gcd
    public int minOperations(int[] nums) {
        int gcd = nums[0];
        int onecnt = nums[0] == 1 ? 1 : 0;
        for (int i = 1; i < nums.length && gcd > 1; i++) {
            gcd = gcd(gcd, nums[i]);
            onecnt += nums[i] == 1 ? 1 : 0;
        }
        // 如果所有数的gcd 大于1 就都为-1
        if (gcd > 1) {
            return -1;
        } else if (onecnt > 0) {
            // 只要有一个1就选择1去消所以只需要len - cnt;
            return nums.length - onecnt;
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int cnt = 0;
            int nowgcd = nums[i];
            for (int j = i - 1; j >= 0 && nowgcd > 1; j--) {
                nowgcd = gcd(nowgcd, nums[j]);
                cnt++;
            }
            if (nowgcd == 1) {
                min = Math.min(min, cnt + nums.length - 1);
            }
        }
        return min;
        
    }
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a; 
        }
        return gcd(b, a % b);
    }
}
