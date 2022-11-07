package com.pf.leetcode.other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindAnagrams {
    public static void main(String[] args) {
       FindAnagrams findAnagrams = new FindAnagrams();
//       findAnagrams.findAnagrams("baa", "aa");
        findAnagrams.findAnagrams("ab", "ba");
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList();
        if (s.length() < p.length()) {
            return res;
        }
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']--;
            count[s.charAt(i) - 'a']++;
        }

        int diff = count.length;;
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                diff--;
            }
        }
        if (diff == 0) {
            res.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            char tmp = s.charAt(i);
            char pre = s.charAt(i - p.length());
            if (count[tmp - 'a']++ == 0) {
                diff++;
            }
            if (count[tmp - 'a'] == 0) {
                diff--;
            }

            if (count[pre - 'a']-- == 0) {
                diff++;
            }
            if (count[pre - 'a'] == 0) {
                diff--;
            }
            if (diff == 0) {
                res.add(i - p.length() + 1);
            }
        }
        return res;
    }
}