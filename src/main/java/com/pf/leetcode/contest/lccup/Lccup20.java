package com.pf.leetcode.contest.lccup;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.security.auth.Subject;
import java.awt.*;
import java.time.Year;
import java.util.*;
import java.util.List;

public class Lccup20 {

    public static void main(String[] args) {
        Lccup20 lccup20 = new Lccup20();
        int[][] preferences = new int[][]{
                {1,2,3},
                {3,2,0},
                {3,1,0},
                {1,2,0}
        };
        int[][] pairs = new int[][]{
                {0, 1},
                {2, 3}
        };
        System.out.println(lccup20.unhappyFriends(4, preferences, pairs));
    }

    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        int[][] dis = new int[len][len];
        int[] parent = new int[len];
        PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->(a.val - b.val));
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            for (int j = 0; j < i; j++) {
                dis[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                dis[j][i] = dis[i][j];
                queue.offer(new Node(i,j,dis[i][j]));
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (!union(parent, now.x, now.y)) {
                count += now.val;
            }
        }
        return count;
    }

    static class Node {
        private int x;
        private int y;
        private int val;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int find(int[] parent, int x) {
        int tmp = x;
        while (parent[tmp] != tmp) {
            tmp = parent[tmp];
        }
        return tmp;
    }

    private boolean union(int[] parent, int x, int y) {
        int parx = find(parent, x);
        int pary = find(parent, y);
        if (parx == pary) {
            return true;
        }

        parent[pary] = parx;
        return false;
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int len = pairs.length;
        int count = 0;
        int[][] ind = new int[preferences.length][preferences[0].length + 1];
        for (int i = 0; i < preferences.length; i++) {
            for (int j = 0; j < preferences[i].length; j++) {
                ind[i][preferences[i][j]] = j;
            }
        }
        int[] unhap = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                count += unhappy(preferences, pairs[i][0], pairs[i][1], pairs[j][0], pairs[j][1], ind, unhap);
                count += unhappy(preferences, pairs[i][0], pairs[i][1], pairs[j][1], pairs[j][0], ind, unhap);
                count += unhappy(preferences, pairs[i][1], pairs[i][0], pairs[j][0], pairs[j][1], ind, unhap);
                count += unhappy(preferences, pairs[i][1], pairs[i][0], pairs[j][1], pairs[j][0], ind, unhap);
            }
        }
        return count;
    }

    private int unhappy(int[][] pre, int x, int y, int u, int v, int[][] ind, int[] unhap) {
        if (unhap[x] == 1) {
            return 0;
        }
        int indxy = ind[x][y];
        int indxu = ind[x][u];
        if (indxu < indxy) {
            int indux = ind[u][x];
            int induv = ind[u][v];
            if (indux < induv) {
                unhap[x] = 1;
                return 1;
            }
            return 0;
        }
        return 0;
    }

    private int midFind(int[] arr, int x) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x){
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public int numSpecial(int[][] mat) {
        int count = 0;
        int row = mat.length;
        int col = mat[0].length;
        int[] sumRow = new int[row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sumRow[i] += mat[i][j];
            }
        }

        int[] sumCol = new int[col];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                sumCol[i] += mat[j][i];
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count += mat[i][j] == 1 && sumRow[i] == 1 && sumCol[j] == 1 ? 1 : 0;
            }
        }
        return count;
    }
    public int key(String s) {
        // write your code here
        return 0;
    }
    static int mode = 1000000007;


    public int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        int len = jump.length;
        Bus[] buses = new Bus[len];
        for (int i = 0; i < len; i++) {
            buses[i] = new Bus(jump[i], cost[i]);
        }
        PriorityQueue<Point> que = new PriorityQueue<Point>((a, b) -> ((int) (a.time - b.time)));
        que.offer(new Point(1, inc * 1l));
        long min = target * inc;
        long max = min;
        Map<Integer, Long> val = new HashMap<>();
        val.put(1, inc * 1l);
        while (!que.isEmpty()) {
            Point tmp = que.poll();
            if (val.getOrDefault(tmp.now, max) < tmp.time || tmp.time >= min) {
                continue;
            }
            for (int i = 0; i < len; i++) {
                int next = tmp.now * buses[i].jump;
                long nextCost = tmp.time + buses[i].cost;
                if (next >= target) {
                    nextCost += (next - target) * dec;
                    min = Math.min(min, nextCost);
                } else {
                    val.put(next, nextCost);
                    que.offer(new Point(next, nextCost));
                }
            }
            Long tmpTime = val.get(tmp.now - 1);
            if (tmp.now > 0 && (tmpTime == null ||  tmpTime > tmp.time + dec) && tmp.time + dec < min) {
                que.offer(new Point(tmp.now - 1, tmp.time + dec));
                val.put(que.peek().now, que.peek().time);
            }
            int next = tmp.now + 1;
            long nextCost = tmp.time + inc;
            if (next == target) {
                min = Math.min(min, nextCost);
            } else if (val.get(next) == null || val.get(next) < min){
                val.put(next, nextCost);
                que.offer(new Point(next, nextCost));
            }
        }
        return (int) (min % mode);
    }

    static class Point {
        int now;
        long time;

        Point(int n, long t) {
            now = n;
            time = t;
        }
    }

    class Bus {
        int jump;
        int cost;

        Bus(int j, int c) {
            jump = j;
            cost = c;
        }
    }

    public int minimumOperations(String leaves) {
        int len = leaves.length();
        int max = len;
        int[][] dp = new int[len + 1][3];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], max);
        }
        dp[0][0] = 0;
        for (int i = 0; i < len; i++) {
            char ch = leaves.charAt(i);
            if (ch == 'r') {
                dp[i + 1][0] = dp[i][0];
                dp[i + 1][1] = i > 0 ? Math.min(dp[i][0] + 1, dp[i][1] + 1) : max;
                dp[i + 1][2] = i > 1 ? Math.min(dp[i][2], dp[i][1]) : max;
            } else {
                dp[i + 1][0] = dp[i][0] + 1;
                dp[i + 1][1] = i > 0 ? Math.min(dp[i][1], dp[i][0]) : max;
                dp[i + 1][2] = i > 1 ? Math.min(dp[i][2] + 1, dp[i][1] + 1) : max;
            }
        }
        return dp[len][2];
    }

//    public int minimumOperations(String leaves) {
//        int len = leaves.length();
//        int[] countArr = new int[len + 1];
//        for (int i = 0; i < len; i++) {
//            countArr[i + 1] = countArr[i] + leaves.charAt(i) == 'y' ? 1 : 0;
//        }
//
//        int min = leaves.length();
//        int leftChaner = leaves.charAt(0) == 'y' ? 1 : 0;
//        int riChaner = leaves.charAt(len - 1) == 'y' ? 1 : 0;
//        int left = 1;
//        int end = len - 1;
//        while (left <= end) {
//            int tmp = leftChaner + riChaner + (end - left + 1) - (countArr[end + 1] - countArr[left]);
//            min = Math.min(min, tmp);
//            if ()
//        }
//    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int count = 0;
        int mode = 1000000007;
        for (int i = 0; i < staple.length; i++) {
            count += midFind(0, drinks.length, drinks, x - staple[i]);
            count %= mode;
        }
        return count;
    }

    int midFind(int left, int ri, int[] arr, int tar) {
        int mid = 0;
        while (left < ri) {
            mid = (left + ri) / 2;
            if (arr[mid] <= tar) {
                left = mid + 1;
            } else {
                ri = mid;
            }
        }
        return ri;
    }


    int lowbit(int x) {
        return x & -x;
    }
}
