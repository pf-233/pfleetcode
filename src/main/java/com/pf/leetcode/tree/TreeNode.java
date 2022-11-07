package com.pf.leetcode.tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 *
 * @author pf
 * @date 2020-03-24 13:18
 **/
public class TreeNode implements Comparable{
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode(int x) {
        val = x;
    }

    Map<TreeNode, int[]> visMap;
    public int minCameraCover(TreeNode root) {

        visMap = new HashMap<>();
        return Math.min(helper(root, false, true), helper(root, false, false));
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(2);
        r1.right = r2;
        r2.left = r3;
        System.out.println(new TreeNode(1).findMode(r1));
    }
    int len;
    public int[] findMode(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        len = 0;
        LinkedList<Integer> list = new LinkedList<>();
        list.getFirst();
        int max = 1;
        int len = 1;
        int base = list.removeFirst();
        LinkedList<Integer> res = new LinkedList<>();
        while (!list.isEmpty()) {
            while(!list.isEmpty() && base == list.getFirst()) {
                len++;
                list.removeFirst();
            }
            if (len == max) {
                res.add(base);
            } else if (len > max){
                res.clear();
                res.add(base);
                max = len;
            }
            if (list.isEmpty()) {
                break;
            }
            base = list.removeFirst();
            len = 1;
        }
        findCount(root, list, 0);
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.removeFirst();
        }
        return  arr;
    }

    int findCount(TreeNode node, List<Integer> list, int pre) {
        if(node == null) {
            return 0;
        }
        int left = findCount(node.left, list, 0);
        if (node.left != null) {
            if (node.left.val == node.val) {
                left++;
            } else {
                left = 1;
            }
        } else {
            left = 1;
        }
        if (left > len) {
            list.clear();
            len = left;
        }
        if (left == len) {
            list.add(node.val);
        }
        return findCount(node.right, list, left);
    }

    private int helper(TreeNode node, boolean cover, boolean put) {
        if (node == null) {
            return put ? (int) 1e9 : 0;
        }
        int[] arr = visMap.getOrDefault(node, new int[3]);
        int ind = cover ? 1 : 0;
        ind = put ? 2 : ind;
        if (arr[ind] != 0) {
            return arr[ind];
        }

        int lc = helper(node.left, true, false);
        int rc = helper(node.right, true, false);

        int lcp = helper(node.left, true, true);
        int rcp = helper(node.right, true,  true);

        int l = helper(node.left, false, false);
        int r = helper(node.right, false, false);

        if (put) {
            int tmp = 1 + Math.min(lc, lcp) + Math.min(rc, rcp);
            arr = visMap.getOrDefault(node, new int[3]);
            arr[2] = tmp;
            visMap.put(node, arr);
            return tmp;
        } else if (cover) {
            int tmp = Math.min(l, lcp) + Math.min(r, rcp);
           arr = visMap.getOrDefault(node, new int[3]);
            arr[1] = tmp;
            visMap.put(node, arr);
            return tmp;
        } else {
            int tmp1 = lcp + Math.min(rcp, r);
            int tmp2 = rcp + Math.min(lcp, l);
            int tmp = Math.min(tmp1, tmp2);
            arr = visMap.getOrDefault(node, new int[3]);
            arr[0] = tmp;
            visMap.put(node, arr);
            return tmp;
        }
    }

    static TreeNode createPreTree(Integer[] arr) {
        int index = 0;
        TreeNode root = new TreeNode(arr[index++]);

        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            temp.left = index < arr.length && arr[index++] != null ? new TreeNode(arr[index - 1]) : null;
            temp.right = index < arr.length && arr[index++] != null ? new TreeNode(arr[index - 1]) : null;
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        return root;
    }

    @Override
    public int compareTo(Object o) {
        TreeNode ans = (TreeNode) o;
        return this.val - ans.val;
    }
}