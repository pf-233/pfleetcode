package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author panf
 * @date 9/17/2023 10:35 AM
 */
public class Contest363 {

    public static void main(String[] args) {
        Contest363 contest363 = new Contest363();
        List<Integer> nums;
//        nums = Arrays.asList(6,0,3,3,6,7,2,7);
//        nums = Arrays.asList(1,1);
//        System.out.println(contest363.countWays(nums));
        int n;
        int k;
        int budget;
        List<List<Integer>> composition;
        List<Integer> stock;
        List<Integer> cost;
        n = 2;
        k = 3;
        budget = 10;
//        [[2,1],[1,2],[1,1]]
//[1,1]
//[5,5]
        composition = Arrays.asList(Arrays.asList(2,1), Arrays.asList(1,2), Arrays.asList(1,1));
        stock = Arrays.asList(1,1);
        cost = Arrays.asList(5,5);
        System.out.println(contest363.maxNumberOfAlloys(n, k, budget, composition, stock, cost));
    }
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int max = 0;
        int i = 0;
        for (List<Integer> comp : composition) {
            System.out.println("===============" + i++);
            max = Math.max(max, maxNumberOfAlloys(n, budget, comp, stock, cost));
        }
        return max;
    }

    private int maxNumberOfAlloys(int n, int budget, List<Integer> composition, List<Integer> stock, List<Integer> cost) {
        int min = (int)1e9;
        for (int i = 0; i < n; i++) {
            min = Math.min(stock.get(i) / composition.get(i), min);
        }
        return binary(min, Integer.MAX_VALUE, budget, composition, stock, cost);
    }

    private int binary(int l, int r, int budget, List<Integer> composition, List<Integer> stock, List<Integer> cost) {
        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (canget(budget, composition, stock, cost, mid)) {
                System.out.println(ans);
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    private boolean canget(int budget, List<Integer> composition, List<Integer> stock, List<Integer> cost, int count) {
        long b = budget;
        for (int i = 0; i < composition.size() && budget >= 0; i++) {
            long need = 1L * composition.get(i) * count - stock.get(i);
            b -= Math.max(0, need) * cost.get(i);
        }
        return b >= 0;
    }

    public int countWays1(List<Integer> nums) {
        int ans = 0;
        int selectCnt = 0;
        int n = nums.size();
        Collections.sort(nums);
        for (int i = 0; i < n; i++) {
            while (i + 1 < n && nums.get(i).equals(nums.get(i + 1))) {
                i++;
            }
            int next = i + 1 < n ? nums.get(i + 1) : n + 100;
            // i + 1 个人
            ans += nums.get(i) <= i && next > i + 1 ? 1 : 0;
        }
        return ans;
    }

    public int countWays(List<Integer> nums) {
        int ans = 0;
        int selectCnt = 0;
        int n = nums.size();
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        for (Integer num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i <= n; i++) {
            int val = treeMap.getOrDefault(i, 0);
            if (val + selectCnt < i) {
                ans++;
            }
            selectCnt += val;
        }
        return ans;
    }
}
