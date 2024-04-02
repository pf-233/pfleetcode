package com.pf.leetcode.tu;

import com.pf.leetcode.utils.InputOutputUtil;

import java.util.Arrays;

/**
 * author：panf
 * date：3/26/2024
 * Description:
 */
public class Graph {
    public static void main(String[] args) {
        int n;
        int[][] edges;
        n = 4;
        edges = InputOutputUtil.get2DimensionArray("[[0,2,5],[0,1,2],[1,2,1],[3,0,3]]");
        Graph graph = new Graph(n, edges);

//["Graph","shortestPath","shortestPath","addEdge","shortestPath"]
//[[4,[[0,2,5],[0,1,2],[1,2,1],[3,0,3]]],[3,2],[0,3],[[1,3,4]],[0,3]]
        graph.shortestPath(3, 2);
        graph.shortestPath(0, 3);
        graph.addEdge(new int[]{1,3,4});
        graph.shortestPath(0, 3);

    }

    int[][] grid;
    final int MAX = Integer.MAX_VALUE;
    int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(grid[i], MAX);
        for (int i = 0; i < edges.length; i++) {
            grid[edges[i][0]][edges[i][1]] = edges[i][2];
        }
    }

    public void addEdge(int[] edge) {
        grid[edge[0]][edge[1]] = edge[2];
    }

    public int shortestPath(int node1, int node2) {
        int[] path = new int[grid.length];
        boolean[] mark = new boolean[grid.length];
        Arrays.fill(path, MAX);
        path[node1] = 0;
        for (int i = 0; i < n; i++) {
            int min = MAX;
            int minInd = node1;
            for (int j = 0; j < n; j++) {
                if (mark[j]) continue;
                if (min > path[j]) {
                    min = path[j];
                    minInd = j;
                }
            }
            mark[minInd] = true;
            if (minInd == node2) break;
            for (int j = 0; j < n; j++) {
                if (mark[j] || grid[minInd][j] == MAX) continue;
                path[j] = Math.min(path[j], path[minInd] + grid[minInd][j]);
            }
        }
        return path[node2] == MAX ? -1 : path[node2];
    }
}
