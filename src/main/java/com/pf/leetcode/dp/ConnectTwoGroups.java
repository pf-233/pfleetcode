package com.pf.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectTwoGroups {
    public static void main(String[] args) {
        ConnectTwoGroups connectTwoGroups = new ConnectTwoGroups();
        List<List<Integer>> cost;
        cost = new ArrayList<List<Integer>>() {
            {
                add(Arrays.asList(15,96));
                add(Arrays.asList(36,2));
            }
        };
        System.out.println(connectTwoGroups.connectTwoGroups(cost));
    }
    List<List<Integer>> costs;
    int[] min2;
    int[][] memo;
    int size1;
    int size2;
    public int connectTwoGroups(List<List<Integer>> cost) {
        costs = cost;
        size1 = cost.size();
        size2 = cost.get(0).size();
        memo = new int[size1][1 << size2];
        for (int i = 0; i < size1; i++) {
            Arrays.fill(memo[i], -1);
        }
        min2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            min2[i] =  Integer.MAX_VALUE;
            for (int j = 0; j < size1; j++) {
                min2[i] = Math.min(min2[i], cost.get(j).get(i));
            }
        }
        return dfs(0, 0);
    }

    // group2的选择情况0为未选1为已选
    // 当前是group1的index
    private int dfs(int state2, int index1) {
        // 当index = size1的时候选完了
        if (index1 == size1) {
            // 如果group2还有没选的就选最小的，都选了就返回0;
            return minGroup2(state2);
        }
        if (memo[index1][state2] != -1) {
            return memo[index1][state2];
        }

        // 如果不是就两种当前已选里的最小的的选一个和未选的里面选一个
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size2; i++) {
            min = Math.min(min, dfs(state2 | (1 << i), index1 + 1) + costs.get(index1).get(i));
        }
        return memo[index1][state2] = min;
    }

    private int minGroup2(int state2) {
        int ans = 0;
        for (int i = 0; i < size2; i++, state2 >>= 1) {
            if ((state2 & 1) == 0) {
                ans += min2[i];
            }
        }
        return ans;
    }
}
