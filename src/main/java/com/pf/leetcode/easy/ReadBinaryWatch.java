package com.pf.leetcode.easy;

import java.util.*;

/**
 * author：panf
 * date：11/1/2023
 * Description:
 */
public class ReadBinaryWatch {

    public static void main(String[] args) {
        ReadBinaryWatch readBinaryWatch = new ReadBinaryWatch();
        for (String s : readBinaryWatch.readBinaryWatch(10)) {
            System.out.println(s);
        }
    }

    public List<String> readBinaryWatch(int turnedOn) {
        Map<Integer, List<String>> hmap = new HashMap();
        hmap.put(0, Arrays.asList("0:"));
        for (int i = 1; i < 12; i++) {
            int ind = i;
            int cnt = 0;
            while (ind > 0) {
                ind -= ind & -ind;
                cnt++;
            }
            List<String> temp = hmap.getOrDefault(cnt, new LinkedList());
            temp.add(i + ":");
            hmap.put(cnt, temp);
        }

        Map<Integer, List<String>> mmap = new HashMap();
        mmap.put(0, Arrays.asList("00"));
        for (int i = 1; i < 60; i++) {
            int ind = i;
            int cnt = 0;
            while (ind > 0) {
                ind -= ind & -ind;
                cnt++;
            }
            List<String> temp = mmap.getOrDefault(cnt, new LinkedList());
            temp.add((i < 10 ? "0" : "") + i);
            mmap.put(cnt, temp);
        }

        List<String> ans = new LinkedList();
        for (int i = 0; i < 5; i++) {
            int need = turnedOn - i;
            List<String> m = mmap.get(need);
            List<String> h = hmap.get(i);
            if (m == null || h == null) {
                continue;
            }

            for (String aa : h) {
                for (String bb : m) {
                    ans.add(aa + bb);
                }
            }
        }
        return ans;
    }
}
