package com.pf.leetcode.heap;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Merge {

    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            que.offer(intervals[i]);
        }
        LinkedList<int[]> list = new LinkedList();
        int[] pre = que.poll();
        int[] tmp = null;
        while (!que.isEmpty()) {
            tmp = que.poll();
            if (tmp[0] < pre[1]) {
                pre[1] = tmp[1];
            } else {
                list.add(pre);
                pre = tmp;
            }
        }
        list.add(pre);
        int len = list.size();
        int[][] res = new int[len][2];
        list.toArray(res);
        return res;
    }
}
