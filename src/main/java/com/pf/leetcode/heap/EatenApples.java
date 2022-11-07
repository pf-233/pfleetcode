package com.pf.leetcode.heap;

import java.util.PriorityQueue;

public class EatenApples {

    public static void main(String[] args) {
        EatenApples eatenApples = new EatenApples();
        int[] apples = new int[] {1,2,3,5,2};
        int[] days = new int[] {3,2,1,4,2};
        System.out.println(eatenApples.eatenApples(apples, days));
    }
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        int[] tmp;
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        int res = 0;
        int t = 0;
        for (; t < n; t++) {
            if (apples[t] != 0) {
                tmp = new int[]{apples[t], t + days[t]};
                que.offer(tmp);
            }
            while (!que.isEmpty()) {
                tmp = que.poll();
                if (tmp[1] <= t) {
                    continue;
                }
                res++;
                if (--tmp[0] > 0) {
                    que.offer(tmp);
                }
                break;
            }
        }

        while (!que.isEmpty()) {
            tmp = que.poll();
            if (tmp[1] <= t) {
                continue;
            }
            int min = Math.min(tmp[0], tmp[1] - t);
            t += min;
            res += min;
        }
        return res;
    }
}
