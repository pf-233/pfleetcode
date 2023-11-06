package com.pf.leetcode.dp;

import java.util.*;

/**
 * author：panf
 * date：10/31/2023
 * Description:
 */
public class SmallestMissingValueSubtree {

    int[] visit;
    Map<Integer, List<Integer>> tree;
    int[] ans;

    public static void main(String[] args) {
        SmallestMissingValueSubtree smallestMissingValueSubtree = new SmallestMissingValueSubtree();
        int[] parents;
        int[] nums;
//        parents = new int[]{-1,0,0,2};
//        nums = new int[]{1,2,3,4};

        parents = new int[]{-1,0,1,0,3,3};
        nums = new int[] {5,4,6,2,1,3};
        System.out.println(smallestMissingValueSubtree.smallestMissingValueSubtree(parents,  nums));
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int len = parents.length;
        ans = new int[len];
        visit = new int[100002];
        tree = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<Integer> list = tree.getOrDefault(parents[i], new LinkedList());
            list.add(i);
            tree.put(parents[i], list);
        }

        Set<Integer> set = new HashSet<>();
        smallestMissingValueSubtree(0, nums);
        return ans;
    }

    private int smallestMissingValueSubtree(int node, int[] nums) {
        List<Integer> children = tree.get(node);
        if (children == null) {
            visit[nums[node]] = 1;
            return ans[node] = nums[node] == 1 ? 2 : 1;
        }
        int max = 1;
        for (Integer child : children) {
            max = Math.max(smallestMissingValueSubtree(child, nums), max);
        }
        visit[nums[node]] = 1;
        if (max == nums[node]) {
            for (int i = max; i < visit.length; i++) {
                if (visit[i] == 0) {
                    max = i;
                    break;
                }
            }
        }
        return ans[node] = max;
    }
}
