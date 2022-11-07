package com.pf.leetcode.contest;

import java.util.*;

public class Test199 {

    int count = 0;

//    public static void main(String[] args) {
//        Integer[] tr = new Integer[] {
//                7,1,4,6,null,5,3,null,null,null,null,null,2
//        };
//        TreeNode root = new TreeNode(tr[0]);
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        int i = 1;
//        while (!queue.isEmpty()) {
//            TreeNode now = queue.poll();
//            if (i == tr.length) {
//                break;
//            }
//            now.left = tr[i++] == null ? null : new TreeNode(tr[i - 1]);
//            if (i == tr.length) {
//                break;
//            }
//            now.right = tr[i++] == null ? null : new TreeNode(tr[i - 1]);
//            if (now.left != null) {
//                queue.offer(now.left);
//            }
//            if (now.right != null) {
//                queue.offer(now.right);
//            }
//        }
//        Test199 test199 = new Test199();
//        System.out.println(test199.countPairs(root, 3));
//    }

    public static void main(String[] args) {
        Test199 test199 = new Test199();
//        System.out.println(test199.getLengthOfOptimalCompression("aaabcccd", 2));
        System.out.println(test199.getLengthOfOptimalCompression("aabbaa", 2));
//        System.out.println(test199.getLengthOfOptimalCompression("aaaaaaaaaaa", 0));

    }

    public int getLengthOfOptimalCompression(String s, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char pre = s.charAt(i);
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i + 1) == pre) {
                count++;
                i++;
            }
            queue.offer(count);
            int des = count == 1 ? 1 : count < 10 ? 2 : 3;
            len += des;
        }
        int size = queue.size();
        int[] vals = new int[size];
        int[] weights = new int[size];
        int top = 0;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            int des = tmp == 1 ? 1 : tmp < 10 ? 2 : 3;
            vals[top] = des;
            weights[top++] = tmp;
        }

        int[] dp = new int[k + 1];
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = k; j >= weights[i]; j--) {
               dp[j] = Math.max(dp[j - weights[i]] + vals[i], dp[j]);
               max = Math.max(dp[j], max);
            }
        }

        return  len - max;
    }
    public int countPairs(TreeNode root, int distance) {
        dg(root, distance);
        return count;
    }

    List<Integer> dg(TreeNode node, int distance) {
        if (node == null) {
            return new ArrayList();
        }
        if (node.left == null && node.right == null) {
            List<Integer> res = new ArrayList();
            res.add(0);
            return res;
        }
        List<Integer> leftList = dg(node.left, distance);
        List<Integer> rightList = dg(node.right, distance);
        if (leftList.size() == 0 && rightList.size() == 0) {
            return new ArrayList();
        }
        List<Integer> res = new ArrayList();
        for (Integer now : leftList) {
            now += node.val;
            if (now < distance) {
                res.add(now);
            }
        }
        for (Integer now : rightList) {
            now += node.val;
            if (now < distance) {
                res.add(now);
            }
        }
        if (leftList.size() == 0 || rightList.size() == 0) {
            leftList = leftList.size() == 0 ? rightList : leftList;
            rightList = new ArrayList<>();
        }
        for (Integer now : leftList) {
            for (Integer nowr : rightList) {
                if (now + nowr <= distance + node.val) {
                    count++;
                }
            }
        }
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
