package com.pf.leetcode.contest.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contest362 {
    public static void main(String[] args) {
        Contest362 contest362 = new Contest362();
        int[][] grid;
        grid = new int[][] {
                {1,0,4},
                {2,0,0},
                {2,0,0}
        };//6
        System.out.println(contest362.minimumMoves(grid));

        System.out.println(contest362.isReachableAtTime(1,1,1,1,3));
    }

    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        int dx = Math.abs(sx - fx);
        int dy = Math.abs(sy - fy);
        // 对角线可以最快到达，因为横纵坐标都-1， 剩余的就按x，y走
        int max = Math.max(dx, dy);
        // 一定可以在max的步骤的时候到达目标点，说明可以在max - 1 步的时候到达周边，这个时候可以消耗剩余步数，直到最后1s才进入
        // [0, max - 1]到周边  1 进入  所以t>=max 都可以
        // 当max=0的时候，说明已经在终点了，这个时候t > 0 需要走出去再回来 需要大于1步 t==0也可以
        if (max == 0) {
            return t != 1;
        }
        return t >= max;
    }

    public int minimumMoves(int[][] grid) {
        List<int[]> stones = new ArrayList();
        List<int[]> holes = new ArrayList();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                while (grid[i][j] > 1) {
                    grid[i][j]--;
                    stones.add(new int[]{i, j});
                }
                if (grid[i][j] == 0) {
                    holes.add(new int[]{i, j});
                }
            }
        }

        return minimumMoves(stones, holes, 0, 0, 0);
    }

    private int minimumMoves(List<int[]> stones, List<int[]> holes, int stoneind, int step, int state) {
        if (stoneind >= stones.size()) {
            return step;
        }
        int min = 100;
        int[] stone = stones.get(stoneind);
        for (int i = 0; i < holes.size(); i++) {
            int next = 1 << i;
            if ((state & next) == 0) {
                int[] hole = holes.get(i);
                int thisStep = Math.abs(stone[0] - hole[0]) +  Math.abs(stone[1] - hole[1]);
                min = Math.min(minimumMoves(stones, holes, stoneind + 1, step + thisStep, state | next), min);
            }
        }
        return min;
    }
}
