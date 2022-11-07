package com.pf.leetcode.contest.leecode;

public class Contest272 {
    public static void main(String[] args) {
        Contest272 contest272 = new Contest272();
        int[] spaces = new int[] {8,13,15};
        System.out.println(contest272.addSpaces("LeetcodeHelpsMeLearn", spaces));

//        int[] prices = new int[] {3,2,1,4};
        // 50

        // 7  6 * 7 / 2  + 7 = 28
        // 3  3 * 2 / 2  + 3 = 6
        // 2  2 * 1 / 2  + 2 = 2
        // 4  4 * 3 / 2  + 4 = 10
        int[] prices = new int[] {3,9,8,7,6,5,4,3,5,4,3,4,3,3,3,7,6,5,4};
        System.out.println(contest272.getDescentPeriods(prices));
    }
    static long[] helper = new long[10005];
    static {
        helper[1] = 1;
        for (int i = 2; i < 10005; i++) {
            helper[i] = helper[i - 1] + 1L * (i - 1) + 1;
        }
    }
    public long getDescentPeriods(int[] prices) {
        long res = 0;
        int nowInd = 0;
        int preInd = 0;
        long tmpres = 0;
        int tmpCount = 1;
        while (nowInd < prices.length) {
            if (preInd == nowInd) {
                tmpres = helper[tmpCount];
                nowInd++;
                continue;
            }
            if (prices[nowInd] + 1 == prices[preInd]) {
                tmpCount++;
                tmpres = helper[tmpCount];
            } else {
                res += tmpres;
                tmpCount = 1;
            }
            preInd++;
            nowInd++;
        }
        return res + helper[tmpCount];
    }

    public String addSpaces(String s, int[] spaces) {
        int len = s.length() + spaces.length;
        int ind = 0;
        int top = 0;
        char[] res = new char[len];
        for (int i = 0; i < spaces.length; i++) {
            while (ind < spaces[i] && ind < s.length()) {
                res[top++] = s.charAt(ind);
                ind++;
            }
            res[top++] = ' ';
        }
        return new String(res);
    }
}
