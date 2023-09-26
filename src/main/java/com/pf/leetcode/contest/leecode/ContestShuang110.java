package com.pf.leetcode.contest.leecode;

import java.util.*;

public class ContestShuang110 {


    public static void main(String[] args) {
        ContestShuang110 contestShuang110 = new ContestShuang110();
        List<Integer> nums;
//        nums = Arrays.asList(1,2,1,2);
        nums = Arrays.asList(5,5,5,5);
        System.out.println(contestShuang110.minimumSeconds(nums));
    }

    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap();
        LinkedList<Integer> temp;
        int top = 0;
        for (Integer num : nums) {
            temp = map.getOrDefault(num, new LinkedList());
            temp.add(top++);
            map.put(num, temp);
        }

        int min = nums.size() / 2;
        for (LinkedList<Integer> list : map.values()) {
            if (list.size() > 1) {
                list.add(list.get(0) + nums.size());
                int pre = -1;
                int max = 0;
                for (int ind : list) {
                    if (pre != -1) {
                        max = Math.max(max, (ind - pre + 1) / 2);
                    }
                    pre = ind;
                }
                min = Math.min(min, max);
            }
        }
        return min;
    }
}
