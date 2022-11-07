package com.pf.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;

public class IsEvenOddTree {
    public static void main(String[] args) {
        IsEvenOddTree isEvenOddTree = new IsEvenOddTree();
        TreeNode treeNode = TreeNode.createPreTree(new Integer[]{5,4,2,3,3,7});
        System.out.println(isEvenOddTree.isEvenOddTree(treeNode));
    }
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null || root.val % 2 == 0) {
            return false;
        }
        LinkedList<TreeNode>[] list = new LinkedList[2];
        list[0]  = new LinkedList<>();
        list[1]  = new LinkedList<>();
        LinkedList<TreeNode> tmp = null;
        list[0].add(root);
        int ind = 0;
        while (!list[ind].isEmpty()) {
            int nextInd = (ind + 1) % 2;
            tmp = list[nextInd];
            tmp.clear();
            int pre = ind % 2 == 0 ? -1 : Integer.MAX_VALUE;
            while (!list[ind].isEmpty()) {
                TreeNode nowNode = list[ind].removeFirst();
                if (ind % 2 == 0) {
                    if (nowNode.val <= pre || nowNode.val % 2 == 0) {
                        return false;
                    }
                } else {
                    if (nowNode.val >= pre || nowNode.val % 2 == 1) {
                        return false;
                    }
                }
                pre = nowNode.val;
                if (nowNode.left != null) {
                    tmp.addLast(nowNode.left);
                }
                if (nowNode.right != null) {
                    tmp.addLast(nowNode.right);
                }
            }
            ind = nextInd;
        }
        return true;
    }
}
