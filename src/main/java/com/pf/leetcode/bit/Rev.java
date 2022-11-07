package com.pf.leetcode.bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Rev {

    public static void main(String[] args) {
        Rev rev = new Rev();
        System.out.println(rev.revArr(5));
        System.out.println(rev.mostCommonWord("Bob. hIt, baLl", new String[]{"bob", "hit"}));
    }

    private static final String sign =  "!?',;.";
    public String mostCommonWord(String paragraph, String[] banned) {
        StringBuilder sb = new StringBuilder(paragraph.length());
        for (int i = 0; i < paragraph.length(); i++) {
            char cs = paragraph.charAt(i);
            if (cs >= 'A' && cs <= 'Z') {
                sb.append((char)( cs + 'a' - 'A'));
            } else if (sign.contains(cs + "")) {
                sb.append(" ");
            } else {
                sb.append(cs);
            }
        }
        String[] str = sb.toString().split(" ");
        Map<String, Integer> countMap = new HashMap();
        Set<String> set = Arrays.stream(banned).collect(Collectors.toSet());
        int max = 0;
        String res = "";
        for (int i = 0; i < str.length; i++) {
            if (!set.contains(str[i]) || !"".equals(str[i])) {
                int tmp = countMap.getOrDefault(str[i], 0);
                countMap.put(str[i], ++tmp);
                if (tmp > max) {
                    max = tmp;
                    res = str[i];
                }
            }
        }
        return res;
    }
    public int[] revArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = getRev(i);
        }
        return arr;
    }

    private int getRev(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>= 1;
        }
        return res;
    }

}
