package com.pf.leetcode.twopoint;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * author：panf
 * date：12/2/2023
 * Description:
 */
public class CarPooling {
    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        int[][] trips;
        int capacity;
        trips = new int[][] {
                {9,3,4},{9,1,7},{4,2,4},{7,4,5}
        };
        System.out.println(carPooling.carPooling(trips, 23));
    }
    public boolean carPooling(int[][] trips, int capacity) {
        int left = 0;
        int right = 0;
        int nums = 0;
        Arrays.sort(trips, (a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < trips.length; i++) {
            while (!queue.isEmpty() && queue.peek()[2] <= trips[i][1]) {
                nums -= queue.poll()[0];
            }
            nums += trips[i][0];
            if (nums > capacity) return false;
            queue.offer(trips[i]);

        }
        return true;
    }
}
