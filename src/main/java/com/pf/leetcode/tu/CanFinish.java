package com.pf.leetcode.tu;

import java.util.*;

public class CanFinish {
    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        int numCourses = 2;
        int[][] pre = new int[][]{
                {1,0}
        };

        System.out.println(canFinish.canFinish(numCourses, pre));
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inCount = new int[numCourses];
        List<Set<Integer>> edges = new ArrayList(numCourses);
        for(int i = 0; i < prerequisites.length; i++){
            inCount[prerequisites[i][1]]++;
            Set<Integer> tempSet = edges.get(prerequisites[i][0]);
            if(tempSet == null){
                tempSet = new HashSet();
                edges.set(prerequisites[i][0], tempSet);
            }
            tempSet.add(prerequisites[i][1]);
        }

        Queue<Integer> que = new LinkedList();
        int total = 0;
        for(int i = 0; i < numCourses; i++){
            if(inCount[i] == 0){
                que.offer(i);
                total++;
            }
        }

        while(!que.isEmpty()) {
            int temp = que.poll();
            Set<Integer> tempSet = edges.get(temp);
            for(Integer to : tempSet){
                if(--inCount[to] == 0){
                    que.offer(to);
                    total++;
                }
            }
        }
        return total == numCourses;
    }
}
