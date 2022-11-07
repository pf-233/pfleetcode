package com.pf.leetcode.heap;

import java.util.*;
import java.util.stream.Collectors;

public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> resList = new LinkedList<>();
        int[][] arr = new int[26][2];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], -1);
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < s.length(); i++) {
            int tmp = s.charAt(i) - 'a';
            if (arr[tmp][0] < 0) {
                arr[tmp][0] = i;
            } else {
                arr[tmp][1] = i;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] > 0) {
                priorityQueue.offer(arr[i]);
            }
        }

        int[] pre = priorityQueue.poll();
        int[] next = null;
        while (!priorityQueue.isEmpty()) {
            next = priorityQueue.poll();
            if (pre[1] < next[0]) {
                resList.add(pre[1] - pre[0] + 1);
                pre = next;
            } else if (pre[1] < next[1]) {
                pre[1] = next[1];
            }
        }
        return resList;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap();
        List<String> tmp = null;
        for (int i = 0; i < strs.length; i++) {
            String key = getKey(strs[i]);
            tmp = map.getOrDefault(key, new LinkedList());
            tmp.add(strs[i]);
            map.put(key, tmp);
        }
        return map.values().stream().collect(Collectors.toList());
    }

    private String getKey(String key) {
        int[] arr = new int[26];
        for (int i = 0; i < key.length(); i++) {
            arr[key.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sb.append('a' + i).append(arr[i]);
            }
        }
        return sb.toString();
    }
}
