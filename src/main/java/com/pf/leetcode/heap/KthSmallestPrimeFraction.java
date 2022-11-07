package com.pf.leetcode.heap;

import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {
    public static void main(String[] args) {
        KthSmallestPrimeFraction kthSmallestPrimeFraction = new KthSmallestPrimeFraction();
        kthSmallestPrimeFraction.kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3);
    }
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int[] res = null;
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]);
        for (int i = 0; i < arr.length - 1; i++) {
            que.offer(new int[]{i, arr.length - 1});
        }

        while (k-- > 0) {
            res = que.poll();
            // 如果可以组成下一个就继续放入
            if (k > 0 && res[0] < --res[1]) {
                que.offer(res);
            }
        }
        return new int[]{arr[res[0]], arr[res[1]]};
    }
}
