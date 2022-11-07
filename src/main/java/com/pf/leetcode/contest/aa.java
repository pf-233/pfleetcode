package com.pf.leetcode.contest;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = new int[][] {
                {1,1,1,1},
                {1,0,0,0},
                {1,0,0,0},
                {1,0,0,0}
        };
//        System.out.println(solution.maxSideLength(mat, 6));
//        System.out.println(solution.reverse(123));
        System.out.println(solution.combinationSum4(new int[]{2,1,3}, 35));
    }

    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        Map<Integer, Integer> visMap = new HashMap();
        visMap.put(0, 1);
        return helper(nums, target, visMap);
    }

    private int helper(int[] nums, int target, Map<Integer, Integer> visMap) {
        if(nums == null || nums.length == 0 || target < 0) {
            visMap.put(target, 0);
            return 0;
        }
        if(visMap.get(target) != null) {
            return visMap.get(target);
        }
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            count += combinationSum4(nums, target - nums[i]);
        }
        visMap.put(target, count);
        return count;
    }

    public int reverse(int x) {
        if((x >= 0 && x < 10) || (x < 0 && x > -10)) {
            return x;
        }
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        boolean flag = x > 0;
        int res = 0;
        if (x > 0) {
            x = -x;
        }
        while(x < 0) {
            if(x % 10 != 0) {
                break;
            }
            x /= 10;
        }

        while(x < 0) {
            int tmp = x % 10;
            if((min + tmp) / 10 > res) {
                return 0;
            }
            res = res * 10 - tmp;
            x /= 10;
        }
        if(flag && res == min) {
            return 0;
        }
        return flag ? -res : res;
    }
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        long[][] sumRow = new long[n + 1][m + 1];
        long[][] sumCol = new long[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                sumRow[i][j] += sumRow[i][j - 1] + mat[i - 1][j - 1];
            }
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                sumCol[j][i] += sumCol[j - 1][i] + mat[j - 1][i - 1];
            }
        }
        Map<Integer, Set> map = new TreeMap<>((a,b)-> b - a);
        long[][] sum = new long[n + 1][m + 1];
        int min = 10000007;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j - 1] + sumRow[i][j - 1] + sumCol[i - 1][j] + mat[i - 1][j - 1];
                min = Math.min(min, mat[i - 1][j - 1]);
            }
        }
        if(threshold <= min) {
            return threshold == min ? 1 : 0;
        }

        int left = 1;
        int right = Math.min(n,m) + 1;
        int mid = 0;
        int res = 1;
        while(left < right) {
            mid = (right + left) / 2;
            if(hasArea(sum, threshold, mid)) {
                left = mid + 1;
                res = mid;
            } else {
                right = mid;
            }
        }
        return res;
    }

    private boolean hasArea(long[][] sum, int threshold, int len) {
        boolean flag = false;
        for(int i = len; i < sum.length; i++) {
            for(int j = len; j < sum[i].length; j++) {
                long num =  sum[i][j] + sum[i - len][j - len] - sum[i - len][j] - sum[i][j - len];
                if(num <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    public int closestToTarget(int[] arr, int target) {
        int[][] next = new int[arr.length][20];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                Arrays.fill(next[i], arr.length);
            } else {
                for (int j = 0; j < 20; j++) {
                    next[i][j] = next[i + 1][j];
                }
            }
            for (int j = 0; j < 20; j++) {
                if (((arr[i] >> j) & 1) == 0) {
                    next[i][j] = i;
                }
            }
        }
        int res = (int) 1e8;
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j < arr.length) {
                res = Math.min(res, Math.abs(tmp - target));
                int len = arr.length;
                for (int k = 0; k < 20; k++) {
                    if (((tmp >> k) & 1) == 0) {
                        continue;
                    }
                    len = Math.min(len, next[j][k]);
                }
                j = len;
                if (j < arr.length) {
                    tmp &= arr[j];
                }
            }
        }
        return res;
    }
}