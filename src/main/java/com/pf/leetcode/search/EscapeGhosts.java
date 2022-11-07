package com.pf.leetcode.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EscapeGhosts {

    private final long rowlen = 100000L;
    private final int min = -10000;
    private final int max = 10000;
    private final int[][] dis = new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static void main(String[] args) {
        EscapeGhosts escapeGhosts = new EscapeGhosts();
        int[][] gh = new int[][]{
                {1, 0},
//                {0,3}
        };
        int[] target = new int[]{2, 0};
        System.out.println(escapeGhosts.escapeGhosts(gh, target));
    }
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        Set<Long> set = new HashSet();
        LinkedList<int[]> que = new LinkedList();
        LinkedList<int[]> gque = new LinkedList();
        LinkedList<int[]> que1 = new LinkedList();
        LinkedList<int[]> gque1 = new LinkedList();
        for (int i = 0; i < ghosts.length; i++) {
            gque.add(ghosts[i]);
            set.add(ghosts[i][0] * rowlen + ghosts[i][1]);
        }
        que.add(new int[]{0, 0});
        int[] now = null;
        int[] tmp = null;

        while (!que.isEmpty()) {
            while (!gque.isEmpty()) {
                tmp = gque.poll();
                for (int i = 0; i < dis.length; i++) {
                    int nextr = tmp[0] + dis[i][0];
                    int nextc = tmp[1] + dis[i][1];
                    long next = nextr * rowlen + nextc;
                    if (!set.contains(next) && nextr >= min && nextr <= max && nextc >= min && nextc <= max) {
                        set.add(next);
                        gque1.add(new int[]{nextr, nextc});
                    }
                }
            }
            gque = gque1;
            gque1 = new LinkedList();

            while (!que.isEmpty()) {
                now = que.poll();
                for (int i = 0; i < dis.length; i++) {
                    int nextr = now[0] + dis[i][0];
                    int nextc = now[1] + dis[i][1];
                    long next = nextr * rowlen + nextc;
                    if (!set.contains(next) && nextr >= min && nextr <= max && nextc >= min && nextc <= max) {
                        if (nextr == target[0] && nextc == target[1]) {
                            return true;
                        }
                        set.add(next);
                        que1.add(new int[]{nextr, nextc});
                    }
                }
            }
            que = que1;
            que1 = new LinkedList();
        }
        return false;
    }
}
