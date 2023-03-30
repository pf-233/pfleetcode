package com.pf.leetcode.dp;

import java.util.Arrays;

public class AtMostNGivenDigitSet {
    int[] arr;
    int[] digitArr;
    int[][] memo;

    public static void main(String[] args) {
        AtMostNGivenDigitSet atMostNGivenDigitSet = new AtMostNGivenDigitSet();
        String[] digits = new String[]{"1","3","5","7"};
        int n = 100;
        System.out.println(atMostNGivenDigitSet.atMostNGivenDigitSet(digits, n));
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] ch = Integer.toString(n).toCharArray();
        int len = ch.length;
        arr = new int[len];
        digitArr = new int[digits.length];
        // 第i个数以j结尾
        memo = new int[len][10];
        for (int i = 0; i < len; i++) {
            arr[i] = ch[i] - '0';
            Arrays.fill(memo[i], -1);
        }

        for (int i = 0; i < digits.length; i++) {
            digitArr[i] = digits[i].charAt(0) - '0';
        }
        return helper(0, 0, true, false);
    }

    private int helper(int deep, int num, boolean isLimit, boolean isNum) {
        if (deep == arr.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && memo[deep][num] != -1) {
            return memo[deep][num];
        }

        int up = isLimit ? arr[deep] : digitArr[digitArr.length - 1];
        // 非数字
        int ans = isNum ? 0 : helper(deep + 1, 0, false, false);
        for (int i = 0; i < digitArr.length; i++) {
            if (isLimit && digitArr[i] > up) {
                break;
            }
            ans += helper(deep + 1, digitArr[i], isLimit && digitArr[i] == up, true);
        }
        if (!isLimit) {
            memo[deep][num] = ans;
        }
        return ans;
    }
}
