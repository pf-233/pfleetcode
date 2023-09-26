package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.List;

public class Contest360 {

    public static void main(String[] args) {
        Contest360 contest360 = new Contest360();
        List<Integer> nums;
        int target;
//        nums = Arrays.asList(1,2,8);
//        target = 7;
//        nums = Arrays.asList(1,32,1,2);
//        target = 12;
//        nums = Arrays.asList(16,64,4,128);
//        target = 6;
        nums = Arrays.asList(64,1,16384,16384,1024,1,2,4096,2,2,65536,1,65536,4,4,256,4,16384,16384,8388608,16384,4,2,4096,4,1073741824,16777216,4,2,256,1,4,256,16384,1073741824,4096,1,4096,4,16384,4,4);
        target = 42;
        System.out.println(contest360.minOperations(nums, target));
//        System.out.println(contest360.minimumPossibleSum(2,3));
    }

    /**
     *
     [64,1,16384,16384,1024,1,2,4096,2,2,65536,1,65536,4,4,256,4,16384,16384,8388608,16384,4,2,4096,4,1073741824,16777216,4,2,256,1,4,256,16384,1073741824,4096,1,4096,4,16384,4,4]
     42
     [1,2,8]
     7
     [16,64,4,128]
     6
     [1,32,1,2]
     12
     [1,32,1]
     35
     * @param nums
     * @param target
     * @return
     */
    public int minOperations(List<Integer> nums, int target) {
        long sum = 0L;
        for (Integer num : nums) {
            sum += num;
        }
        if (target >= sum) {
            return target == sum ? 0 : -1;
        }
        int[] cnt = new int[32];
        for (Integer num : nums) {
            int ind = 0;
            while (num > 0) {
                ind++;
                num /= 2;
            }
            cnt[ind - 1]++;
        }

        int[] need = new int[32];
        int ind = 0;
        while (target > 0) {
            need[ind++] = (target & 1);
            target >>= 1;
        }

        int ans = 0;
        for (int i = 0; i < need.length; i++) {
            if (need[i] > 0) {
                int j = i;
                while (j < need.length && cnt[j] == 0) {
//                    cnt[j] = j == i ? 2 : 1;
                    cnt[j++] = 1;
                    ans++;
                }
                cnt[j]--;
//                if (j > i) cnt[i]--;
            }

            if (i + 1 < need.length && cnt[i] > 1) {
                cnt[i + 1] += cnt[i] / 2;
                cnt[i] &= 1;
            }
        }
        return ans;
    }


    public long minimumPossibleSum(int n, int target) {
        long ans = 0L;
        int i = 1;
        for (; i <= target - i && i <= n; i++) {
            ans += i;
        }
        n -= i;
        while (n >= 0) {
            ans += target;
            target++;
            n--;
        }
        return ans;

    }
}
