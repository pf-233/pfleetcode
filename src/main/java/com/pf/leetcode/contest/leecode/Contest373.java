package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：11/19/2023
 * Description:
 */
public class Contest373 {

    public static void main(String[] args) {
        Contest373 contest = new Contest373();
//        int[][] mat;
//        int k;
//        mat = new int[][] {
//                {2,2},
//                {2,2}
//        };
//        k = 3;
//        System.out.println(contest.areSimilar(mat, k));
//
//        String s;
//        int k;
//        s = "baeyh";
//        k = 2;
//        System.out.println(contest.beautifulSubstrings(s, k));

        int[] nums = new int[] {1,5,3,9,8};
        int limit = 2;
        System.out.println(contest.lexicographicallySmallestArray(nums, limit));
    }

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        // 满足|nums[i] - nums[j]| <= limit 的分成一组然后排序
        int len = nums.length;
        int[] ans = new int[len];
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        List<Integer>[] lists = new ArrayList[len];
//        for (int i = 0; i < len; i++) {
//            //nums[i] > nums[j]  nums[i] - nums[j] <= limit   ==> nums[j]  >= limit - nums[i]
//            int lowBound = limit - nums[i];
//            //nums[i] < nums[j]  nums[j] - nums[i] <= limit   ==> nums[j]  <= limit + nums[i]
//            int highBound = nums[i] + limit;
//            int lind = map.height(lowBound);
//            int hind = map.cellingKey(highBound);
//            int ind1 = find(map, lind);
//            int ind2 = find(map, hind);
//            union(map, ind1, ind2);
//        }
//
//        Map<Integer, List<Integer>> vals = new HashMap();
//        List<Integer> temp;
//        for (int i = 0; i < len; i++) {
//            int root = find(map, )
//        }
        return ans;
    }

    private int find(Map<Integer, Integer> map, int ind) {
        while (map.get(ind) != ind) {
            map.put(ind, map.get(ind));
            ind = map.get(ind);
        }
        return ind;
    }

    private void union(Map<Integer, Integer> map, int ind1, int ind2) {
        int i1 = find(map, ind1);
        int i2 = find(map, ind2);
        int max = Math.max(i1, i2);
        int min = Math.min(i1, i2);
        map.put(max, min);
    }

    Set<Character> set = new HashSet() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};

    public int beautifulSubstrings(String s, int k) {
        int len = s.length();
        int vowels = 0;
        int consonants = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            vowels = 0;
            consonants = 0;
            for (int j = i; j >= 0; j--) {
                if (set.contains(s.charAt(j))) {
                    vowels++;
                } else {
                    consonants++;
                }
                if (vowels == consonants && (vowels * consonants) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public boolean areSimilar(int[][] mat, int k) {
        int r = mat.length;
        int c = mat[0].length;
        k %= c;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i % 1 == 0 && mat[i][(j - k + c) % c] != mat[i][j]) {
                    return false;
                } else if (i % 1 == 1 && mat[i][(j + k + c) % c] != mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
