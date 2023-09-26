package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ContestShuang105 {


    public static void main(String[] args) {
        ContestShuang105 contestShuang105 = new ContestShuang105();
//        String s = "";
//        String[] dict = null;
//        s = "leetscode";
//        dict = new String[] {"leet","code","leetcode"};
////        "sayhelloworld"
////                ["hello","world"]
//        System.out.println(contestShuang105.minExtraChar(s, dict));;


        int[] nums = null;
//        nums = new int[]{1,2,3,0,0,0,-1,-2,-3};
        nums = new int[]{0, -1};
        System.out.println(contestShuang105.maxStrength(nums));
    }

    public long maxStrength(int[] nums) {
        Arrays.sort(nums);
        long mul = 1L;
        long neMul = 1L;
        int negativeCnt = 0;
        int zeroCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negativeCnt++;
                neMul *= nums[i];
            } else if (nums[i] == 0) {
                zeroCnt++;
            } else {
                mul *= nums[i];
            }
        }
        if (zeroCnt == nums.length) {
            return 0;
        }

        if (zeroCnt + negativeCnt == nums.length) {
            mul = 0L;
        }
        if (negativeCnt < 2) {
            neMul = 0L;
        } else if (neMul < 0) {
            neMul /= nums[negativeCnt - 1];
        }
        long ans = 0L;
        if (neMul != 0 && mul != 0) {
            ans = neMul * mul;
        } else if (neMul == 0 && mul == 0) {
            ans = 0;
        } else {
            ans = Math.max(neMul, mul);
        }
        return ans;
    }



    Set<String> set;
    String s;
    int[] memo;
    public int minExtraChar(String s, String[] dictionary) {
        set = new HashSet();
        this.s = s;
        memo = new int[60];
        Arrays.fill(memo, -1);
        for (String str : dictionary) {
            set.add(str);
        }
        return dfs(0);
    }

    private int  dfs(int ind) {
        if (ind >= s.length()) {
            return 0;
        }
        if (memo[ind] >= 0) {
            return memo[ind];
        }
        int min = s.length();
        for (int i = ind; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                //[i, j)
                String sub = s.substring(i, j);
                // 不包含的时候肯定是加上当前[i, j)区间 和dfs(j)开始的
                if (!set.contains(sub)) {
                    min = Math.min(min, j - i + dfs(j));
                } else {
                    //包含就把当前计算进去
                    min = Math.min(min, dfs(j));
                }

            }
        }
        memo[ind] = min;
        return min;
    }


}
