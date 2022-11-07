package com.pf.leetcode.contest.codeforces.div2;

import java.beans.Visibility;
import java.util.*;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.Collectors;

public class Round669 {
    static AtomicMarkableReference atomicMarkableReference;
    public static void main(String[] args) {
        atomicMarkableReference.attemptMark(Round669.class, true);
        AbstractQueuedSynchronizer
        new Round669().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }

    int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        List<List<Integer>> res = new LinkedList();
        if(candidates == null) {
            return res;
        }
        Arrays.sort(candidates);
        int[] vis = new int[candidates.length];
        helper(vis, -1, res, target);
        return res;
    }

    private void helper(int[] vis, int now, List<List<Integer>> res, int target) {
        pr(vis);
        if(target < 0) {
            return;
        } else if (target == 0) {
            List<Integer> tmp = new LinkedList();
            for(int i = 0; i < vis.length; i++) {
                if(vis[i] == 1) {
                    tmp.add(candidates[i]);
                }
            }
            res.add(tmp);
            return;
        }
        for(int i = now + 1; i < vis.length; i++) {
            int count = 0;
            int tmp = i;
            while(tmp < vis.length && candidates[i] == candidates[tmp++]) {
                count++;
            }
            for(int j = 1; j <= count; j++) {
                vis[i + j - 1] = 1;
                helper(vis, i + count - 1, res, target - j * candidates[i]);
            }
            for(int j = 1; j <= count; j++) {
                vis[i + j - 1] = 0;
            }
            i = i + count - 1;
        }
    }

    private void pr(int[] vis) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vis.length; i++) {
            if (vis[i] == 1) stringBuilder.append(" " + candidates[i]);
        }
        System.out.println(stringBuilder);
    }


    public void ahahahahahahahaha() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
                count += arr[i];
            }
            boolean removeZero = count > n / 2;
            StringBuilder sb = new StringBuilder();
            int limit = removeZero ? count / 2 : arr.length - count;
            for (int i = 0; i < limit; i++) {
                sb.append(removeZero ? " 1" : " 0");
            }
            System.out.println(limit);
            System.out.println(sb.substring(1));
        }
    }

    public void bigVova() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            int max = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
                max = Math.max(max, arr[i]);
            }
            Node[] c = new Node[n];
            for (int i = 0; i < n; i++) {
                c[i] = new Node(arr[i], this.gcd(max, arr[i]));
            }
            Arrays.sort(c, (a, d) -> d.cd - a.cd != 0 ? d.cd - a.cd : d.val - a.val);
            for (int i = 2; i < n; i++) {
                c[i].cd = this.gcd(c[i].val, c[i - 1].cd);
            }


            System.out.print(c[0].val);
            for (int i = 1; i < n; i++) {
                System.out.print(" " + c[i].val);
            }
            System.out.println();
        }
    }

    class Node {
        private int val;
        private int cd;

        Node(int v, int c) {
            val = v;
            cd = c;
        }
    }

    public int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }
//    public void ahahahahahahahaha() {
//        Scanner scanner = new Scanner(System.in);
//        int num = scanner.nextInt();
//        while (num-- > 0) {
//            int n = scanner.nextInt();
//            int[] arr = new int[n];
//
//            //偶数
//            int[] ecount = new int[n];
//            //奇数
//            int[] ocount = new int[n];
//            arr[0] = scanner.nextInt();
//            if (arr[0] == 1) {
//                ecount[0] = 1;
//            }
//            for (int i = 1; i < n; i++) {
//                arr[i] = scanner.nextInt();
//                ecount[i] = ecount[i - 1];
//                ocount[i] = ocount[i - 1];
//                if (arr[i] == 1) {
//                    if (i % 2 == 0) {
//                        ecount[i]++;
//                    } else {
//                        ocount[i]++;
//                    }
//                }
//            }
//
//            StringBuilder sb = new StringBuilder();
//            int i = 0;
//            for (; i < n; i++) {
//                int tmpe = ecount[i];
//                int tmpo = ocount[i];
//                if (arr[i] == 1) {
//                   int tmp = i % 2 == 0 ? tmpe-- : tmpo--;
//                }
//                if (ecount[n - 1] - ecount[i] + tmpo == ocount[n - 1] - ocount[i] + tmpe) {
//                    break;
//                }
//                sb.append(" " + arr[i]);
//            }
//            i++;
//            for (; i < n; i++ ) {
//                sb.append(" " + arr[i]);
//            }
//            System.out.println(n - 1);
//            System.out.println(sb.substring(1, sb.length()));
//        }
//    }
}
