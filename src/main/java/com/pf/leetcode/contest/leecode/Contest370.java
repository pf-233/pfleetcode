package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：11/5/2023
 * Description:
 */
public class Contest370 {

    public static void main(String[] args) {
        Contest370 contest370 = new Contest370();
        int[][] edges = null;
        int[] values = null;

//        edges = new int[][]{{0,1},{0,2},{0,3},{2,4},{4,5}};
//        values = new int[]{5,2,5,2,1,1};

//        edges = new int[][]{{7,0},{3,1},{6,2},{4,3},{4,5},{4,6},{4,7}};
//        values = new int[]{2,16,23,17,22,21,8,6};
//        ans = 113
//        System.out.println(contest370.maximumScoreAfterOperations(edges, values));
        int[] nums = new int[]{3,3,5,6};
//        System.out.println(contest370.maxBalancedSubsequenceSum(nums));
    }

    // nums[ij] - nums[ij-1] >= ij - ij-1   ==>   nums[i] - i >= nums[j] - j
    //i，j   比i小且满足nums[i] - i >= nums[j] - j   这个条件可以通过diff 数组确定
    // 满足的里面的最大值
//    public long maxBalancedSubsequenceSum(int[] nums) {
//        int n = nums.length;
//        long[][] diff = new long[n][2];
//        for (int i = 0; i < n; i++) {
//            diff[i] = new int[] {nums[i] - i, i};
//        }
//        Arrays.sort(diff, (a, b) -> {
//            long ans = a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
//            if (ans == 0L) return 0;
//            else if (ans > 0L) return 1;
//            else return -1;
//        });
//
//        long max = nums[diff[0][1]];
//        for (int i = 0; i < n; i++) {
//
//        }
//        return max;
//    }


    int[] vals;
    long[][] cnt;
    Map<Integer, List<Integer>> tree;
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        tree = new HashMap();
        vals = values;
        int n = vals.length;
        cnt = new long[n][2];
        List<Integer> temporary = null;
        for (int i = 0; i < edges.length; i++) {
            temporary = tree.getOrDefault(edges[i][0], new LinkedList());
            temporary.add(edges[i][1]);
            tree.put(edges[i][0], temporary);

            temporary = tree.getOrDefault(edges[i][1], new LinkedList());
            temporary.add(edges[i][0]);
            tree.put(edges[i][1], temporary);
        }
        // 1 可以全空   0 不可以全空(这里被获取过了)
        return dfs(0, 0, -1);
    }

    //canAllZero = 1 可以全空   0 不可以全空
    private long dfs(int node, int canAllZero, int preNode) {
        if (cnt[node][canAllZero] != 0) {
            return cnt[node][canAllZero];
        }
        List<Integer> temporary = tree.get(node);
        if (temporary.size() == 1 && temporary.get(0) == preNode) {
            // 叶子节点时，如果可以全空就给值，否则就只能给0保证路径和不会为0
            return cnt[node][canAllZero] = (canAllZero == 1 ? vals[node] + 0L : 0L);
        }
        // 可以全空就  当前值不取， 或者本来就可以全空 那就加上vals[node]
        long nextAllZero = canAllZero == 1 ? vals[node] : 0L;
        // 不可全部为空，那么当前就取了
        long nextNotAllZero = vals[node];
        for (Integer nextNode : temporary) {
            if (nextNode == preNode) continue;
            nextAllZero += dfs(nextNode, 1, node);
            nextNotAllZero += dfs(nextNode, 0, node);
        }
        return cnt[node][canAllZero] = Math.max(nextAllZero, nextNotAllZero);
    }
}
