package com.pf.leetcode.contest.leecode;

import java.util.*;

public class Contest355 {

    public static void main(String[] args) {
        Contest355 contest355 = new Contest355();
        List<String> words;
        char separator;
//        words = Arrays.asList("one.two.three","four.five","six");
//        separator = '.';
//        words = Arrays.asList("|||");
//        separator = '|';
//        for (String s : contest355.splitWordsBySeparator(words, separator)) {
//            System.out.println(s);
//        }

        List<Integer> usageLimits;
        usageLimits = Arrays.asList(2,2,2,2,2,2,3,4);
        System.out.println(contest355.maxIncreasingGroups(usageLimits));
    }

    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits);
        int ans = 0;
        long preSum = 0;
        for (Integer x : usageLimits) {
            preSum += x;
            if (preSum > ans) preSum -= ++ans;
        }
        return ans;
    }

    public int maxIncreasingGroupsOutOfTime(List<Integer> usageLimits) {
        int n = usageLimits.size();
        // 次数的值 和对应的数字的个数
        Map<Integer, Integer> countMap = new HashMap();
        for (Integer temp : usageLimits) {
            // 最多n组,所以n个以上都一样
            temp = Math.min(temp, n);
            countMap.put(temp, countMap.getOrDefault(temp, 0) + 1);
        }
        PriorityQueue<Integer> que = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int temp : countMap.keySet()) {
            que.offer(temp);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int need = i;
            Map<Integer, Integer> backMap = new HashMap();
            while (need > 0 && !que.isEmpty()) {
                int top = que.poll();
                int count = countMap.get(top);
                countMap.remove(top);
                int diff = Math.min(count, need);
                backMap.put(top - 1, backMap.getOrDefault(top - 1, 0) + diff);
                backMap.put(top, backMap.getOrDefault(top, 0) + count - diff);
                need -= diff;
            }
            if (need > 0) {
                break;
            }
            for (Integer key : backMap.keySet()) {
                int val = backMap.get(key);
                if (val == 0 || key == 0) {
                    continue;
                }
                if (!que.contains(key)) {
                    que.offer(key);
                }
                countMap.put(key, countMap.getOrDefault(key, 0) + val);
            }
            ans = i;
        }
        return ans;
    }

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> list = new LinkedList();
        for (String str : words) {
            for (String temp : str.split(separator+"")) {
                if (!"".equals(temp)) {
                    list.add(temp);
                }
            }
        }
        return list;
    }
}
