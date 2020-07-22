package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class GenerateTrees {

    public static void main(String[] args) {
        GenerateTrees trees = new GenerateTrees();
        System.out.println(trees.generateTrees(3));
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new LinkedList();
        if (n == 0) {
            return list;
        }
        for (int i = 1; i <= n; i++) {
            list.addAll(buildTree(i, 1, n));
        }
        return list;
    }

    List<TreeNode> buildTree(int nowVal, int left, int right) {
        List<TreeNode> res = new LinkedList();
        if (nowVal < left || nowVal > right) {
            return null;
        } else if (nowVal == left && nowVal == right) {
            TreeNode nowNode = new TreeNode(nowVal);
            res.add(nowNode);
            return res;
        }
        List<TreeNode> tmp = new LinkedList();
        List<TreeNode> tmp2 = new LinkedList();

        for (int i = left; i < nowVal; i++) {
            List<TreeNode> temp = buildTree(i, left, nowVal - 1);
            if (temp != null) {
                tmp.addAll(temp);
            }
        }
        for (int i = nowVal + 1; i <= right; i++) {
            List<TreeNode> temp = buildTree(i, nowVal + 1, right);
            if (temp != null) {
                tmp2.addAll(temp);
            }
        }

        if (tmp.size() > 0 && tmp2.size() > 0) {
            for (TreeNode leftNode : tmp) {
                for (TreeNode rightNode : tmp2) {
                    res.add(new TreeNode(nowVal, leftNode, rightNode));
                }
            }
        } else if (tmp.size() == 0) {
            for (TreeNode rightNode : tmp2) {
                res.add(new TreeNode(nowVal, null, rightNode));
            }
        } else if (tmp2.size() == 0) {
            for (TreeNode leftNode : tmp) {
                res.add(new TreeNode(nowVal, leftNode, null));
            }
        }
        return res;
    }

    class TreeNode {
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
