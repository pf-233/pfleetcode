package com.pf.leetcode.search;

import java.util.*;

public class LongestStrChain {
    public static void main(String[] args) {
        LongestStrChain longestStrChain = new LongestStrChain();
        String[] words = new String[] {
            "a","b","ba","bca","bda","bdca"
        };
        System.out.println(longestStrChain.longestStrChain(words));
    }
    public int longestStrChain(String[] words) {
        Map<String, List<String>> map = new HashMap();
        List<String> temp = null;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0;  j < words.length; j++) {
                if (words[i].length() + 1 == words[j].length() && isBefore(words[i], words[j])) {
                    temp =  map.getOrDefault(words[i], new LinkedList());
                    temp.add(words[j]);
                    map.put(words[i], temp);
                }
            }
        }

        Map<String, Integer> memo = new HashMap();
        int max = 1;
        for (String key : map.keySet()) {
            max = Math.max(dfs(key, memo, map), max);
        }
        return max;
    }

    private int dfs(String key, Map<String, Integer> memo, Map<String, List<String>> map) {
        if (memo.get(key) != null) {
            return memo.get(key);
        }
        int max = 0;
        for (String nextKey : map.getOrDefault(key, Collections.emptyList())) {
            max = Math.max(max, dfs(nextKey, memo, map));
        }
        memo.put(key, max + 1);
        return max + 1;
    }

    private boolean isBefore(String before, String after) {
        int ind = 0;
        while (ind < before.length() && before.charAt(ind) == after.charAt(ind)) {
            ind++;
        }
        return after.substring(ind + 1).equals(before.substring(ind));
    }
}
