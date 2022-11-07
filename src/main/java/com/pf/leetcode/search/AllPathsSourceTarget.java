package com.pf.leetcode.search;

import java.util.LinkedList;
import java.util.List;

public class AllPathsSourceTarget {

    public static void main(String[] args) {
        AllPathsSourceTarget all = new AllPathsSourceTarget();
        int[][] graph = new int[][] {
                {4,3,1},
                {3,2,4},
                {3},
                {4},
                {}
        };
        all.allPathsSourceTarget(graph);
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<List<Integer>> res = new LinkedList();
        LinkedList<Integer> vis = new LinkedList();
        vis.add(0);

        dfs(res, graph, 0, graph.length - 1, vis);
        List<List<Integer>> ans = (List<List<Integer>>)res;
        return ans;
    }

    private void dfs(LinkedList<List<Integer>> res, int[][] graph, int now, int target, LinkedList<Integer> vis) {
        for (int i : graph[now]) {
            vis.addLast(i);
            if (i == target) {
                List<Integer> tmp = new LinkedList();
                vis.stream().forEach(e -> tmp.add(e));
                res.add(tmp);
            }
            vis.removeLast();
        }
    }
}
