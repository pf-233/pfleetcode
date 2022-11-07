package com.pf.leetcode.search;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindCheapestPrice {

    int max = 10000000;

    public static void main(String[] args) {
        FindCheapestPrice findCheapestPrice = new FindCheapestPrice();
        int[][] edges = new int[][] {
                {0,1,100},{1,2,100},{0,2,500}
        };
//        System.out.println(findCheapestPrice.findCheapestPrice(3, edges, 0, 2,1));

        edges = new int[][] {
                {0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}
        };
        System.out.println(findCheapestPrice.findCheapestPrice(5, edges, 0, 2, 2));
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], max);
        }
        for (int i = 0; i < flights.length; i++) {
            dis[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        // 当前点, 价格, 次数
        que.offer(new int[]{src, 0, 0});

        int res = max;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int start = now[0];
            int price = now[1];
            int step = now[2];
            if (price >= res) {
                break;
            }
            if (step > k) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                int tmp = dis[start][i] + price;
                if (tmp < max) {
                    que.add(new int[]{i, tmp, step + 1});
                    if (i == dst) {
                        res = Math.min(res, tmp);
                    }
                }
            }
        }
        return res == max ? -1 : res;
    }
}
