package com.pf.leetcode.twopoint;

import java.util.Arrays;

/**
 * author：panf
 * date：11/26/2023
 * Description:
 */
public class UniqueLetterString {
    public static void main(String[] args) {
        UniqueLetterString uniqueLetterString = new UniqueLetterString();
        System.out.println(uniqueLetterString.uniqueLetterString("ABC"));
    }
    public int uniqueLetterString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] l = new int[n], r = new int[n];
        int[] idx = new int[26];
        Arrays.fill(idx, -1);
        for (int i = 0; i < n; i++) {
            int u = cs[i] - 'A';
            l[i] = idx[u];
            idx[u] = i;
        }
        Arrays.fill(idx, n);
        for (int i = n - 1; i >= 0; i--) {
            int u = cs[i] - 'A';
            r[i] = idx[u];
            idx[u] = i;
        }
        for (int i = 0; i < n; i++) ans += (i - l[i]) * (r[i] - i);
        return ans;
    }
}
