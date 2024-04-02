package com.pf.leetcode.huishu;

import com.pf.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * author：panf
 * date：3/15/2024
 * Description:
 */
public class PathSum {
    public static void main(String[] args) {

    }
    int cnt;
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap();
        cnt = 0;
        map.put(0L, 1);
        pathSum(root, map, targetSum, root.val + 0L);
        return cnt;
    }

    private void pathSum(TreeNode node, Map<Long, Integer> map, int targetSum, long sum) {
        cnt += map.getOrDefault(sum - targetSum, 0);
        if (node == null) return;
        long now = 0L;
        if (node.left != null) {
            now = sum + node.left.val;
            map.put(now, map.getOrDefault(now, 0) + 1);
            pathSum(node.left, map, targetSum, now);
            map.put(now, map.getOrDefault(now, 0) - 1);
        }
        if (node.right != null) {
            now = sum + node.right.val;
            map.put(now, map.getOrDefault(now, 0) + 1);
            pathSum(node.right, map, targetSum, now);
            map.put(now, map.getOrDefault(now, 0) - 1);
        }
    }
}
