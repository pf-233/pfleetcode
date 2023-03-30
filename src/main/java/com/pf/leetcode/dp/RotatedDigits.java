package com.pf.leetcode.dp;

import java.util.HashSet;
import java.util.Set;

public class RotatedDigits {

    char[] arr;
    int[] memo;
    static int[] digits = new int[] {0, 1, 2, 5, 6, 8, 9};
    static Set<Integer> changeNumSet = new HashSet() {
        {
            add(2);
            add(5);
            add(6);
            add(9);
        }
    };

    public static void main(String[] args) {
        RotatedDigits rotatedDigits = new RotatedDigits();
        System.out.println(rotatedDigits.rotatedDigits(10));
    }
    public int rotatedDigits(int n) {
        arr = Integer.toString(n).toCharArray();
        memo = new int[10005];
        return helper(0, 0, true, false);
    }

    private int helper(int deep, int changeCount, boolean isLimit, boolean isNum) {
        if (deep == arr.length) {
            return (isNum && changeCount > 0) ? 1 : 0;
        }

        int up = isLimit ? arr[deep] - '0' : 9;
        int ans = 0;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] > up) {
                break;
            }
            ans += helper(deep + 1, changeCount + (changeNumSet.contains(i) ? 1 : 0), isLimit && digits[i] == up, isNum || digits[i] > 0);
        }
        return ans;
    }
}
