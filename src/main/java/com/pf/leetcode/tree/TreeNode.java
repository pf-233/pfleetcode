package com.pf.leetcode.tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 *
 * @author pf
 * @date 2020-03-24 13:18
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x) {
        val = x;
    }

    static TreeNode createPreTree(Integer[] arr) {
        int index = 0;
        TreeNode root = new TreeNode(arr[index++]);

        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            temp.left = index < arr.length && arr[index] != null ? new TreeNode(arr[index++]) : null;
            temp.right = index < arr.length && arr[index] != null ? new TreeNode(arr[index++]) : null;
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        return root;
    }
}