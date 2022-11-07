package com.pf.leetcode.contest.niuke;

import java.util.*;

public class Contest12 {

    public static void main(String[] args) {
        Contest12 contest12 = new Contest12();
        int n = 5;
        Contest11.Point[] Edge = new Contest11.Point[]{
                new Contest11.Point(1, 2),
                new Contest11.Point(2, 3),
                new Contest11.Point(3, 4),
                new Contest11.Point(2, 5),
        };

        System.out.println(contest12.solve(5, 5, Edge));
    }

    public int solve(int n, int x, Contest11.Point[] Edge) {
        // write code here
        if (x == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> nextPoint = new HashMap();
        List<Integer> tmpList = null;
        for (int i = 0; i < Edge.length; i++) {
            tmpList = nextPoint.getOrDefault(Edge[i].x, new LinkedList());
            tmpList.add(Edge[i].y);
            nextPoint.put(Edge[i].x, tmpList);

            tmpList = nextPoint.getOrDefault(Edge[i].y, new LinkedList());
            tmpList.add(Edge[i].x);
            nextPoint.put(Edge[i].y, tmpList);
        }

        int[] deep = new int[n + 1];
        dfs(nextPoint, 1, deep, 1);

        int[] deep2 = new int[n + 1];
        dfs(nextPoint, x, deep2, 1);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (deep2[i] < deep[i]) {
                max = Math.max(max, deep[i]);
            }
        }
        return max;
    }

    private void dfs(Map<Integer, List<Integer>> nextPoint, Integer now, int[] deep, int nowDeep) {
        List<Integer> tmpList = nextPoint.getOrDefault(now, new LinkedList());

        deep[now] = nowDeep;
        for (Integer tmp : tmpList) {
            if (deep[tmp] > 0) {
                continue;
            }
            dfs(nextPoint, tmp, deep, nowDeep + 1);
        }
    }
}
