package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class ContestShuang107 {


    public static void main(String[] args) {
        ContestShuang107 contestShuang107 = new ContestShuang107();
//        String[] words;
//        words = new String[] {"ab","cab","aa"};//6
//        System.out.println(contestShuang107.minimizeConcatenatedLength(words));

        int n;
        int[][] logs;
        int x;
        int[] queries;
//        n = 3;
//        logs = new int[][]{
//                {1,3},{2,6},{1,5}
//        };
//        x = 5;
//        queries = new int[]{10, 11};
        n = 2;
        logs = new int[][] {
                {1,12},{1,5},{2,13},{2,11},{1,23},{1,9},{2,7},{1,16},{2,16},{1,22}
        };
        x = 1;
        queries = new int[]{8,18,9,5};
        System.out.println(contestShuang107.countServers(n, logs, x, queries));
    }

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int[] memo = new int[1000005];
        Map<Integer, Integer> serverCount = new HashMap<>();
        int top = 0;
        int start = 0;
        for (int i = x; i < memo.length; i++) {
            while (top < logs.length && logs[top][1] <= i) {
                serverCount.put(logs[top][0], serverCount.getOrDefault(logs[top][0], 0) + 1);
                top++;
            }
            while (start < logs.length && logs[start][1] < i - x) {
                int count = serverCount.getOrDefault(logs[start][0], 0) - 1;
                if (count == 0) {
                    serverCount.remove(logs[start][0]);
                } else {
                    serverCount.put(logs[start][0], count);
                }
                start++;
            }
            memo[i] = n - serverCount.size();
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = memo[queries[i]];
        }
        return ans;
    }



    int[][][] memo;
    String[] words;
    public int minimizeConcatenatedLength(String[] words) {
        int len = words.length;
        this.words = words;
        memo = new int[len][26][26];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        memo[0][words[0].charAt(0) - 'a'][words[0].charAt(words[0].length() - 1) - 'a'] = words[0].length();
        for (int i = 1; i < len; i++) {
            int nowlen = words[i].length();
            int start = words[i].charAt(0) - 'a';
            int end = words[i].charAt(nowlen - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k <26; k++) {
                    if (memo[i - 1][j][k] == -1) {
                        continue;
                    }
                    //join(i, i + 1)
                    memo[i][j][end] = memo[i][j][end] == -1 ? memo[i - 1][j][k] + nowlen + (k == start ? -1 : 0) : Math.min(memo[i - 1][j][k] + nowlen + (k == start ? -1 : 0), memo[i][j][end]);
                    System.out.println(i + "," + j + "," + end + ":" +  memo[i][j][end]);
                    //join(i + 1, i)
                    memo[i][start][k] = memo[i][start][k] == -1 ? memo[i - 1][j][k] + nowlen + (j == end ? -1 : 0) : Math.min(memo[i - 1][j][k] + nowlen + (j == end ? -1 : 0), memo[i][start][k]);
                    System.out.println(i + "," + start + "," + k+ ":" +  memo[i][start][k]);
                }
            }
        }

        int min = 500000;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (memo[len - 1][i][j] > -1) {
                    min = Math.min(min, memo[len - 1][i][j]);
                }
            }
        }
        return min;
    }

}
