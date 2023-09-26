package com.pf.leetcode.array;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CanMakePaliQueries {
    public static void main(String[] args) {
        CanMakePaliQueries canMakePaliQueries = new CanMakePaliQueries();
        String s;
        int[][] queries;
        s = "abcda";
        queries = new int[][]{
                {3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}
        };
        Collections.synchronizedCollection(new LinkedHashSet<>());
        canMakePaliQueries.canMakePaliQueries(s, queries);
    }
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int len = s.length();
        int[][] cnt = new int[len + 1][26];
        int[] roundCnt = new int[26];
        for (int i = 0; i < len; i++) {
            roundCnt[s.charAt(i) - 'a']++;
            cnt[i] = Arrays.copyOf(roundCnt, roundCnt.length);
        }

        List<Boolean> ans = new ArrayList(queries.length);
        int[] sentinel = new int[26];
        for (int[] query : queries) {
            int[] start = query[0] == 0 ? sentinel : cnt[query[0] - 1];
            int[] end = cnt[query[1]];
            int odd = 0;
            for (int i = 0; i < roundCnt.length; i++) {
                odd += (end[i] - start[i]) & 1;
            }
            ans.add(odd / 2 <= query[2]);
        }
        return ans;
    }
}
