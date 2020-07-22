package com.pf.leetcode.dp;

public class IsInterleave {
    public static void main(String[] args) {
        IsInterleave isInterleave = new IsInterleave();
        System.out.println(isInterleave.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
    char[] c1;
    char[] c2;
    char[] c3;
    int[][] vis;
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3 == null || "".equals(s3)) {
            return (s2 == null || "".equals(s2) ) && (s1 == null || "".equals(s1) );
        } else if ((s2 == null || "".equals(s2) ) && (s1 == null || "".equals(s1) ) ) {
            return s3 == null || "".equals(s3);
        } else if (s2 == null || "".equals(s2) ) {
            return s1.equals(s3);
        } else if (s1 == null || "".equals(s1)){
            return s2.equals(s3);
        }
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();
        c3 = s3.toCharArray();
        if(c1.length + c2.length != c3.length) {
            return false;
        }
        vis = new int[c1.length][c2.length];
        return find(0,0);
    }

    boolean find(int c1Ind, int c2Ind) {
        int len = c1Ind + c2Ind;
        if(len == c3.length) {
            return true;
        }
        if (c1Ind < c1.length && c2Ind < c2.length && vis[c1Ind][c2Ind] != 0) {
            return vis[c1Ind][c2Ind] == 1;
        }
        if(c1Ind >= c1.length) {
            for(int i = c2Ind; i < c2.length; i++) {
                if(c3[len++] != c2[i]){
                    vis[c1Ind - 1][c2Ind] = 2;
                    return false;
                }
            }
            vis[c1Ind - 1][c2Ind] = 1;
            return true;
        } else if (c2Ind >= c2.length) {
            for(int i = c1Ind; i < c1.length; i++) {
                if(c3[len++] != c1[i]){
                    vis[c1Ind][c2Ind - 1] = 2;
                    return false;
                }
            }
            vis[c1Ind][c2Ind - 1] = 1;
            return true;
        }

        boolean flag = false;
        if(c1[c1Ind] == c3[len]) {
            flag = find(c1Ind + 1, c2Ind);
        }
        if(c2[c2Ind] == c3[len]) {
            flag = flag || find(c1Ind, c2Ind + 1);
        }
        vis[c1Ind][c2Ind] = flag ? 1 : 2;
        return flag;
    }
}
