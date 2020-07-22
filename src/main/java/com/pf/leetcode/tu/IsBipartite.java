package com.pf.leetcode.tu;

import java.util.*;

public class IsBipartite {
    public static void main(String[] args) {
        IsBipartite isBipartite = new IsBipartite();
        int[][] graph = new int[][] {
                {1,2,3},
                {0,2},
                {0,1,3},
                {0,2}
        };
        System.out.println(isBipartite.isBipartite(graph));
    }
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        if(len == 0){
            return false;
        }
        TreeSet<Integer> visSet = new TreeSet<>((a, b)->  a - b);
        for(int i = 0; i < len; i++){
            visSet.add(i);
        }
        //前面放节点，后面放组别
        Map<Integer, Integer> splitMap = new HashMap();
        Queue<Integer> que = new LinkedList();
        int root = visSet.pollFirst();
        visSet.remove(root);
        splitMap.put(root, 1);
        que.offer(root);
        while(true) {
            if(que.isEmpty() && visSet.isEmpty()) {
                return true;
            } else if (que.isEmpty()) {
                int tmp = visSet.pollFirst();
                visSet.remove(tmp);
                splitMap.put(tmp, 1);
                que.offer(tmp);
            } else {
                while(!que.isEmpty()) {
                    int id = que.poll();
                    int next = (splitMap.get(id) + 1 )% 2;
                    for(int i = 0; i < graph[id].length; i++) {
                        int tmp = graph[id][i];
                        int tmpNext = splitMap.getOrDefault(tmp, -1);
                        if (tmpNext >= 0 && tmpNext != next) {
                            return false;
                        }
                        if(visSet.contains(tmp)) {
                            visSet.remove(tmp);
                            splitMap.put(tmp, next);
                            que.offer(tmp);
                        }
                    }
                }
            }
        }
    }
}
