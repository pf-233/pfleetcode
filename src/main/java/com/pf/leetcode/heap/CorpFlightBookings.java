package com.pf.leetcode.heap;

import java.util.PriorityQueue;

public class CorpFlightBookings {

    public static void main(String[] args) {
        CorpFlightBookings corpFlightBookings = new CorpFlightBookings();
        int[][] books = new int[][] {
                {1,2,10},
                {2,3,20},
                {2,5,25}
        };
        System.out.println(corpFlightBookings.corpFlightBookings(books, 5));
    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for(int i = 0; i < bookings.length; i++) {
            que.offer(bookings[i]);
        }
        int[] tmp = null;
        int[] pre = que.poll();
        while (!que.isEmpty()) {
            tmp = que.poll();
            int f1 = pre[0];
            int l1 = pre[1];
            int f2 = tmp[0];
            int l2 = tmp[1];

            if (l1 < f2) {
                setNum(res,f1 - 1, l1 - 1, pre[2]);
                pre = tmp;
            } else if (l1 >= f2) {
                setNum(res, f1 - 1, f2 - 2, pre[2]);
                setNum(res, f2 - 1, Math.min(l1, l2) - 1, pre[2] + tmp[2]);
                tmp[0] = Math.min(l1, l2) + 1;
                tmp[1] = Math.max(l1, l2);
                if (l1 > l2) {
                    tmp[2] = pre[2];
                }
                que.offer(tmp);
                pre = que.poll();
            }
        }
        setNum(res, pre[0] - 1, pre[1] - 1, pre[2]);
        return res;
    }

    private void setNum(int[] res, int start, int end, int num) {
        for (int i = start; i <= end; i++) {
            res[i] += num;
        }
    }
}
