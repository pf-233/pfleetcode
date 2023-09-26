package com.pf.leetcode.contest.leecode;

import java.util.*;

public class Spring2023 {
    public static void main(String[] args) {
        Spring2023 spring2023 = new Spring2023();
        String[] matrix = new String[] {"sd","ep"};
        String mantra = "speed";
        System.out.println(spring2023.extractMantra(matrix, mantra));;
    }



    Map<String, Integer> map; // deep + i,j  100 * 10000
    // 100 层 每层10000次。
    List<int[]>[] memo;
    String mantra;
    String[] matrix;
    public int extractMantra(String[] matrix, String mantra) {
        int row = matrix.length;
        int col = matrix[0].length();
        int[] cnt = new int[26];
        this.mantra = mantra;
        this.matrix = matrix;
        map = new HashMap();
        memo = new LinkedList[26];
        new HashSet<>();
        Arrays.setAll(memo, e -> new LinkedList());
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = matrix[i].charAt(j);
                cnt[ch - 'a']++;
                memo[ch - 'a'].add(new int[]{i, j});
            }
        }
        for (int i = 0; i < mantra.length(); i++) {
            if (cnt[mantra.charAt(i) - 'a'] == 0) {
                return -1;
            }
        }

        return dfs(0, 0, 0);
    }

    private int dfs(int deep, int row, int col) {
        if (deep >= mantra.length()) {
            return 0;
        }
        String key = deep + "-" + row +"," + col;
        if (map.get(key) != null) {
            return map.get(key);
        }
        char ch = mantra.charAt(deep);
        if (matrix[row].charAt(col) == ch) {
            int min = dfs(deep + 1, row, col) + 1;
            map.put(key, min);
            return min;
        }

        int min = Integer.MAX_VALUE;;
        for (int[] next : memo[ch - 'a']) {
            int stepAndExtract = Math.abs(next[0] - row) + Math.abs(next[1] - col) + 1;
            min = Math.min(stepAndExtract + dfs(deep + 1, next[0], next[1]), min);
        }
        map.put(key, min);
        return min;
    }
}
