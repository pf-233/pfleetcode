package com.pf.leetcode.contest.niuke;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Contest5 {

    public static void main(String[] args) {
        Contest5 contest5 = new Contest5();
//        System.out.println(contest5.decode("pqyeqfgt", 2));
        System.out.println(contest5.getNumValidPairs(5,2, new int[]{1,3,2,5,4}));
    }

    public long getNumValidPairs (int n, int m, int[] a) {
        // write code here
        PriorityQueue<Node> queue = new PriorityQueue<Node>((aa,bb)->  aa.val == bb.val ? Integer.compare(aa.index, bb.index) : Long.compare(aa.val, bb.val));
        Node[] nodes = new Node[n];
        for (int i= 0; i < n; i++) {
            nodes[i] = new Node(i + 1, a[i]);
        }
        for (int i = 0; i < m && i < n; i++) {
            queue.offer(nodes[i]);
        }
        int size = Math.min(m,n);
        Node[] res = new Node[n];
        int top = 0;
        while (!queue.isEmpty() && size <= n) {
            Node node = queue.poll();
            res[top++] = node;
            if (size < n) {
                Node inNode = nodes[size++];
                inNode.val += node.val;
                queue.offer(inNode);
            }
        }
        long ans = 0;
        int[] bit = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ans += res[i].index - 1 - query(bit, res[i].index);
            update(bit, res[i].index);
        }
        return ans;
    }

    void update(int[] bit, int index) {
        while (index < bit.length) {
            bit[index]++;
            index += lowbit(index);
        }
    }

    long query(int[] bit, int index) {
        long sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= lowbit(index);
        }
        return sum;
    }

    int lowbit(int x) {
        return x & (-x);
    }

    class Node {
        int index;
        long val;
        Node (int index, long val) {
            this.index = index;
            this.val = val;
        }
    }

    public String decode (String str, int d) {
        // write code here
        char[] map = new char[62];
        Map<Character, Integer> mapc = new HashMap(62);
        for(int i = 0; i < 10; i++) {
            map[i] =(char)('0' + i);
            mapc.put(map[i], i);
        }
        for(int i = 0; i < 26; i++) {
            map[i + 10] =(char)('A' + i);
            mapc.put(map[i + 10], i + 10);
        }
        for(int i = 0; i < 26; i++) {
            map[i + 36] =(char)('a' + i);
            mapc.put(map[i + 36], i + 36);
        }

        char[] vals = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < vals.length; i++) {
            int ind = mapc.get(vals[i]);
            sb.append(map[(ind - d + 62) % 62]);
        }
        return sb.toString();
    }
}
