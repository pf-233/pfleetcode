package com.pf.leetcode.tu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumTime {
    public static void main(String[] args) {
        int n;
        int[][] relations;
        int[] time;
        n = 5;
        relations = new int[][] {{1,5},{2,5},{3,5},{3,4},{4,5}};
        time = new int[] {1,2,3,4,5};
        MinimumTime minimumTime = new MinimumTime();
        System.out.println(minimumTime.minimumTime(n, relations, time));
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] preCnt = new int[n + 1];
        LinkedList<Integer>[] rela = new LinkedList[n + 1];
        Arrays.setAll(rela, e -> new LinkedList());
        for (int i = 0; i < relations.length; i++) {
            int pi = relations[i][0];
            int ni = relations[i][1];
            preCnt[ni]++;
            rela[pi].add(ni);
        }
        // [course, completeTime]
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        for (int i = 1; i < preCnt.length; i++) {
            if (preCnt[i] == 0)
                que.offer(new int[]{i, time[i - 1]});
        }

        int min = 0;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            min = Math.max(min, now[1]);
            for (int next : rela[now[0]]) {
                if (--preCnt[next] == 0) {
                    que.offer(new int[]{next, now[1] + time[next - 1]});
                }
            }
        }
        return min;
    }
}
