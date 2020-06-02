package com.pf.leetcode.stack;

import com.pf.leetcode.tree.TreeNode;

import java.util.*;

/**
 * @author pf
 * @date 2020-05-23 11:01
 **/
public class MinWindow {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
        System.out.println(minWindow("a","aa"));
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int len = t.length();

        List<Integer> list = new ArrayList();
        Map<Character, Integer> sMap = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            if(tMap.getOrDefault(s.charAt(i), 0) > 0){
                list.add(i);
                sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            }
        }

        for (Character ch : tMap.keySet()){
            if (tMap.get(ch) > sMap.getOrDefault(ch, 0)){
                return "";
            }
        }

        int minLen = list.get(list.size() - 1) - list.get(0);
        int minStart = list.get(0);
        int minEnd = list.get(list.size() - 1);
        Map<Character, Integer> tempMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int count = 0;
        while(end < list.size()){
            for(; end < list.size(); end++){
                Character tempCh = s.charAt(list.get(end));
                tempMap.put(tempCh, tempMap.getOrDefault(tempCh, 0) + 1);
                if (tempMap.get(tempCh) <= tMap.get(tempCh)){
                    count++;
                }
                if(count == len){
                    //排除第一个然后排除多余的 比如 AAABCAAB ABC   ----》 AABCAAB ABC -> ABCAAB ABC
                    while (count == len){
                        int tempMinLen = list.get(end) - list.get(start);
                        if(tempMinLen < minLen){
                            minStart = list.get(start);
                            minEnd = list.get(end);
                            minLen = tempMinLen;
                            System.out.println(minStart + "  " + minEnd);
                        }
                        tempCh = s.charAt(list.get(start));
                        tempMap.put(tempCh, tempMap.get(tempCh) - 1);
                        //如果在数据中就是多余的就不减
                        if (tempMap.get(tempCh) < tMap.get(tempCh)){
                            count--;
                        }
                        start++;
                    }
                    //下次往下一个继续
                    end++;
                    break;
                }
            }
        }
        return s.substring(minStart, minEnd + 1);
    }
}
