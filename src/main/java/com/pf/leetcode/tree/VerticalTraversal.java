package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class VerticalTraversal {
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(9);
        TreeNode r3 = new TreeNode(20);
        TreeNode r4 = new TreeNode(15);
        TreeNode r5 = new TreeNode(7);
        r1.left = r2;
        r1.right = r3;
        r3.left = r4;
        r3.right = r5;
        VerticalTraversal verticalTraversal = new VerticalTraversal();
        verticalTraversal.verticalTraversal(r1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        LinkedList<Node> list = new LinkedList<Node>();
        dg(root, list, 0, 0);
        list.sort((a, b) -> a.col == b.col ? (a.row == b.row ? a.val - b.val : a.row - b.row) : a.col - b.col);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmpList = null;
        int preCol = -1000000;
        for(Node node : list) {
            if(preCol != node.col) {
                preCol = node.col;
                tmpList = new LinkedList<>();
                res.add(tmpList);
            }
            tmpList.add(node.val);

        }
        return res;
    }

    void dg(TreeNode root, LinkedList<Node> set, int row, int col) {
        if (root == null) {
            return;
        }
        set.add(new Node(row, col, root.val));
        dg(root.left, set, row + 1, col - 1);
        dg(root.right, set, row + 1, col + 1);
    }

   static class Node {
        private int row;
        private int col;
        private int val;
        Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }
}

