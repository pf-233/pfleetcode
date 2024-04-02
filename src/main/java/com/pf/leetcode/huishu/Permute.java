package com.pf.leetcode.huishu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author：panf
 * date：3/15/2024
 * Description:
 */
public class Permute {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        Permute permute = new Permute();
        permute.permute(nums);
        for (List<Integer> temp : permute.ans) {
            temp.stream().forEach(System.out::println);
        }
    }
    int[] nums;
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        ans = new LinkedList();
        int[] temporary = new int[nums.length];
        permute(0, temporary, 0);
        return ans;
    }

    private void permute(int memo, int[] temporary, int deep) {
        if (deep == nums.length) {
            ans.add(Arrays.stream(temporary).boxed().collect(Collectors.toList()));
            return;
        }
        int now = 1;
        for (int i = 0; i < nums.length; i++) {
            if ((now & memo) == 0) {
                temporary[deep] = nums[i];
                permute(memo | now, temporary, deep + 1);
            }
            now <<= 1;
        }
    }
}
