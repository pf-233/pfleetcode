package com.pf.leetcode.heap;

import java.util.PriorityQueue;

public class KWeakestRows {

    public int[] kWeakestRows(int[][] mat, int k) {
        int len = mat.length;
        int[] res = new int[k];
        int[] tmp = new int[len];

        PriorityQueue<Integer> que = new PriorityQueue<Integer>((a, b) -> tmp[a] == tmp[b] ? a - b : tmp[a] - tmp[b]);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mat[i].length && mat[i][j] == 1; j++) {
                tmp[i]++;
            }
            que.offer(i);
        }
        for (int i = 0; i < k; i++) {
            res[i] = que.poll();
        }
        return res;
    }
}
