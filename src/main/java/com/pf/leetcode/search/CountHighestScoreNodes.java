package com.pf.leetcode.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CountHighestScoreNodes {
    public static void main(String[] args) {
        System.out.println(new CountHighestScoreNodes().countHighestScoreNodes(new int[]{-1,3,3,5,7,6,0,0}));
    }

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        int[] visParent = new int[n];
        List<Integer>[] list = new ArrayList[n];
        for (int i = 1; i < n; i++) {
            if (list[parents[i]] == null) {
                list[parents[i]] = new ArrayList(2);
            }
            list[parents[i]].add(i);
            visParent[parents[i]]++;
        }

        int[] son = new int[n];
        Arrays.fill(son, 1);
        LinkedList<Integer> que = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (visParent[i] == 0) {
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int tmp = que.removeFirst();
            son[parents[tmp]] += son[tmp];
            if (--visParent[parents[tmp]] == 0) {
                que.add(parents[tmp]);
            }
        }

        int max = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> child = list[i];
            if (child == null) {
                if (max < n - 1) {
                    max = n - 1;
                    maxCount = 1;
                } else if (max == n - 1) {
                    maxCount++;
                }
                continue;
            }
            int mul = 1;
            int next = n - 1;
            for (int aa : child) {
                mul *= son[aa];
                next -= son[aa];
            }
            if (next > 0) {
                mul *= next;
            }

            if (max < mul) {
                max = mul;
                maxCount = 1;
            } else if (max == mul) {
                maxCount++;
            }
        }

        return maxCount;
    }
}
