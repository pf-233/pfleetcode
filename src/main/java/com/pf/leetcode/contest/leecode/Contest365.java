package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：11413
 * date：10/1/2023
 * Description:
 */
public class Contest365 {

    public static void main(String[] args) {
        Contest365 contest365 = new Contest365();
//        int[] nums;
//        int target;
//        nums = new int[]{1,2,3};
//        target = 5;
//        System.out.println(contest365.minSizeSubarray(nums, target));
        List<Integer> edges;
        edges = Arrays.asList(1,2,0,0);
        System.out.println(contest365.countVisitedNodes(edges));
    }

    Map<Integer, Integer> map;
    int[] ans;
    int[] edgess;
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        ans = new int[n];
        edgess = new int[n];
        for (int i = 0; i < n; i++) {
            edgess[i] = edges.get(i);
        }
        for (int i = 0; i < n; i++) {
            map = new HashMap();
            if (ans[i] == 0) dfs(i, 0);
        }
        return ans;
    }

//     private int[] dfs(int ind) {
//         Map<Integer, Integer> map = new HashMap();
//         int top = 0;
//         int tempind = ind;
//         while (ans[tempind] == 0 && map.get(tempind) == null) {
//             map.put(tempind, top++);
//             tempind = edgess[tempind];
//         }

//     }



    // 循环的长度， 循环的开始深度
    private int[] dfs(int ind, int deep) {
        if (ans[ind] != 0) {
            return new int[]{ans[ind], deep};
        }
        // 开始循环了
        if (map.get(ind) != null) {
            ans[ind] = deep - map.get(ind);
            return new int[] {ans[ind], map.get(ind)};
        }
        int next = edgess[ind];
        map.put(ind, deep);
        int[] res = dfs(next, deep + 1);
        // 循环开始的深度 <= 当前深度 说明在环里就直接设置为环长
        if (map.get(res[1]) <= deep) {
            ans[ind] = res[0];
            return res;
        } else {
            // 不在环里
            ans[ind] = res[0] + 1;
            return new int[]{ans[ind], deep};
        }
    }

    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        long[] prefix = new long[n];
        prefix[0] = 0L + nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        long len = 1L * target / prefix[n - 1] * n;
        target %= prefix[n - 1];
        if (target == 0) {
            return (int) len;
        }

        int left = 0;
        int right = 0;
        long sum = 0L;
        int minLen = n * 10;
        while (left < n) {
            if (sum == target) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left++];
            } else if (sum > target) {
                sum -= nums[left++];
            } else {
                sum += nums[right++ % n];
            }
        }
        return minLen <= n ? (int) (minLen + len) : -1;
    }
}
