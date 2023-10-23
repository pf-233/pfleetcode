package com.pf.leetcode.twopoint;

import java.util.*;

/**
 * author：11413
 * date：10/12/2023
 * Description:
 */
public class ShortestSeq {

    public static void main(String[] args) {
        int[] big;
        int[] small;
        big = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        small = new int[] {1, 5, 9};
        ShortestSeq shortestSeq = new ShortestSeq();
        shortestSeq.shortestSeq(big, small);
        Arrays.stream(shortestSeq.shortestSeq(big, small)).forEach(System.out::println);
    }
    public int[] shortestSeq(int[] big, int[] small) {
        Set<Integer> set = new HashSet();
        for (int num : small) set.add(num);
        Map<Integer, Integer> cntMap = new HashMap();
        int[] ans = null;
        int left = 0;
        int right = 0;
        int[] temp = null;
        while (right < big.length) {
            temp = getShortestSeq(big, left, right, set, cntMap);
            if (temp == null) {
                break;
            }
            left = temp[0];
            right = temp[1];
            if (ans == null) {
                ans = temp;
            } else {
                int lenans = ans[1] - ans[0];
                int lentemp = temp[1] - temp[0];
                ans = lentemp < lenans ? temp : ans;
            }

        }
        return ans;
    }

    private int[] getShortestSeq(int[] big, int left, int right, Set<Integer> set, Map<Integer, Integer> cntMap) {
        int[] ans = null;
        while (right < big.length) {
            int now = big[right++];
            if (set.contains(now)) {
                cntMap.put(now, cntMap.getOrDefault(now, 0) + 1);
            }
            if (cntMap.size() == set.size()) {
                break;
            }
        }
        if (cntMap.size() < set.size()) {
            return ans;
        }
        while (cntMap.size() == set.size()) {
            int now = big[left++];
            if (!set.contains(now)) {
                continue;
            }

            int cnt = cntMap.get(now);
            if (--cnt == 0) {
                cntMap.remove(now);
                break;
            } else {
                cntMap.put(now, cnt);
            }
        }
        return new int[]{left - 1, right - 1};
    }
}
