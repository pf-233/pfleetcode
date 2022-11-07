package com.pf.leetcode.contest.leecode;

public class Contest271 {

    public static void main(String[] args) {
        Contest271 contest271 = new Contest271();
//        System.out.println(contest271.countPoints("B0B6G0R6R0R6G9"));
                int[][] f = new int[][] {
//                        {2,8},{6,3},{8,6}
                        {0,9},{4,1},{5,7},{6,2},{7,4},{10,9}
                };
//                int startPos = 5;
//                int k = 4;
        int startPos = 5;
        int k = 4;
        System.out.println(contest271.maxTotalFruits(f, startPos, k));
    }

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int dis = 0;
        // 0 向左 1 向右
        int[][] counts = new int[2][k + 1];
        for (int i = 0; i < fruits.length; i++) {
            dis = fruits[i][0] - startPos;
            if (dis <= 0 && dis >= -k) {
                counts[0][-dis] = fruits[i][1];
            }
            if (dis >= 0 && dis <= k) {
                counts[1][dis] = fruits[i][1];
            }
        }

        int max = counts[0][0];
        for (int i = 1; i < counts[0].length; i++) {
            counts[0][i] += counts[0][i - 1];
            counts[1][i] += counts[1][i - 1];
            max = Math.max(counts[0][i], counts[1][i]);
        }

        for (int i = k; i >= 0; i--) {
            dis = (k - i) / 2;
            if (dis > 0) {
                max = Math.max(max, counts[0][i] + counts[1][dis] - counts[0][0]);
                max = Math.max(max, counts[1][i] + counts[0][dis] - counts[0][0]);
            }
        }
        return max;
    }

    public int countPoints(String rings) {
        int len = rings.length();
        int[] counts = new int[10];
        char[] chs = rings.toCharArray();

        for (int i = 0; i < len - 1; i++) {
            char color = chs[i];
            int ind = chs[i + 1] - '0';
            int tmp = 0;
            if (color == 'R') {
                tmp = 1;
            } else if (color == 'G') {
                tmp = 2;
            } else {
                tmp = 4;
            }

            if ((counts[ind] & tmp) == 0) {
                counts[ind] += tmp;
            }
        }

        int res = 0;
        for (int i = 0; i < 10; i++) {
            res += counts[i] == 7 ? 1 : 0;
        }
        return res;
    }
}
