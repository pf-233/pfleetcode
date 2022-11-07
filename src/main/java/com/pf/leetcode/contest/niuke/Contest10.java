package com.pf.leetcode.contest.niuke;

import java.util.Arrays;

public class Contest10 {
    public static void main(String[] args) {
        Contest10 contest10 = new Contest10();
//        System.out.println(contest10.GetNumberOfPath(4,4,2,2,3,3));
//        System.out.println(contest10.longestAwesome("3242415"));
//        System.out.println(contest10.longestAwesome("12345678"));
//        System.out.println(contest10.longestAwesome("213123"));
//        System.out.println(contest10.longestAwesome("00"));

//        System.out.println(contest10.findKthBit(3,1));
    }


    public int longestAwesome(String s) {
        int[] state = new int[1 << 11];
        int[] count = new int[10];
        Arrays.fill(state, -1);
        state[0] = 0;
        int max = 1;
        int pre = 0;
        for(int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            count[num] = (count[num] + 1) % 2;
            int nowState = pre ^ (1 << num);
            if(state[nowState] < 0) {
                state[nowState] = i;
            }
            max = Math.max(max, nowState == 0 ? i + 1 : i - state[nowState]);
            pre = nowState;

            for(int j = 0; j < 10; j++) {
                int tmp = nowState ^ (1 << j);
                if(state[tmp] >= 0) {
                    max = Math.max(max, tmp == 0 ? i + 1 - state[tmp] : i - state[tmp]);
                }
            }
        }
        return max;

    }
    public int GetNumberOfPath (int n, int m, int x0, int y0, int x1, int y1) {
        // write code here
        int mode = 1000000007;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 0; i <= n; i++) {
            dp[1][i] = 1;
        }
        for(int i = 0; i <= m; i++) {
            dp[i][1] = 1;
        }
        int[][] dis = new int[][]{
                {-1,0},
                {0,-1}
        };

        for(int i = 2; i <= m; i++) {
            for(int j = 2; j <= n; j++) {
                if(isDeath(j, i, x0, y0, x1, y1)){
                    continue;
                }
                for(int k = 0; k < dis.length; k++) {
                    int tmpy = i + dis[k][0];
                    int tmpx = j + dis[k][1];
                    if(isDeath(tmpx, tmpy, x0, y0, x1, y1)){
                        continue;
                    }
                    if(tmpx >= 1 && tmpx <= n && tmpy >= 1 && tmpy <= m) {
                        dp[i][j] += dp[tmpy][tmpx];
                        dp[i][j] %= mode;
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean isDeath(int tmpx, int tmpy, int x0, int y0, int x1, int y1) {
        return x0 <= tmpx && x1>= tmpx && y0 <= tmpy && y1 >= tmpy;
    }
}
