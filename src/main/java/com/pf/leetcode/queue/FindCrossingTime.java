package com.pf.leetcode.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindCrossingTime {

    public static void main(String[] args) {
        FindCrossingTime findCrossingTime = new FindCrossingTime();
        int n;
        int k;
        int[][] time;
//        n = 1;
//        k = 3;
//        time = new int[][] {{1,1,2,1}, {1,1,3,1}, {1,1,4,1}};
        n = 3;
        k = 2;
        time = new int[][] {{1,9,1,8}, {10, 10, 10, 10}};
        System.out.println(findCrossingTime.findCrossingTime(n,k,time));;
    }

    public int findCrossingTime(int n, int k, int[][] time) {
        int[][] seq = new int[k][5];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 4; j++) {
                seq[i][j] = time[i][j];
            }
            seq[i][4] = i;
        }
        Arrays.sort(seq, (a, b) -> a[0] + a[2] == b[0] + b[2] ? (b[4] - a[4]) : (b[0] + b[2] - a[0] - a[2]));
        //index 和时间  按时间排
        PriorityQueue<int[]> leftWaitQueue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        //按index排
        PriorityQueue<int[]> leftAbleQueue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        //按时间排
        PriorityQueue<int[]> rightWaitQueue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        //按index排
        PriorityQueue<int[]> rightAbleQueue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < seq.length; i++) {
            leftAbleQueue.offer(new int[]{i, 0});
        }
        int timestamp = 0;
        while (n >= 0) {
            waitToAble(rightWaitQueue, rightAbleQueue, timestamp);
            // 右边的先过桥
            while (rightAbleQueue.size() != 0) {
                int[] now = rightAbleQueue.poll();
                // 过桥时间加在时间戳上
                timestamp += seq[now[0]][2];
                // 等待时间加上放下并回到桥边
                now[1] = timestamp + seq[now[0]][3];
                leftWaitQueue.offer(now);
                waitToAble(rightWaitQueue, rightAbleQueue, timestamp);
            }

            // 不需要过桥了，已经都拿完了
            if (n == 0) {
                break;
            }
            waitToAble(leftWaitQueue, leftAbleQueue, timestamp);
            // 左边挑一个过桥
            if (leftAbleQueue.size() > 0) {
                int[] now = leftAbleQueue.poll();
                timestamp += seq[now[0]][0];
                now[1] = timestamp + seq[now[0]][1];
                rightWaitQueue.offer(now);
                n--;
            } else if (leftWaitQueue.size() > 0){
                // 有等待过桥的 且时间没有满足的时候就直接延长时间
                int next1 = leftWaitQueue.peek()[1];
                // rightAble 都已经取完了
                int next2 = rightWaitQueue.isEmpty() ? Integer.MAX_VALUE : rightWaitQueue.peek()[1];
                timestamp = Math.min(next1, next2);
            } else {
                // 左边没有变化，那么就取右边的时间
                timestamp = rightWaitQueue.peek()[1];
            }
        }
        // 计算剩余的左边过桥时间
        while (rightWaitQueue.size() != 0 || rightAbleQueue.size() != 0) {
            waitToAble(rightWaitQueue, rightAbleQueue, timestamp);
            while (rightAbleQueue.size() != 0) {
                int[] now = rightAbleQueue.poll();
                // 过桥时间加在时间戳上
                timestamp += seq[now[0]][2];
                waitToAble(rightWaitQueue, rightAbleQueue, timestamp);
            }
            if (rightWaitQueue.size() > 0) {
                timestamp = rightWaitQueue.peek()[1];
            }
        }
        return timestamp;
    }

    private void waitToAble(PriorityQueue<int[]> wait, PriorityQueue<int[]> able, int timestamp) {
        while (wait.size() > 0 && wait.peek()[1] <= timestamp) {
            able.offer(wait.poll());
        }
    }
}
