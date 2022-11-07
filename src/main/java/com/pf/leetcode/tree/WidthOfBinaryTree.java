package com.pf.leetcode.tree;

import java.util.*;

public class WidthOfBinaryTree {
    public static void main(String[] args) {
        int len = 19;
        TreeNode[] arr = new TreeNode[len];
        for(int i = 0; i < len; i++) {
            arr[i] = new TreeNode(len - i);
        }
        String string = new String();

        System.out.println(Arrays.binarySearch(arr, arr[18], (a,b)->(b.val - a.val)));
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);
//        Arrays.binarySearch()
//        treeNode.left = treeNode1;
//        treeNode.right = treeNode2;
//        treeNode1.left = treeNode3;
//        treeNode2.right = treeNode4;
//        treeNode2.right = treeNode5;

//        System.out.println(new WidthOfBinaryTree().widthOfBinaryTree(treeNode));
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int max = 1;
        Queue<TreeNode> que = new LinkedList();
        Map<TreeNode, Integer> map = new HashMap();
        que.offer(root);
        map.put(root, 0);
        TreeNode tmp = null;
        while(true) {
            Queue<TreeNode> sub = new LinkedList();
            int count = 1;
            int k = 0;
            boolean start = true;
            while(!que.isEmpty()) {
                tmp = que.poll();
                if(start) {
                    start = false;
                } else {
                    count += map.get(tmp) + 1;
                }
                TreeNode left = tmp.left;
                TreeNode right = tmp.right;
                if(left == null) {
                    k++;
                } else {
                    sub.offer(left);
                    map.put(left, k);
                    k = 0;
                }
                if(right == null) {
                    k++;
                } else {
                    sub.offer(right);
                    map.put(right, k);
                    k = 0;
                }

            }
            max = Math.max(max, count);
            if(sub.isEmpty()) {
                break;
            }
            que = sub;
        }
        return max;
    }
}
