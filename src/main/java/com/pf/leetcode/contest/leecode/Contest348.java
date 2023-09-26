package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Contest348 {
    public static void main(String[] args) {
        Contest348 contest348 = new Contest348();
        System.out.println(contest348.getNumber("1"));
        System.out.println(contest348.getNumber("10000"));
        System.out.println(contest348.getNumber("10009"));

        String num1 = "1";
        String num2 = "12";
        int min_sum = 1;
        int max_sum = 8;
        System.out.println(contest348.count(num1, num2, min_sum, max_sum));
    }
    int mode = (int)(10e9 + 7);
    int[][][] memo;
    String n1;
    String n2;
    int minSum;
    int maxSum;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        int len = num2.length();
        // 位数个数，是否限制0不限制，1限制， 当前和
        memo = new int[len + 1][2][max_sum + 1];
        n1 = num1;
        n2 = num2;
        minSum = min_sum;
        maxSum = max_sum;
        for (int i = 0; i < len + 1; i++) {
            Arrays.fill(memo[i][0], -1);
            Arrays.fill(memo[i][1], -1);
        }
        int cnt2 = dp(0, 1, num2, 0 ) % mode;
        for (int i = 0; i < len + 1; i++) {
            Arrays.fill(memo[i][0], -1);
            Arrays.fill(memo[i][1], -1);
        }
        int cnt1 = dp(0, 1, getNumber(num1), 0) % mode;
        return (cnt2 - cnt1) + mode % mode;
    }

    private int dp(int deep, int limit, String num, int nowSum) {
        if (deep == num.length()) {
            return (nowSum <= maxSum && nowSum >= minSum) ? 1 : 0;
        }
        if (memo[deep][limit][nowSum] != -1) {
            return memo[deep][limit][nowSum];
        }
        //限制就用当前的，不限制就是9
        int uplimit = limit == 1 ? num.charAt(deep) - '0' : 9;
        //还有限制不可以大于最大个数和
        uplimit = Math.min(uplimit, maxSum - nowSum);
        // 不能有前缀0
//        int start = deep == 0 ? 1 : 0;
        long ans = 0L;
        for (int i = 0; i <= uplimit; i++) {
            // 当前是否是上界
            int nextLimit = i == uplimit ? 1 : 0;
            // 上界且之前是有限制的。 没有限制的话当前也不限制
            nextLimit &= limit;
            ans += dp(deep + 1, nextLimit, num, nowSum + i);
        }

        memo[deep][limit][nowSum] = (int)(ans % mode);
        return memo[deep][limit][nowSum];
    }

    private String getNumber(String num) {
        if (num.length() == 1) {
            return (num.charAt(0) - 1 - '0') + "";
        }
        int i = num.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 && num.charAt(i) == '0') {
            sb.append("9");
            i--;
        }

        if (i == 0) {
            int first = (num.charAt(i) - 1 - '0');
            return first > 0 ? first + sb.toString() : sb.toString();
        } else {
            return num.substring(0, i) + (num.charAt(i) - 1 - '0') + sb.toString();
        }
    }
}
