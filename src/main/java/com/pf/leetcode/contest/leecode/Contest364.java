package com.pf.leetcode.contest.leecode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author panf
 * @date 9/24/2023 10:51 AM
 */
public class Contest364 {

    public static void main(String[] args) {
        Contest364 contest364 = new Contest364();
//        System.out.println(contest364.maximumSumOfHeights(Arrays.asList(6,5,3,9,2,7)));
        System.out.println(contest364.maximumSumOfHeights2(Arrays.asList(5,3,4,1,1)));
    }
//
//    public long countPaths(int n, int[][] edges) {
//        boolean[] prime = isPrime(n);
//        List<Integer>[] adjacent = new LinkedList[n];
//        Arrays.setAll(adjacent, e -> new LinkedList<Integer>());
//
//        for (int[] edge : edges) {
//            int x = edge[0];
//            int y = edge[1];
//            adjacent[x].add(y);
//            adjacent[y].add(x);
//        }
//
//        long ans = 0L;
//        Queue<int[]> que = new LinkedList();
//        // 当前值， 开始素数， 节点数
//        int[][] memo = new int[n][3];
//        for (int i = 2; i <= n; i++) {
//            if (!prime[i]) {
//                memo[i] = new int[]{i, i, 0};
//                que.offer(memo[i]);
//            }
//        }
//        List<int[]>[] adjacentPrime = new LinkedList[n];
//        Arrays.setAll(adjacentPrime, e -> new LinkedList<int[]>());
//        while (!que.isEmpty()) {
//            int[] now = que.poll();
//            int ind = now[0];
//            int parentPrime = now[1];
//            int deep = now[2];
//            for (Integer next : adjacent[ind]) {
//                if (memo[next] != null) {
//                    // 已经遇见质数进行合并
////                    int[] merge = new int[]{parentPrime, memo[next][1], deep + memo[next][2]};
//                    adjacentPrime[parentPrime].add();
//                }
//            }
//        }
//    }

    private boolean[] isPrime(int n) {
        // false 是素数
        boolean[] isPrime = new boolean[n + 1];
        isPrime[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= n / j; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return isPrime;
    }

    /**
     * O(n)
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] prefix = new long[n + 2];
        long[] suffix = new long[n + 2];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < n; i++) {
            int now = maxHeights.get(i);
            while (!stack.isEmpty() && now < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            int ind = stack.isEmpty() ? -1 : stack.peek();
            prefix[i + 1] = 1L * now  * (i - ind) + prefix[ind + 1];
            stack.push(i);
        }

        stack = new Stack();
        for (int i = n - 1; i >= 0; i--) {
            int now = maxHeights.get(i);
            while (!stack.isEmpty() && now < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            int ind = stack.isEmpty() ? n : stack.peek();
            suffix[i + 1] = 1L * now  * (ind - i) + suffix[ind + 1];
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, prefix[i + 1] + suffix[i + 1] - maxHeights.get(i));
        }
        return ans;
    }

    /**
     * O(n^2)
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long ans = 0;
        for (int i = 0; i < maxHeights.size(); i++) {
            long temp = 0L + maxHeights.get(i);
            int j = i - 1;
            int pre = maxHeights.get(i);
            while (j >= 0) {
                temp += Math.min(pre, maxHeights.get(j));
                pre = maxHeights.get(j--);
            }
            j = i + 1;
            pre = maxHeights.get(i);
            while (j < maxHeights.size()) {
                temp += Math.min(pre, maxHeights.get(j));
                pre = maxHeights.get(j++);
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }
}
