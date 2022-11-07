package com.pf.leetcode.contest.leecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Contest270 {

    public static void main(String[] args) {
        Contest270 contest270 = new Contest270();
        contest270.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        int[] r = new int[]{1, 3, 5, 10};
//        System.out.println(contest270.addRungs(r, 2));
//        System.out.println(contest270.addRungs(new int[]{3,6,8,10}, 3));
//        System.out.println(contest270.addRungs(new int[]{3,4,6,7}, 2));
        System.out.println(contest270.addRungs(new int[]{3}, 1));
    }

    public int addRungs(int[] rungs, int dist) {
        int len = rungs.length;
        int pre = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (i < len && pre + dist < rungs[i]) {
                int need = (rungs[i] - pre) / dist;
                count += need;
                pre += need * dist;
            }

            while (i < len && rungs[i] <= pre + dist) {
                i++;
            }
            pre = rungs[--i];
        }
        return count;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap();
        List<String> tmpList = null;
        for(String s : strs) {
            String key = getKey(s);
            tmpList = map.getOrDefault(key, new LinkedList());
            tmpList.add(s);
            map.put(key, tmpList);
        }

        List<List<String>> res = new LinkedList();
        map.keySet().stream().forEach(e -> res.add(map.get(e)));
        return res;
    }

    public String getKey(String strs) {
        int[] ch = new int[26];
        for(char c : strs.toCharArray()) {
            ch[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (ch[i] > 0) {
                sb.append((char) (i + 'a')).append(ch[i]);
            }
        }
        return sb.toString();
    }
}
