package com.pf.leetcode.mid;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author panf
 * @date 9/21/2023 9:59 AM
 */
public class SubsetsWithDup {
//    public static void main(String[] args) {
//        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();
//        int[] nums;
//        nums = new int[]{4,4,4,1,4};
//        for (List<Integer> num : subsetsWithDup.subsetsWithDup(nums)) {
//            num.stream().forEach(e -> System.out.print(e + " "));
//            System.out.println();
//        }
//    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new LinkedList();
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, ans);
        ans.add(new ArrayList());
        return ans;
    }

    private void subsetsWithDup(int[] nums, int start, List<List<Integer>> ans) {
        if (nums.length == start) {
            return;
        }
        int cnt = 1;
        while (start + 1 < nums.length && nums[start] == nums[start + 1]) {
            start++;
            cnt++;
        }
        List<List<Integer>> add = new LinkedList();
        List<Integer> nextCnt = new ArrayList();
        for (int i = 0; i < cnt; i++) {
            nextCnt.add(nums[start]);
            List<Integer> next = new ArrayList();
            next.addAll(nextCnt);
            for (List<Integer> temp : ans) {
                List<Integer> newList = new ArrayList();
                newList.addAll(temp);
                newList.addAll(next);
                add.add(newList);
            }
            add.add(next);
        }
        ans.addAll(add);
        subsetsWithDup(nums, start + 1, ans);
    }
}
