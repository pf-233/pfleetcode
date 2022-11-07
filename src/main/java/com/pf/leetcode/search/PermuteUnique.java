package com.pf.leetcode.search;

import java.util.*;
import java.util.stream.Collectors;

public class PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList();
        Arrays.sort(nums);
        Map<Integer, Integer> countMap = new HashMap();
        for(int i = 0; i < nums.length ; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer tmp : countMap.keySet()) {
            int tmpcount = countMap.get(tmp);
            List<Integer> now = new ArrayList<>(nums.length);
            for (int i = 0; i < tmpcount; i++) {
                now.add(tmp);
                countMap.put(tmp, tmpcount - i - 1);
                dfs(tmp, res, countMap, now, nums.length);
            }
            countMap.put(tmp, tmpcount);
        }
        return res;
    }

    private void  dfs(int pre, List<List<Integer>> res, Map<Integer, Integer> countMap, List<Integer> now, int len) {
        if (now.size() == len) {
            res.add(now.stream().collect(Collectors.toList()));
            return;
        }
        for (Integer tmp : countMap.keySet()) {
            int tmpcount = countMap.get(tmp);
            if (tmp == pre || tmpcount == 0) {
                continue;
            }
            List<Integer> next = new ArrayList<>();
            for (Integer tmpInd : now) {
                next.add(tmpInd);
            }
            for (int i = 0; i < tmpcount; i++) {
                next.add(tmp);
                countMap.put(tmp, tmpcount - i - 1);
                dfs(tmp, res, countMap, next, len);
            }
            countMap.put(tmp, tmpcount);
        }
    }
}
