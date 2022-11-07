package com.pf.leetcode.other;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class StrongPasswordChecker {
    public static void main(String[] args) {
        StrongPasswordChecker checker = new StrongPasswordChecker();
        System.out.println(checker.strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaa"));
    }
    public int strongPasswordChecker(String password) {
        int len = password.length();
        int sum = 0;
        boolean[] vis = new boolean[3];
        char pre = '*';
        int preCount = 1;
        List<Integer> countlist = new LinkedList();
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'a' &&  ch <= 'z' && !vis[0]) {
                sum++;
                vis[0] = !vis[0];
            } else if (ch >= 'A' &&  ch <= 'Z' && !vis[1]) {
                sum++;
                vis[1] = !vis[1];
            } else if (ch >= '0' &&  ch <= '9' && !vis[2]) {
                sum++;
                vis[2] = !vis[2];
            }

            if (pre == ch) {
                preCount++;
            } else {
                pre = ch;
                if (preCount >= 3) {
                    countlist.add(preCount);
                }
                preCount = 1;
            }
        }
        if (preCount >= 3) {
            countlist.add(preCount);
        }

        int res = 0;
        if (len < 6) {
            // 5个为同字符那就+1个换一个  4个同字符就加2个, 其他情况就直接加不同的
            res = Math.max(6 - len, 3 - sum);
        } else if (6<= len && len <= 20) {
            // 每3个节点换一个
            for (Integer tmp : countlist) {
                res += tmp / 3;
                sum++;
            }
        } else {
            PriorityQueue<Integer> que = new PriorityQueue<Integer>((a, b) -> a % 3 - b % 3);
            countlist.stream().forEach(e -> que.offer(e));
            int cost = len - 20;
            while (cost > 0 && !que.isEmpty()) {
                int top = que.poll();
                // cost 可能直接够了不需要继续删除
                int need = Math.min(top % 3 + 1, cost);
                res += need;
                cost -= need;
                if (top - need >= 3) {
                    que.offer(top - need);
                }
            }
            while (!que.isEmpty()) {
                res += que.poll() / 3;
                sum++;
            }
            res += cost + Math.max(3 - sum, 0);
        }
        return res;
    }
}
