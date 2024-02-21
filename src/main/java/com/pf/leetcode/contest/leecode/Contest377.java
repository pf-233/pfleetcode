package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：2023/12/24
 * Description:
 */
public class Contest377 {

    public static void main(String[] args) {
        Contest377 contest = new Contest377();
        String source;
        String target;
        char[] original;
        char[] changed;
        int[] cost;
        source="abcd";
        target="acbe";
        original = "abcced".toCharArray();
        changed="bcbebe".toCharArray();
        cost = new int[]{2,5,5,1,2,20};
        System.out.println(contest.minimumCost(source, target, original, changed, cost));


//        int m;
//        int n;
//        int[] hFences;
//        int[] vFences;
//        m = 4;
//        n = 3;
//        hFences = new int[]{2,3};
//        vFences = new int[]{2};
//        System.out.println(contest.maximizeSquareArea(m, n, hFences, vFences));


    }


    private void shortest(Map<String, Map<String, Long>> grid) {
        String[] keys = (String[]) grid.keySet().stream().toArray();
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] grid = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(grid[i], -1);
            grid[i][i] = 0;
        }
        int n = original.length;
        for (int i = 0; i < n; i++) {
            grid[original[i] - 'a'][changed[i] - 'a'] = grid[original[i] - 'a'][changed[i] - 'a'] == -1 ? cost[i] : Math.min(grid[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }

        long minCost = 0L;
        for (int i = 0; i < source.length(); i++) {
            int costs = grid[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
            if (costs == -1) return -1;
            minCost += costs;
        }
        return minCost;
    }

    private void shortest(int[][] grid) {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    if (grid[j][i] >= 0 && grid[i][k] >= 0) {
                        int diff = grid[j][i] + grid[i][k];
                        grid[j][k] = grid[j][k] == -1 ?  diff : Math.min(diff, grid[j][k]);
                    }
                }
            }
        }
    }



    int mode = (int)1e9 + 7;
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        long area = 0L;
        Set<Integer> set = new HashSet();
        set.add(m - 1);
        for (int i = 0; i < hFences.length; i++) {
            // 第一个
            set.add(hFences[i] - 1);
            for (int j = 0; j < hFences.length; j++) {
                if (j == i) continue;
                set.add(Math.abs(hFences[i] - hFences[j]));
            }
            set.add(m - hFences[i]);
        }

        if (set.contains(n - 1)) {
            return (int) (1L * (n - 1) * (n - 1) % mode);
        }
        for (int i = 0; i < vFences.length; i++) {
            // 第一个
            if (set.contains(vFences[i] - 1)) {
                area = Math.max(area, 1L * (vFences[i] - 1) * (vFences[i] - 1));
            }
            for (int j = 0; j < vFences.length; j++) {
                if (j == i) continue;
                int diff = Math.abs(vFences[i] - vFences[j]);
                if (set.contains(diff)) {
                    area = Math.max(area, 1L * diff * diff);
                }
            }
            int diff = n - vFences[i];
            if (set.contains(diff)) {
                area = Math.max(area, 1L * diff * diff);
            }
        }
        return area == 0 ? -1 : (int) (area % mode);
    }
}
