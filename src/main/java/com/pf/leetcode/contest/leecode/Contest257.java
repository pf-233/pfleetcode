package com.pf.leetcode.contest.leecode;

import java.util.*;

public class Contest257 {
    public static void main(String[] args) {
        Contest257 contest257 = new Contest257();
        int[][] pr = new int[][]{
                {7, 9},
                {10, 7},
                {6, 9},
                {10, 4},
                {7, 5},
                {7, 10}

        };
        System.out.println(contest257.numberOfWeakCharacters(pr));
        contest257.threeSum(new int[]{1,2,-2,-1});
    }

    public int numberOfWeakCharacters(int[][] properties) {
        int res = 0;
        Arrays.sort(properties, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int len = properties.length;
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        for (int i = 0; i < len; i++) {
            int tmp = treeMap.getOrDefault(properties[i][1], 0);
            treeMap.put(properties[i][1], ++tmp);
        }

        for (int i = 0; i < len - 1 && properties[i][0] < properties[len - 1][0]; i++) {
            int val = properties[i][1];
            Integer key = treeMap.ceilingKey(val + 1);
            if (key != null) {
                res++;
            }
            int tmp = treeMap.get(val);
            tmp--;
            if (tmp > 0) {
                treeMap.put(val, tmp);
            } else {
                treeMap.remove(val);
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new LinkedList();
        Arrays.sort(nums);
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        int ind = 0;
        int zerocnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                set1.add(nums[i]);
                ind = i;
            } else if (nums[i] == 0) {
                zerocnt++;
                set1.add(nums[i]);
                ind = i;
            } else {
                set2.add(nums[i]);
            }
        }

        List<Integer> tmp = null;

        if (zerocnt > 2) {
            tmp = new ArrayList(3);
            tmp.add(0);
            tmp.add(0);
            tmp.add(0);
            list.add(tmp);
        }


        // 一正
        for (int i = 0; i < ind; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j <= ind; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (set2.contains(0 - nums[j] - nums[i])) {
                    tmp = new ArrayList(3);
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(0 - nums[j] - nums[i]);
                    list.add(tmp);
                }
            }
        }
        // 两正
        for (int i = ind + 1; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (set1.contains(0 - nums[j] - nums[i])) {
                    tmp = new ArrayList(3);
                    tmp.add(0 - nums[j] - nums[i]);
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    list.add(tmp);
                }
            }
        }
        return list;
    }
}
