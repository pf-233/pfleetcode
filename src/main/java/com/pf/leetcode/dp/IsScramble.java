package com.pf.leetcode.dp;

public class IsScramble {

    public static void main(String[] args) {
        IsScramble isScramble = new IsScramble();
        System.out.println(isScramble.isScramble("great", "etagr"));

    }

    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        // 1 是可以 2 是不可以
        int[][][][] dp = new int[len][len][len][len];
        execute(dp, s1, s2, 0, len - 1, 0, len - 1);
        return dp[0][len - 1][0][len - 1] == 1;
    }

    private void execute(int[][][][] dp, String s1, String s2, int s1start, int s1end, int s2start, int s2end) {
        String t1 = s1.substring(s1start, s1end + 1);
        String t2 = s2.substring(s2start, s2end + 1);
        if (dp[s1start][s1end][s2start][s2end] > 0) {
            return;
        }
        if (s1start == s1end) {
            dp[s1start][s1end][s2start][s2end] = s1.charAt(s1start) == s2.charAt(s2start) ? 1 : 2;
            System.out.println(t1 + "  " + t2 + "  " + (dp[s1start][s1end][s2start][s2end] == 1));
            return;
        }
        for (int i = 0; i < s1end - s1start; i++) {
            int f1 = i + s1start;
            int f2 = i + s2start;
            int f11 = i + s1start + 1;
            int f22 = i + s2start + 1;
            // 不交换
            execute(dp, s1, s2, s1start, f1, s2start, f2);
            execute(dp, s1, s2, f11, s1end, f22, s2end);
            if (dp[s1start][f1][s2start][f2] == 1 && dp[f11][s1end][f22][s2end] == 1) {
                dp[s1start][s1end][s2start][s2end] = 1;
                System.out.println(t1 + "  " + t2 +"  " +  (dp[s1start][s1end][s2start][s2end] == 1));
                return;
            }

            // 交换
            f1 = i + s1start;
            f2 = s2end - i;
            f11 = i + s1start + 1;
            f22 = s2end - i - 1;
            execute(dp, s1, s2, s1start, f1, f2, s2end);
            execute(dp, s1, s2, f11, s1end, s2start , f22);
            if (dp[s1start][f1][f2][s2end] == 1 && dp[f11][s1end][s2start][f22] == 1) {
                dp[s1start][s1end][s2start][s2end] = 1;
                System.out.println(t1 + "  " + t2 + "  " + (dp[s1start][s1end][s2start][s2end] == 1));
                return;
            }
        }
        dp[s1start][s1end][s2start][s2end] = 2;
        System.out.println(t1 + "  " + t2 + "  " + (dp[s1start][s1end][s2start][s2end] == 1));
    }
}
