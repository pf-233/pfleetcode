package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * author：panf
 * date：11/19/2023
 * Description:
 */
public class Contest374 {

    public static void main(String[] args) {
        Contest374 contest = new Contest374();
        int[] coins = null;
        int target;
//        target = 19;
//        coins = new int[]{1,4,10};
        coins = new int[]{1,1,1};
        target = 20;
//        System.out.println(contest.minimumAddedCoins(coins, target));
    }

    // 1,2,4,8,16
    //1,10,100,1000,10000
//    public int minimumAddedCoins(int[] coins, int target) {
//        Arrays.sort(coins);
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        for (int i = 0; i < coins.length; i++) {
//            queue.offer(coins[i]);
//        }
//        int bit = 0;
//        while (target > 0) {
//            bit++;
//            target >>= 1;
//        }
//
//        int next = 1;
//        for (int i = 0; i < coins.length; i++) {
//            while (coins[i] < )
//        }
//    }

//
//    public int minimumAddedCoins(int[] coins, int target) {
//        Arrays.sort(coins);
//        int[] visit = new int[target + 1];
//        LinkedList<Integer> list = new LinkedList();
//        for (int i = 0; i < coins.length; i++) {
//            LinkedList<Integer> add = new LinkedList();
//            for (Integer temp : list) {
//                int next = coins[i] + temp;
//                if (next <= target && visit[next] == 0) {
//                    add.add(next);
//                    visit[next] = 1;
//                }
//            }
//            list.addAll(add);
//            if (visit[coins[i]] == 0) {
//                visit[coins[i]] = 1;
//                list.add(coins[i]);
//            }
//        }
//        int cnt = 0;
//        for (int i = 1; i <= target; i++) {
//            if (visit[i] != 0) {
//                continue;
//            }
//            cnt++;
//            visit[i] = 1;
//            LinkedList<Integer> add = new LinkedList();
//            for (Integer temp : list) {
//                int next = i + temp;
//                if (next <= target && visit[next] == 0) {
//                    add.add(next);
//                    visit[next] = 1;
//                }
//            }
//            add.add(i);
//            list.addAll(add);
//        }
//        return cnt;
//    }
}
