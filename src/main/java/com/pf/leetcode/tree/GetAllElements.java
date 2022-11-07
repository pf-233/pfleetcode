package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GetAllElements {
    public static void main(String[] args) {
        TreeNode t1 = TreeNode.createPreTree(new Integer[]{1, null, 8});
        TreeNode t2 = TreeNode.createPreTree(new Integer[]{8, 1});
        System.out.println(new GetAllElements().getAllElements(t1, t2));
    }
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list = new LinkedList();
        Stack<TreeNode> t1 = new Stack();
        Stack<TreeNode> t2 = new Stack();
        addTree(root1, t1);
        addTree(root2, t2);

        while(t1.size() > 0 && t2.size() > 0) {
            if (t1.peek().val <= t2.peek().val) {
                TreeNode tmp = t1.pop();
                list.add(tmp.val);
                if (tmp.right != null) {
                    addTree(tmp.right, t1);
                }
            } else {
                TreeNode tmp = t2.pop();
                list.add(tmp.val);
                if (tmp.right != null) {
                    addTree(tmp.right, t2);
                }
            }
        }

        t1 = t1 == null ? t2 : t1;
        while (!t1.isEmpty()) {
            TreeNode tmp = t1.pop();
            list.add(tmp.val);
            if (tmp.right != null) {
                addTree(tmp.right, t1);
            }
        }
        return list;
    }

    private void addTree(TreeNode root, Stack<TreeNode> t) {
        while (root != null) {
            t.push(root);
            root = root.left;
        }
    }
}
