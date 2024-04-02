package com.pf.leetcode.dp;

import com.pf.leetcode.array.Rain;
import com.pf.leetcode.utils.InputOutputUtil;

/**
 * author：panf
 * date：3/5/2024
 * Description:
 */
public class CountPaths {
    public static void main(String[] args) {
        CountPaths countPaths = new CountPaths();
        int n;
        int[][] roads;
        n = 7;
        String arr = "[[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]";
        roads = InputOutputUtil.get2DimensionArray(arr);
        System.out.println(countPaths.countPaths(n, roads));
    }
    int mode = (int) 1e9 + 7;
    public int countPaths(int n, int[][] roads) {
        long[][] distance = new long[n][n];
        for (int i = 0; i < roads.length; i++) {
            distance[roads[i][0]][roads[i][1]] = roads[i][2];
            distance[roads[i][1]][roads[i][0]] = roads[i][2];
        }
        // 1 是方案数，  0是路径长度
        long[][] disPoint = new long[n][2];
        boolean[] isShortest = new boolean[n];
        disPoint[0] = new long[] {0L , 1L};
        for (int i = 1; i < n; i++) {
            long path = distance[i][0];
            disPoint[i] = new long[] {path, path > 0 ? 1L : 0L};
        }

        isShortest[0] = true;
        // 次数里面有几个点
        for (int i = 1; i < n && !isShortest[n - 1]; i++) {
            long minPath = -1L;
            int ind = -1;
            for (int j = 1; j < n; j++) {
                if (!isShortest[j] && disPoint[j][0] > 0) {
                    if (ind == -1L) {
                        minPath = disPoint[j][0];
                        ind = j;
                    } else if (disPoint[j][0] < minPath){
                        minPath = disPoint[j][0];
                        ind = j;
                    }
                }
            }
            isShortest[ind] = true;
            for (int j = 1; j < n; j++) {
                if (!isShortest[j] && distance[ind][j] > 0) {
                    long path = minPath + distance[ind][j];
                    if (path < disPoint[j][0] || disPoint[j][0] == 0) {
                        disPoint[j][0] = path;
                        disPoint[j][1] = disPoint[ind][1];
                    } else if (path == disPoint[j][0]) {
                        disPoint[j][1] += disPoint[ind][1];
                    }
                    disPoint[j][1] %= mode;
                }
            }
        }
        return (int) disPoint[n - 1][1];
    }
}
