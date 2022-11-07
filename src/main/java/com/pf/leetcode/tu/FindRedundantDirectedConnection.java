package com.pf.leetcode.tu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindRedundantDirectedConnection {

    public static void main(String[] args) {
        int[][] ede = new int[][]{
                {1, 2},
                {1,3},
                {2,3}
        };
        System.out.println(new FindRedundantDirectedConnection().findRedundantDirectedConnection(ede));
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] res = null;
        for(int i = edges.length - 1; i >= 0; i--) {
            if (find(edges, i)) {
                return edges[i];
            }
        }
        return res;
    }

    private boolean find(int[][] edges, int ind) {
        int[] parent = new int[edges.length + 1];
        int[] count = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (ind == i) {
                continue;
            }
            if (count[edges[i][1]]++ > 0) {
                return false;
            }
            int x = find(edges[i][0], parent);
            int y = find(edges[i][1], parent);
            if (x != y) {
                union(x, y, parent);
            }
        }
        Set<Integer> rootSet = new HashSet<>();
        int rootCount = 0;
        for (int i = 1; i < edges.length + 1; i++) {
            rootSet.add(find(i, parent));
            if (count[i] == 0) {
                rootCount++;
            }
        }
        return rootSet.size() == 1 && rootCount == 1;
    }

    private int find(int x, int[] parent) {
        int now = x;
        while (parent[now] != now) {
            int tmp = now;
            now = parent[now];
            parent[tmp] = parent[parent[tmp]];
        }
        return now;
    }

    private void union(int x, int y, int[] parent) {
        int root = find(x, parent);
        while (parent[y] != root) {
            int tmp = parent[y];
            parent[y] = root;
            y = tmp;
        }
    }
}
