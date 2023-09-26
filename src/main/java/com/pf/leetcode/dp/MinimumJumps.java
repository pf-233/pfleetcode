package com.pf.leetcode.dp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MinimumJumps {
    int maxValue = 10000005;

    public static void main(String[] args) {
        int[] forbidden;
        int a;
        int b;
        int x;
        forbidden = new int[]{162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98};
        a = 29;
        b = 98;
        x = 80;
        System.out.println(new MinimumJumps().minimumJumps(forbidden, a, b, x));
    }
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int max = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, x) + b;
        Set<Integer> set = new HashSet();
        for (int temp : forbidden) {
            set.add(temp);
        }
        Set<Integer> visit = new HashSet();
        Queue<int[]> que = new ArrayDeque<int[]>();
        // [position, times, direction]
        que.offer(new int[]{0, 0, 1});
        visit.add(0);
        int ans = -1;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int position = now[0];
            int times = now[1];
            int direction = now[2];
            if (position == x) {
                ans = times;
                break;
            }
            int next = position + a;
            int nextDirection = 1;
            if (next >= 0 && next <= max && !visit.contains(next * nextDirection) && !set.contains(next)) {
                que.offer(new int[]{next, times + 1, nextDirection});
                visit.add(next * nextDirection);
            }
            if (direction == 1) {
                next = position - b;
                nextDirection = -1;
                if (next >= 0 && next <= max && !visit.contains(next * nextDirection) && !set.contains(next)) {
                    que.offer(new int[]{next, times + 1, nextDirection});
                    visit.add(next * nextDirection);
                }
            }
        }
        return ans;
    }
}
