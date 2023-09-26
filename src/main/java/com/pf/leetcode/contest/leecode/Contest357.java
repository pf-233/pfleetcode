package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.stream.Collectors;

public class Contest357 {
    public static void main(String[] args) {
        Contest357 contest357 = new Contest357();
//        List<Integer> nums;
//        int m;
//        nums = Arrays.asList(2,1,3);
//        m = 5;
//        System.out.println(contest357.canSplitArray(nums, m));
        List<List<Integer>> lists = new LinkedList<>();
        String str = "[[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]";
        str = str.substring(2, str.length() - 2);
        for (String temp : str.split("],\\[")){
            System.out.println(temp);
            String[] arr = temp.split(",");
            lists.add(Arrays.stream(arr).map(e -> new Integer(e)).collect(Collectors.toList()));
        }

        System.out.println(contest357.maximumSafenessFactor(lists));
    }
    int[][] distance;
    int[][] direction = new int[][] {
            {1,0},{0,1},{0,-1},{-1,0}
    };
    int[][] memo;
    int n;
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        distance = new int[n][n];
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], 170000);
            Arrays.fill(memo[i], -1);
        }
        int low = 0;
        int hight = bfs(grid);
        int mid = 0;
        if (distance[0][0] == 0 || distance[n - 1][n - 1] == 0) {
            return 0;
        }

        int ans = 0;
        while (low <= hight) {
            mid = (low + hight) / 2;
            System.out.println(mid);
            if (maximumSafenessFactor(mid)) {
                low = mid + 1;
                ans = mid;
            } else {
                hight = mid - 1;
            }
        }
        return ans;
    }

    private boolean maximumSafenessFactor(int diff) {
        Set<Integer> set = new HashSet();
        LinkedList<int[]> list = new LinkedList();
        boolean ans = false;
        if (distance[0][0] < diff) {
            return false;
        }
        list.add(new int[]{0,0});
        while (list.size() > 0) {
            int[] now = list.removeFirst();
            for (int i = 0; i < direction.length; i++) {
                int nr = now[0] + direction[i][0];
                int nc = now[1] + direction[i][1];
                int ind = nr * n + nc;
                if (canMove(nr, nc) && distance[nr][nc] >= diff && !set.contains(ind)) {
                    set.add(ind);
                    list.add(new int[]{nr, nc});
                    if (nr == n - 1 && nc == n - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int bfs(List<List<Integer>> grid) {
        LinkedList<int[]> list = new LinkedList();
        LinkedList<int[]> list2 = new LinkedList();
        int dis = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    list.add(new int[]{i, j});
                    distance[i][j] = dis;
                }
            }
        }

        while (true) {
            dis++;
            while (list.size() > 0) {
                int[] temp = list.removeFirst();
                for (int i = 0; i < direction.length; i++) {
                    int nr = temp[0] + direction[i][0];
                    int nc = temp[1] + direction[i][1];
                    if (canMove(nr, nc) && distance[nr][nc] > dis) {
                        list2.add(new int[]{nr, nc});
                        distance[nr][nc] = dis;
                    }
                }
            }
            if (list2.size() > 0) {
                list = list2;
                list2 = new LinkedList();
            } else {
                break;
            }
        }
        return dis;
    }

    private boolean canMove(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        return true;
    }


    int[][] dp;
    int[] pre;
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        dp = new int[n][n];
        pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            pre[i + 1] = pre[i] + nums.get(i);
        }
        return dfs(0, n-1, m);
    }

    private boolean dfs(int start, int end, int m) {
        if (dp[start][end] != 0) {
            return dp[start][end] == 1;
        }
        // if (pre[end + 1] - pre[start] < m) {
        //     dp[start][end] = -1;
        //     return false;
        // }
        boolean ans = false;
        for (int x = start; x < end && !ans; x++) {
            //[start, x] [x + 1, end]
            if ((start == x || pre[x + 1] - pre[start] >= m)
                    && (x+1 == end || pre[end + 1] - pre[x + 1] >= m))
                ans = dfs(start, x, m) && dfs(x+1, end, m);
        }
        dp[start][end] = ans ? 1 : -1;
        return ans;
    }

}
