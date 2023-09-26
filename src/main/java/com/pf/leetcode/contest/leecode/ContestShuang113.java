package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ContestShuang113 {
    public static void main(String[] args) {
        ContestShuang113 contestShuang113 = new ContestShuang113();
//        List<Integer> nums = Arrays.asList(3,4,5,1,2);
//        System.out.println(contestShuang113.minimumRightShifts(nums));

        List<Integer> nums = Arrays.asList(1,1,2,2);
        System.out.println(contestShuang113.minLengthAfterRemovals(nums));
    }

    Map<Integer, List<Integer>> dir;
    Map<Integer, List<Integer>> reversedir;
//    public int[] minEdgeReversals(int n, int[][] edges) {
//        dir = new HashMap();
//        reversedir = new HashMap();
//        List<Integer> temp;
//        for (int[] edge : edges) {
//            temp = dir.getOrDefault(edge[0], new LinkedList());
//            temp.add(edge[1]);
//            dir.put(edge[0], temp);
//            temp = reversedir.getOrDefault(edge[1], new LinkedList());
//            temp.add(edge[0]);
//            reversedir.put(edge[1], temp);
//        }
//
//        int[] ans = new int[n];
//        build(ans, 0, -1);
//        Queue<Integer> que = new LinkedList();
//        que.addAll(dir.getOrDefault(0, new LinkedList()));
//    }

    private int build(int[] ans, int now, int pre) {
        int count = 0;
        List<Integer> temp = dir.getOrDefault(now, new LinkedList());
        for (Integer next : temp) {
            count += build(ans, next, now);
        }
        temp = reversedir.getOrDefault(now, new LinkedList());
        for (Integer next : temp) {
            if (pre != next) count += build(ans, next, now) + 1;
        }
        return ans[now] = count;
    }

    public int countPairs(List<List<Integer>> coordinates, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        Map<Integer, Integer> temp;
        int ans = 0;
        for (List<Integer> coordinate : coordinates) {
            int f0 = coordinate.get(0);
            int f1 = coordinate.get(1);
            for (int i = 0; i <= k; i++) {
                int a = f0 ^ i;
                int b = f1 ^ (k - i);
                ans += map.getOrDefault(a, new HashMap<Integer, Integer>()).getOrDefault(b, 0);
            }
            temp = map.getOrDefault(f0, new HashMap());
            temp.put(f1, temp.getOrDefault(f1, 0) + 1);
            map.put(f0, temp);
        }
        return ans;
    }

    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> map = new HashMap();
        int maxCnt = 0;
        for (Integer temp : nums) {
            int now = map.getOrDefault(temp, 0);
            maxCnt = Math.max(maxCnt, ++now);
            map.put(temp, now);
        }
        return maxCnt <= n / 2 ? n % 2 : 2 * maxCnt - n;
    }

    public int minimumRightShifts(List<Integer> nums) {
        int min = 1000;
        int ind = -1;
        for (int i = 0; i < nums.size(); i++) {
            if (min > nums.get(i)) {
                ind = i;
                min = nums.get(i);
            }
        }
        int ans = nums.size() - ind;
        int top = 1;
        int pre = min;
        while (top < nums.size() && nums.get((top + ind) % nums.size()) > pre) {
            pre = nums.get((top + ind) % nums.size());
            top++;
        }
        return top == nums.size() ? ans : -1;
    }
}
