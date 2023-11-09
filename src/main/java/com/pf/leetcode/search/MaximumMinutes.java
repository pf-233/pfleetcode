package com.pf.leetcode.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * author：panf
 * date：11/9/2023
 * Description:
 */
public class MaximumMinutes {

    int max = (int)1e9;
    int[][] direction = new int[][] {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public static void main(String[] args) {
        MaximumMinutes maximumMinutes = new MaximumMinutes();
        int[][] grid = new int[][] {
                {0,0,0,0,0,0},
                {0,2,2,2,2,0},
                {0,0,0,1,2,0},
                {0,2,2,2,2,0},
                {0,0,0,0,0,0}
        };
        System.out.println(maximumMinutes.maximumMinutes(grid));
    }
    public int maximumMinutes(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] fireTime = new int[r][c];
        for (int i = 0; i < r; i++) Arrays.fill(fireTime[i], -1);
        Queue<int[]> que = new LinkedList();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    que.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                }
            }
        }
        while (!que.isEmpty()) {
            int[] now = que.poll();
            for (int i = 0; i < direction.length; i++) {
                int nextr = now[0] + direction[i][0];
                int nextc = now[1] + direction[i][1];
                if (inbound(nextr, nextc, r, c) && grid[nextr][nextc] < 2 && fireTime[nextr][nextc] == -1) {
                    fireTime[nextr][nextc] = fireTime[now[0]][now[1]] + 1;
                    que.offer(new int[]{nextr, nextc});
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(fireTime[i][j] +",");
            }
            System.out.println();
        }
        // r,c  step,  canDelay
        que.offer(new int[]{0, 0, 0, max});
        int[][] visit = new int[r][c];
        visit[0][0] = 1;
        int maxDelay = -1;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int step = now[2] + 1;
            int delay = now[3];
            for (int i = 0; i < direction.length; i++) {
                int nextr = now[0] + direction[i][0];
                int nextc = now[1] + direction[i][1];
                if (inbound(nextr, nextc, r, c) && grid[nextr][nextc] != 2 && visit[nextr][nextc] == 0) {
                    // 火烧到的时间-到达当前的步数之差 canDelay
                    int canDelay = fireTime[nextr][nextc] == -1 ? max : fireTime[nextr][nextc] - step;
                    // 如果是最后了就可以canDelay=0 先进去再烧
                    if (nextr == r - 1 && nextc == c - 1 && canDelay >= 0) {
                        maxDelay = Math.max(maxDelay, Math.min(delay, canDelay));
                    } else if (canDelay > 0){
                        visit[nextr][nextc] = 1;
                        // 不是最后 当max == canDelay 说明不可能烧到 max
                        // 可以烧到的话要canDelay - 1 因为如果等到canDelay 步的话就会在这里被烧到
                        // 取当前路径上能走的最小值
                        que.offer(new int[]{nextr, nextc, step, Math.min(max == canDelay ? max : canDelay - 1, delay)});
                    }
                }
            }
        }
        return maxDelay;
    }

    private boolean inbound(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
