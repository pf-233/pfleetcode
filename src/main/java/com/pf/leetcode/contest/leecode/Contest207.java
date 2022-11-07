package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Contest207 {

    public int maxProductPath(int[][] grid) {
        int mode = (int) Math.pow(10, 9) + 7;
        return 0;
    }

    public int maxUniqueSplit(String s) {
        Set<String> vis = new HashSet<>();
        return maxUniqueSplit(s, vis, 0);
    }

    private int maxUniqueSplit(String s, Set<String> vis, int ind) {
        if (ind == s.length()) {
            return vis.size();
        }
        int max = 0;
        for (int i = ind + 1; i <= s.length(); i++) {
            String tmp = s.substring(ind, i);
            if (vis.contains(tmp)) {
                continue;
            }
            vis.add(tmp);
            max = Math.max(max, maxUniqueSplit(s, vis, i));
            vis.remove(tmp);
        }
        return max;
    }


    public String reorderSpaces(String text) {
        List<String> list = new ArrayList<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                count++;
                if (sb.toString().length() > 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(text.charAt(i));
            }
        }
        String temp  =  sb.toString();
        if (temp.length() > 0) {
            list.add(temp);
        }
        int scount = list.size();
        String res = "";
        int tmpcount = scount == 1 ? count : count / (scount - 1);
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < tmpcount; i++) {
            blank.append(' ');
        }
        String bb = blank.toString();
        StringBuilder resb = new StringBuilder();
        resb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            resb.append(bb).append(list.get(i));
        }
        if (scount == 1) {
            resb.append(bb);
        } else {
            for (int i = 0; i < count % (scount - 1); i++) {
                resb.append(' ');
            }
        }
        return resb.toString();
    }
}
