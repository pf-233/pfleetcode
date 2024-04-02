package com.pf.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * author：panf
 * date：3/17/2024
 * Description:
 */
public class FindMinHeightTrees {

    public static void main(String[] args) {
        FindMinHeightTrees findMinHeightTrees = new FindMinHeightTrees();
        int[][] edges;
//        edges = new int[][] {
//                {1,0},{1,2},{1,3}
//        };
        edges = new int[][] {
                {3,0},{3,1},{3,2},{3,4},{5,4}
        };
        System.out.println(findMinHeightTrees.findMinHeightTrees(edges.length + 1, edges));
    }
    Map<Integer, List<int[]>> edgeMap;
    int[] ans;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        ans = new int[n];
        edgeMap = new HashMap();
        for (int i = 0; i < n; i++) {
            edgeMap.put(i, new LinkedList());
        }
        for (int i = 0; i < edges.length; i++) {
            edgeMap.get(edges[i][0]).add(new int[]{edges[i][1], 1});
            edgeMap.get(edges[i][1]).add(new int[]{edges[i][0], 1});
        }

        ans[0] = dfs(0, -1);
        dfs2(0, -1);
        int min = ans[0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, ans[i]);
        }
        List<Integer> answer = new LinkedList();
        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
            if (ans[i] == min) answer.add(i);
        }
        return answer;
    }

    private int dfs(int ind, int parent) {
        int max = 0;
        for (int[] next : edgeMap.get(ind)) {
            if (next[0] == parent) continue;
            int path = dfs(next[0], ind) + 1;
            next[1] = path;
            max = Math.max(max, path);
        }
        return max;
    }

    private void dfs2(int ind, int parent) {
        int max = 0;
        for (int[] next : edgeMap.get(ind)) {
            if (next[0] == parent) {
                int temp = 0;
                for (int[] before : edgeMap.getOrDefault(parent, new LinkedList<int[]>())) {
                    if (before[0] != ind) {
                        temp = Math.max(temp, before[1] + 1);
                    }
                }
                max = Math.max(max, temp);
            } else {
                max = Math.max(max, next[1]);
                dfs2(next[0], ind);
            }
        }
        ans[ind] = max;
    }
}
