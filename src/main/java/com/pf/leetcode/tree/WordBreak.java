package com.pf.leetcode.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        String s;
        List<String> wordDict;
//        s = "leetcode";
//        wordDict = Arrays.asList("leet","code");
        s = "aaaaaaa";
        wordDict = Arrays.asList("aaaa","aaa");
        System.out.println(wordBreak.wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet();
        int maxLen = 0;
        for (String str : wordDict) {
            set.add(str);
            maxLen = Math.max(maxLen, s.length());
        }

        int n = s.length();
        boolean[] vaild = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, i - maxLen); j <= i; j++) {
                if (set.contains(s.substring(j, i + 1))) {
                    vaild[i] |= j == 0 ? true : vaild[j - 1];
                }
            }
        }
        return vaild[n - 1];
    }
}
