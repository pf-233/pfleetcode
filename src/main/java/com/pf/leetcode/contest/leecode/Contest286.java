package com.pf.leetcode.contest.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Contest286 {

    public static void main(String[] args) {
        Contest286 contest286 = new Contest286();
//        System.out.println(contest286.minDeletion(new int[]{1,1,2,2,3,3}));
        int[] queries = new int[]{2,201429812,8,520498110,492711727,339882032,462074369,9,7,6};
//        System.out.println(contest286.kthPalindrome(queries, 1));
        List<List<Integer>> piles = new ArrayList<>();

        piles.add(Arrays.asList(1,100,3));
        piles.add(Arrays.asList(7,8,9));
        System.out.println(contest286.maxValueOfCoins(piles, 2));
        //        [37,88],[51,64,65,20,95,30,26],[9,62,20],[44]
//        piles.add(Arrays.asList(37,88));
//        piles.add(Arrays.asList(51,64,65,20,95,30,26));
//        piles.add(Arrays.asList(9,62,20));
//        piles.add(Arrays.asList(44));
//        System.out.println(contest286.maxValueOfCoins(piles, 9));
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] dp = new int[k + 1];
        for (List<Integer> tmpList : piles) {
            int max = Math.min(tmpList.size(), k);
            int[] counts = new int[max];
            counts[0] = tmpList.get(0);
            for (int i = 1; i < max; i++) {
                counts[i] = counts[i - 1] + tmpList.get(i);
            }

            for (int i = k; i >= counts.length; i--) {
                for (int w = 0; w < i && w < counts.length; w++) {
                    dp[i] = Math.max(dp[i], dp[i - w - 1] + counts[w]);
                }
            }
        }
        return dp[k];
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] counts = getCounts(intLength);
        long[] res = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > counts[0]) {
                res[i] = -1;
                continue;
            }
            String str = getCount(queries[i], counts, 0);
            StringBuilder tmp = null;
            if (intLength % 2 == 1) {
                tmp = new StringBuilder(str.substring(0, str.length() - 1));
            } else {
                tmp = new StringBuilder(str.substring(0, str.length()));
            }
            tmp.reverse();
            str += tmp.toString();
            res[i] = Long.valueOf(str);
        }
        return res;
    }

    String getCount(int ind, long[] counts, int deep) {
        if (ind <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (deep == counts.length - 1) {
            sb.append(ind - 1 + "");
        } else {
            int i = deep == 0 ? 1 : 0;
            for (; i < 10 && ind - counts[deep + 1] > 0; i++) {
                ind -= counts[deep + 1];
            }
            sb.append(i + getCount(ind, counts, deep + 1));
        }
        return sb.toString();
    }

    long[] getCounts(int intLength) {
        int len = intLength / 2 + (intLength % 2);
        if (len == 1) {
            return new long[]{9};
        }
        long[] count = new long[len];
        count[len - 1] = 10;
        for (int i = len - 2; i > 0; i--) {
            count[i] = count[i + 1] * 10;
        }
        count[0] = count[1] * 9;
        return count;
    }

    public int minDeletion(int[] nums) {
        int len = nums.length;
        int remove = 0;
        for (int i = 0; i < nums.length; i++) {
            if (((i - remove) & 1) == 0) {
                int tmp = i;
                while (tmp + 1 < len && nums[i] == nums[tmp + 1]) {
                    remove++;
                    tmp++;
                }
                i = tmp;
            }
        }
        if ((len - remove) % 1 == 1) {
            remove++;
        }
        return remove;
    }
}
