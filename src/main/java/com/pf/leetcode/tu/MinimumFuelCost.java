package com.pf.leetcode.tu;

import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * author：panf
 * date：2023/12/5
 * Description:
 */
public class MinimumFuelCost {
    public static void main(String[] args) {

    }


    boolean[] vis;
    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0) return 0L;
        vis = new boolean[roads.length + 1];
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < roads.length; i++) {
            List<Integer> list = map.getOrDefault(roads[i][0], new LinkedList());
            list.add(roads[i][1]);
            map.put(roads[i][0], list);

            list = map.getOrDefault(roads[i][1], new LinkedList());
            list.add(roads[i][0]);
            map.put(roads[i][1], list);
        }
        return dfs(map, seats, 0)[1];
    }

    private long[] dfs(Map<Integer, List<Integer>> map, int seats, int node) {
        vis[node] = true;
        // 最多2*(n-1) 条边被存储
        List<Integer> list = map.get(node);
        long[] ans = new long[]{1, 0};
        boolean leaf = true;
        for (Integer nextNode : list) {
            if (vis[nextNode]) continue;
            long[] temp = dfs(map, seats, nextNode);
            leaf = false;
            ans[0] += temp[0];
            ans[1] += temp[1] + temp[0] / seats + (temp[0] % seats == 0 ? 0 : 1);
        }
        if (leaf) {
            // 总共1个节点， 下面节点到本节点需要0升
            return new long[] {1L, 0L};
        }
        return ans;
    }
}
