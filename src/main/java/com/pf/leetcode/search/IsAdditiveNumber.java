package com.pf.leetcode.search;

import java.math.BigInteger;

public class IsAdditiveNumber {
    public static void main(String[] args) {
        IsAdditiveNumber isAdditiveNumber = new IsAdditiveNumber();
        System.out.println(isAdditiveNumber.isAdditiveNumber("000"));
    }

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        boolean res = false;
        for (int i = 0; i < len - 2; i++) {
            if (num.charAt(i + 1) == '0') {
                res |= dfs(num, 0, i, i + 1);
            }  else {
                for (int j = i + 1; j < len - 1; j++) {
                    res |= dfs(num, 0, i, j);
                }
            }
            if (num.charAt(0) == '0') {
                break;
            }
        }
        return res;
    }

    // [i, j] [j + 1, k]
    private boolean dfs(String num, int i, int j, int k) {
        if (k == num.length() - 1) {
            return true;
        }
        BigInteger a = new BigInteger(num.substring(i, j + 1));
        BigInteger b = new BigInteger(num.substring(j + 1, k + 1));
        BigInteger need = a.add(b);
        if (num.charAt(k + 1) == '0') {
            return need.compareTo(BigInteger.ZERO) == 0 ? dfs(num, j + 1, k, k + 1) : false;
        }
        for (int q = k + 1; q < num.length(); q++) {
            BigInteger tmp = new BigInteger(num.substring(k + 1, q + 1));
            if (tmp.compareTo(need) > 0) {
                return false;
            } else if (tmp.compareTo(need) == 0) {
                return dfs(num, j + 1, k, q);
            }
        }
        return false;
    }
}
