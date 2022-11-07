package com.pf.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckRecord {
    int mode = 1000000007;

    public static void main(String[] args) {
        CheckRecord checkRecord = new CheckRecord();
        int[][][] res1 = checkRecord.checkRecord(10101);
        int[][][] res2 = checkRecord.checkRecord1(10101);
        for (int i = 0; i < res1.length; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    System.out.println(String.format("i=%s, j=%s, k=%s, res1=%s, res2 = %s", i, j, k, res1[i][j][k], res2[i][j][k]));
                    if (res1[i][j][k] != res2[i][j][k]) {
                        System.out.println("===");
                    }
                }
            }
        }
    }


    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int left = 0;
        int ri = chs.length - 1;
        Set<Character> set = Arrays.asList('a','e','i','o','u','A','E','I','O','U').stream().collect(Collectors.toSet());
        while (left < ri) {
            while (left < chs.length && !set.contains(chs[left])) {
                left++;
            }

            while (ri >= 0 && !set.contains(chs[ri])) {
                ri--;
            }
            if (left < ri) {
                char tmp = chs[left];
                chs[left] = chs[ri];
                chs[ri] = tmp;
            }
        }
        return new String(chs);
    }

    public int[][][]  checkRecord(int n) {
        int[][][] res = new int[n + 1][3][2];
        res[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // PP                     LP                LLP
            res[i][0][0] = (((res[i - 1][0][0]  % mode) + (res[i - 1][1][0]   % mode)) % mode + (res[i - 1][2][0]   % mode)) % mode;
            //PA LA LLA           AP                 ALP             ALLP
            res[i][0][1] = (((res[i][0][0] + (res[i - 1][0][1] % mode)) % mode  + (res[i - 1][1][1]  % mode)) % mode   + (res[i - 1][2][1]  % mode)) % mode;
            //PL
            res[i][1][0] = res[i - 1][0][0] % mode;
            res[i][1][1] = res[i - 1][0][1] % mode;
            res[i][2][0] = res[i - 1][1][0] % mode;
            res[i][2][1] = res[i - 1][1][1] % mode;
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                count = (count + res[n][i][j]) % mode;
            }
        }
        return res;
    }

    public int[][][] checkRecord1(int n) {
        int[][][] res = new int[n + 1][3][2];
        res[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            //Oa 下最后是P
            for (int j = 0; j < 3; j++) {
                res[i][0][0] = (res[i][0][0] + res[i - 1][j][0]) % mode;
            }
            // 0A 下最后是A
            for (int j = 0; j < 3; j++) {
                res[i][0][1] = (res[i][0][1] + res[i - 1][j][0]) % mode;
            }
            // 1A 下最后是P
            for (int j = 0; j < 3; j++) {
                res[i][0][1] = (res[i][0][1] + res[i - 1][j][1]) % mode;
            }
            // 最后是L
            for (int j = 1; j < 3; j++) {
                res[i][j][0] = res[i - 1][j - 1][0] % mode;
                res[i][j][1] = res[i - 1][j - 1][1] % mode;
            }
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                count = (count + res[n][i][j]) % mode;
            }
        }
        return res;
    }
}
