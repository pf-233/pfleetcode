package com.pf.leetcode.other;

public class CompareVersion {

    public static void main(String[] args) {
        CompareVersion compareVersion = new CompareVersion();
        System.out.println(compareVersion.compareVersion("1.05", "1.1"));
    }
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        int res = 0;
        for (int i = 0; i < Math.max(s1.length, s2.length); i++) {
            int tmp1 = i < s1.length ? new Integer(s1[i]) : 0;
            int tmp2 = i < s2.length ? new Integer(s2[i]) : 0;
            System.out.println(tmp1 + " m " +tmp2);
            if (tmp1 == tmp2) {
                continue;
            }
            res = tmp1 - tmp2;
            break;
        }
        return res;
    }
}
