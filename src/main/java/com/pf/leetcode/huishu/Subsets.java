package com.pf.leetcode.huishu;

import com.pf.leetcode.tree.TreeNode;

import java.beans.Visibility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Subsets {
    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1,2,3}));
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList((int) Math.pow(2, nums.length));
        int[] vis = new int[nums.length];
        helper(res, nums, 0, vis);
        return res;
    }



    private void helper(List<List<Integer>> res, int[] nums, int nowInd, int[] vis) {
        if (nowInd == vis.length) {
            List<Integer> tmp = new ArrayList<>(vis.length);
            for(int i = 0; i < vis.length; i++) {
                if (vis[i] == 1) {
                    tmp.add(nums[i]);
                }
            }
            res.add(tmp);
            return;
        }
        helper(res, nums, nowInd + 1, vis);
        vis[nowInd] = 1;
        helper(res, nums, nowInd + 1, vis);
        vis[nowInd] = 0;
    }
}
