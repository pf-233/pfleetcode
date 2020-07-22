package com.pf.leetcode.tu;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GardenNoAdj {
    public static void main(String[] args) {
        GardenNoAdj gardenNoAdj = new GardenNoAdj();
        int N = 3;
        int[][] paths = new int[][]{
                {1,2},
                {2,3},
                {3,1}
        };
        int[] res = gardenNoAdj.gardenNoAdj(N, paths);
        System.out.println(res.toString());
    }
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] answer = new int[N];
        Set<Integer>[] sets = new Set[N + 1];
       for (int i = 0; i < N + 1; i++){
           sets[i] = new HashSet<>();
       }
        for(int i = 0; i < paths.length; i++){
            sets[paths[i][0]].add(paths[i][1]);
            sets[paths[i][1]].add(paths[i][0]);
        }
        for(int i = 0; i < N; i++){
            if(answer[i] == 0){
                bfs(sets, answer, i + 1);
            }
        }
        return answer;
    }

    void bfs(Set<Integer>[] sets, int[] answer, int val){
        Queue<Integer> que = new LinkedList();
        que.offer(val);
        while(!que.isEmpty()){
            int now = que.poll();
            int[] flowers = new int[4];
            Set<Integer> set = sets[now];
            for(Integer ind : set){
                if(answer[ind - 1] == 0){
                    que.offer(ind);
                } else {
                    flowers[answer[ind - 1] - 1] = 1;
                }
            }
            int i = 0;
            while(flowers[i] == 1){
                i++;
            }
            answer[now - 1] = i + 1;
        }
    }
}
