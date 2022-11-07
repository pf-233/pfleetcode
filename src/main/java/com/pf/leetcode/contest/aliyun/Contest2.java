package com.pf.leetcode.contest.aliyun;

import java.util.*;
import java.util.stream.Collectors;

public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest1 = new Contest2();
//        paper();
//        List<String> res = contest1.stringPermutation("aabb");
//        System.out.println(res);
        System.out.println(contest1.firstWillWin(new int[]{1,20,4}));
    }

    int[] values;
    public boolean firstWillWin(int[] values) {
        // write your code here
        this.values = values;
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        int left = 0;
        int ri = values.length - 1;
        int[] val = new int[2];
        int[][] dp = new int[values.length + 1][val.length + 1];
        return helper(left, ri, dp) > sum - helper(left, ri, dp) ;
    }


    private int helper(int left, int ri, int[][] dp) {
        if (left > ri || ri < 0) {
            return 0;
        }
        if (dp[left][ri] != 0) {
            return dp[left][ri];
        }
        int tmp1 = helper(left, ri - 1, dp) + values[ri];
        int tmp3 = helper(left + 1, ri, dp) + values[left];
        
        return dp[left][ri];
    }

    public List<String> stringPermutation(String str) {
        // write your code here
        Set<String> res = new TreeSet<>();
        Map<Character, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < str.length(); i++) {
            int count = countMap.getOrDefault(str.charAt(i), 0);
            countMap.put(str.charAt(i), count + 1);
        }

        dfs(countMap, res, "", str.length());
        return res.stream().collect(Collectors.toList());
    }

    private void dfs(Map<Character, Integer> countMap,Set<String> res, String pre, int len) {
        if (pre.length() == len) {
            res.add(pre);
            return;
        }
        Character preCh = pre.length() == 0 ? null : pre.charAt(pre.length() - 1);
        int kind = 0;
        Character tmp = null;
        for (Character ch : countMap.keySet()) {
            if (countMap.get(ch) > 0) {
                kind++;
                tmp = ch;
            }
        }
        if (kind == 1) {
            while (pre.length() != len) {
                pre += tmp;
            }
            res.add(pre);
            return;
        }
        for (Character ch : countMap.keySet()) {
            if (preCh == ch) {
                continue;
            }
            int count = countMap.get(ch);
            StringBuilder tmpPre = new StringBuilder(pre);
            int k = 0;
            while (k < count) {
                tmpPre.append(ch);
                k++;
            }
            for (int i = count; i > 0; i--) {
                countMap.put(ch, count - i);
                dfs(countMap, res, tmpPre.substring(0, tmpPre.length() - count + i), len);
                countMap.put(ch, count);
            }
        }
    }


    private static void paper() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        if (count == 1) {
            System.out.println("0");
            return;
        }
        String now = "0";
        System.out.println(helper(count - 1, now));
    }

    public String getString(int n) {
        // Write your code here
        String now = "0";
       if (n == 1) {
           return now;
       }
       return helper(n - 1, now);
    }
    private static String helper(int count, String pre) {
        if (count == 0) {
            return pre;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (int i = 0; i < pre.length(); i++) {
            sb.append(k % 2).append(pre.charAt(i));
            k++;
        }
        sb.append(k % 2);
        return helper(count - 1, sb.toString());
    }
}
