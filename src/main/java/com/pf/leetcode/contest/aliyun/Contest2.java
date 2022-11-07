package com.pf.leetcode.contest.aliyun;

import java.util.*;

public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest1 = new Contest1();
//        System.out.println(contest1.treePlanning(new int[]{1,2,3,5,6}, 2));
//        System.out.println(contest1.makeEquilateralTriangle(new int[]{2,3,7,5}));
//        System.out.println(contest1.makeEquilateralTriangle(new int[]{2,3,3,5}));
//        System.out.println(contest1.makeEquilateralTriangle(new int[]{2,3,6}));
        System.out.println(contest1.suffixQuery("bacbdab"));
        System.out.println(contest1.suffixQuery("b"));
//        System.out.println(contest1.shuttleInBuildings(new int[]{1,5,4,3,3,5}, 3,10,6));
    }

    public long shuttleInBuildings(int[] heights, int k, int x, int y) {
        // write your code here.
        long[] dp = new long[heights.length];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 1; j <= k && i + j < heights.length; j++) {
                if (heights[i + j] >= heights[i]) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + x);
                    break;
                }
            }
            for (int j = 1; j <= 2 && i + j < heights.length; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + y);
            }
        }
        return dp[heights.length - 1];
    }

    public long suffixQuery(String s) {
        // write your code here
        long count = 0L;
        if (s == null) {
            return count;
        }
        Map<String, Set<Integer>> revcountMap = new HashMap<>();
        String rev = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < rev.length(); i++) {
            for (int j = i; j < rev.length(); j++) {
                String s1 = rev.substring(i, j + 1);
                Set<Integer> tmpList = revcountMap.getOrDefault(s1, new HashSet<>());
                tmpList.add(rev.length() - 1 - j);
                revcountMap.put(s1, tmpList);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            Set<Integer> visSet = new HashSet<>();
            for (int j = s.length(); j > i; j--) {
                String s1 = s.substring(i, j);
                Set<Integer> tmpList = revcountMap.getOrDefault(s1, new HashSet<>());
                for (int ind : tmpList) {
                    if (ind - s1.length() + 1 >= i && !visSet.contains(ind)) {
                        count += s1.length();
                        System.out.println(s1);
                        visSet.add(i);
                    }
                }
            }
        }
        return count;
    }

    public int makeEquilateralTriangle(int[] lengths) {
        // write your code here.
        TreeMap<Integer, Integer> map = new TreeMap();
        int max = 0;
        for (int i = 0; i < lengths.length; i++) {
            int val = map.getOrDefault(lengths[i], 0);
            val++;
            max = Math.max(max, val);
            map.put(lengths[i], val);
        }
        if (max >= 2) {
            return 3 - max >= 0 ? 3 - max : 0;
        } else {
            for(int i = 0; i < lengths.length; i++) {
                if (map.get(lengths[i] * 2) != null) {
                    return 1;
                }
            }
            return 2;
        }
    }

    public int treePlanning(int[] trees, int d) {
        // write your code here.
        int pre = trees[0];
        int count = 0;
        for(int i = 1; i < trees.length; i++) {
            if(trees[i] - d >= pre) {
                pre = trees[i];
            } else {
                count++;
            }
        }
        return count;
    }
}
