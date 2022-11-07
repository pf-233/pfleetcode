package com.pf.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianFinder {
    private Double mid;
    /** 小顶堆
     */
    private PriorityQueue<Integer> minQ;
    /** 大顶堆
     */
    private PriorityQueue<Integer> maxQ;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxQ = new PriorityQueue<Integer>((a, b) -> b - a);
        minQ = new PriorityQueue<Integer>((a, b) -> a - b);
        mid = 0d;
    }

    public static void main(String[] args) {
        /**
         *
         ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
         [[],[1],[],[2],[],[3],[],[4],[],[5],[],[6],[],[7],[],[8],[],[9],[],[10],[]]
         */
        MedianFinder finder = new MedianFinder();
        for (int i = 1; i < 10; i ++) {
            finder.addNum(i);
            System.out.println(finder.findMedian());
        }

    }
    public void addNum(int num) {
        int minl = minQ.size();
        int maxl = maxQ.size();
        if (minl + maxl == 0) {
            mid = num * 1.0;
            minQ.offer(num);
        } else if (minl + maxl == 1) {
            int tmp = minl == 0 ? maxQ.poll() : minQ.poll();
            int[] tmps = new int[]{tmp, num};
            Arrays.sort(tmps);
            minQ.offer(tmps[1]);
            maxQ.offer(tmps[0]);
            mid = (tmps[0] + tmps[1]) * 1.0 / 2;
        } else {
            int[] arr = new int[]{minQ.poll(), maxQ.poll(), num};
            Arrays.sort(arr);
            minQ.offer(arr[2]);
            maxQ.offer(arr[0]);
            if (minl == maxl) {
                minQ.offer(arr[1]);
                mid = arr[1] * 1.0;
            } else {
                maxQ.offer(arr[1]);
                mid = (maxQ.peek() + minQ.peek()) * 1.0 / 2;
            }
        }
    }

    public double findMedian() {
        return mid;
    }
}
