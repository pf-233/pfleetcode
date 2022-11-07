package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Contest273 {
    public static void main(String[] args) {
        Contest273 contest273 = new Contest273();
//        int[] res = contest273.executeInstructions(3, new int[]{0,1}, "RRDDLU");
//        System.out.println(res);
        contest273.getDistances(new int[]{2,1,3,1,2,3,3});
    }

    public long[] getDistances(int[] arr) {
        Map<Integer, LinkedList<Integer>> map = new HashMap();
        LinkedList<Integer> tmpList = null;
        long[] res = new long[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            tmpList = map.getOrDefault(arr[i], new LinkedList());
            tmpList.addLast(i);
            map.put(arr[i], tmpList);
        }

        for (int i = 0; i < arr.length; i++) {
            if (res[i] != -1) {
                continue;
            }
            fillRes(map, res, arr[i], i);
        }
        return res;
    }

    private void fillRes(Map<Integer, LinkedList<Integer>> map, long[] res, int key, int ind) {
        LinkedList<Integer> list = map.get(key);
        long[] sum = new long[list.size()];
        int[] arr = new int[list.size()];
        arr[0] = list.removeFirst();
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            arr[i] = list.removeFirst();
            sum[i] = sum[i - 1] + arr[i];
        }
        // ind[j] - ind[i] +  ind[j - 1] - ind[i] +  ind[j - 2] - ind[i];
        // ind[i] - ind[i - 1] + ind[i] - ind[i - 2];
        for (int i = 0; i < arr.length; i++) {
            long sumRight = sum[sum.length - 1] - sum[i];
            long sumLeft =  sum[i];
            res[arr[i]] = sumRight - 1L * arr[i] * (sum.length - i - 1);
            res[arr[i]] += 1L * arr[i] * (i + 1) - sumLeft;
        }
    }

    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] res = new int[m];
        for (int i = 0; i < s.length(); i++) {
            int step = 0;
            int[] tmppos = startPos;
            for (int j = i; j < m; j++) {
                tmppos = getNext(tmppos, s.charAt(j), n);
                if (tmppos[0] == -1) {
                    break;
                }
                step++;
            }
            res[i] = step;
        }
        return res;
    }

    private int[] getNext(int[] startPos, char nextChar, int max) {
        int[] next = new int[2];
        if (nextChar == 'L') {
            next[0] = startPos[0];
            next[1] = startPos[1] - 1;
        } else if (nextChar == 'R') {
            next[0] = startPos[0];
            next[1] = startPos[1] + 1;
        } else if (nextChar == 'U') {
            next[0] = startPos[0] - 1;
            next[1] = startPos[1];
        } else if (nextChar == 'D') {
            next[0] = startPos[0] + 1;
            next[1] = startPos[1];
        }
        if (next[0] < 0 || next[0] >= max || next[1] < 0 || next[1] >= max) {
            Arrays.fill(next, -1);
        }
        return next;
    }
}
