package com.pf.leetcode.heap;

import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {
    public static void main(String[] args) {
        System.out.println();
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Point> minQue = new PriorityQueue<Point>((a,b)-> a.list.get(a.index) - b.list.get(b.index));
        PriorityQueue<Point> maxQue = new PriorityQueue<Point>((a,b)-> b.list.get(b.index) - a.list.get(a.index));
        for(int i = 0; i < nums.size(); i++) {
            if (nums.get(i).size() == 0) {
                return null;
            }
            Point tmp = new Point(nums.get(i));
            minQue.offer(tmp);
            maxQue.offer(tmp);
        }
        int areaLeft1 = -1000000;
        int areaRight1 = 10000000;
        int[] res;
        while(true) {
            Point minPoint = minQue.poll();
            Point maxPoint = maxQue.peek();
            res = getArea(areaLeft1, areaRight1, minPoint.list.get(minPoint.index), maxPoint.list.get(maxPoint.index));
            if(minPoint.index == minPoint.list.size() - 1) {
                break;
            } else {
                maxQue.remove(minPoint);
                minPoint.index++;
                minQue.offer(minPoint);
                maxQue.offer(minPoint);
            }
        }
        return res;
    }

    private int[] getArea(int areaLeft1, int areaRight1, int areaLeft2, int areaRight2) {
        int len1 = areaRight1 - areaLeft1;
        int len2 = areaRight2 - areaLeft2;
        if (len1 < len2 || (len1 == len2 && areaLeft1 < areaLeft2)) {
            return new int[]{areaLeft1, areaRight1};
        }
        return new int[]{areaLeft2, areaRight2};
    }

    static class Point {
        private int index;
        private List<Integer> list;

        Point(List list) {
            this.list = list;
            index = 0;
        }
    }
}
