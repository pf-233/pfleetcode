package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contest354 {

    public static void main(String[] args) {
        Contest354 contest354 = new Contest354();
        String word;
        List<String> forbidden;
        // 4
//        word = "cbaaaabc";
//        forbidden = Arrays.asList( "aaa","cb");
        // 35
        word = "aaabccccacacacaabcbaaabacbbbcabcbcaacbabccbababcabaacaacbbcbaabc";
        forbidden = Arrays.asList("bbbacbcb","bcbaaabacb","abbbbcb","bcbcbac","cbaabbbbbb","bbbbaabcb","cccaaaacaa","cbabaaca","baaabacbb","abcabaacaa");
        System.out.println(contest354.longestValidSubstring(word, forbidden));;

//        int[] nums;
//        int k;
//        nums = new int[]{1,1,1,1};
//        k = 10;
//        System.out.println(contest354.maximumBeauty(nums, k));
    }

    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> set = new HashSet();
        set.addAll(forbidden);
        int len = word.length();
        int[] minInd = new int[len];
        for (int i = 0; i < len; i++) {
            int ind = 0;
            for (int j = 1; j <= Math.min(10, len - i); j++) {
                String temp = word.substring(i, i + j);
                if (set.contains(temp)) {
                    break;
                }
                ind = j;
            }
            minInd[i] = ind == 10 ? len - 1 : i + ind - 1;
        }

        int max = 0;
        // 以i为开始的时候一开始的结束是minInd[i]
        for (int i = 0; i < len; i++) {
            int end = minInd[i];
            int start = i;
            //每次缩小end， 当start 移动的时候以 start 开始的可选长度去做缩小
            while (start <= end) {
                end = Math.min(end, minInd[start++]);
            }
            max = Math.max(max, end - i + 1);
        }
        return max;
    }

    public int maximumBeauty(int[] nums, int k) {
        int[] diff = new int[400005];
        int a = 100000;
        for (int i = 0; i < nums.length; i++) {
            diff[nums[i] - k + a]++;
            diff[nums[i] + k + a + 1]--;
        }
        int max = 0;
        int sum = 0;
        for (int i = 0; i < diff.length; i++) {
            sum += diff[i];
            max = Math.max(max, sum);
        }
        return max;
    }
}
