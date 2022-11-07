package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Contest237 {
    public static void main(String[] args) {
        int[][] tasks = new int[][]{
                {1,2},{2,4},{3,2},{4,1}
        };
        Contest237 contest237 = new Contest237();
        Arrays.stream(contest237.getOrder(tasks)).forEach(e-> System.out.println(e));
//[[7,10],[7,12],[7,5],[7,4],[7,2]]
    }
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        Node[] nodes = new Node[len];

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i, tasks[i][0], tasks[i][1]);
        }

        // 按开始时间排序
        Arrays.sort(nodes, (a, b) -> a.startTime == b.startTime ? a.needTime - b.needTime : a.startTime - b.startTime);
        PriorityQueue<Node> queue = new PriorityQueue<>((a,  b)-> a.needTime == b.needTime ? a.id - b.id : a.needTime - b.needTime);

        int time = nodes[0].startTime + nodes[0].needTime;
        res[0] = nodes[0].id;
        queue.offer(nodes[0]);
        int top = 0;
        res[top++] = queue.poll().id;

        Node tmp = null;
        int i = 1;
        while (i < len || !queue.isEmpty()) {
            while (i < len && time >= nodes[i].startTime) {
                queue.offer(nodes[i++]);
            }
            if (queue.isEmpty() && i < len) {
                time = nodes[i].startTime + nodes[i].needTime;
                queue.offer(nodes[i++]);
                continue;
            }
            tmp = queue.poll();
            res[top++] = tmp.id;
            time += tmp.needTime;
        }
        return res;
    }

    class Node {
        private int id;
        private int startTime;
        private int needTime;
        Node (int i, int st, int nt) {
            id = i;
            startTime = st;
            needTime = nt;
        }

    }
}
