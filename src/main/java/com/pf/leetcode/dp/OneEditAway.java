package com.pf.leetcode.dp;

public class OneEditAway {
    public static void main(String[] args) {
        OneEditAway oneEditAway = new OneEditAway();
        System.out.println(oneEditAway.oneEditAway("ab", "bc"));
    }
    public boolean oneEditAway(String first, String second) {
        int lenf = first == null ? 0 : first.length();
        int lens = second == null ? 0 : second.length();
        if (Math.abs(lenf - lens) > 1) {
            return false;
        }
        if (Math.min(lenf, lens) == 0) {
            return true;
        }
        int[][] dp = new int[lenf + 1][lens + 1];
        for (int i = 0; i < lenf; i++) {
            for (int j = 0; j < lens; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    // 一样时直接前面就可以得到 或者删除i
                    dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    //或者删除j
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j + 1] + 1);
                } else {
                    // 替换i 或 j 中一个  或者删除j
                    dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i + 1][j]) + 1;
                    // 删除i
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j + 1] + 1);
                }

            }
        }
        return dp[lenf][lens] < 2;
    }
}
