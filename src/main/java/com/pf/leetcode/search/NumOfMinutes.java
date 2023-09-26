package com.pf.leetcode.search;

import java.util.Arrays;
import java.util.LinkedList;

public class NumOfMinutes {
    
    public static void main(String[] args) {
        NumOfMinutes minutes = new NumOfMinutes();
        int n = 15;
        int headID = 0;
        int[] manager =  new int[] {-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6};
        int[] informTime = new int[]{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
        minutes.numOfMinutes(n, headID, manager, informTime);
    }
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        LinkedList<Integer>[] subList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            subList[i] = new LinkedList();
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            subList[manager[i]].add(i);
        }

        int max = 0;
        LinkedList<Integer> que = new LinkedList();
        que.add(headID);
        int nowNeed = informTime[headID];
        while (!que.isEmpty()) {
            int top = que.peek();
            LinkedList<Integer> sub = subList[top];
            if (sub.size() == 0) {
                que.pop();
                nowNeed -= informTime[top];
                continue;
            } else {
                int temp = sub.removeFirst();
                nowNeed += informTime[temp];
                max = Math.max(max, nowNeed);
                que.add(temp);
            }
        }
        return max;
    }
}
