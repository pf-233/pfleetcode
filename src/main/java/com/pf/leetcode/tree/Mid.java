package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 *
 * @author pf
 * @date 2020-03-24 13:17
 **/
public class Mid {
    public static void main(String[] args) {
        Integer[] init = {1, null, 2, null, null, 3};
        TreeNode root = createTree(init);
        inorderTraversal(root);
    }

    public static TreeNode createTree(Integer[] init) {
        TreeNode[] treeNodes = new TreeNode[init.length];
        for (int i = 0; i < init.length; i++) {
            treeNodes[i] = init[i] == null ? null : new TreeNode(init[i]);
        }
        for (int i = 0; i < init.length / 2; i++) {
            if (treeNodes[i] == null) {
                continue;
            }
            if (2 * i + 1 < init.length) {
                treeNodes[i].left = treeNodes[2 * i + 1];
            }
            if (2 * i + 2 < init.length) {
                treeNodes[i].right = treeNodes[2 * i + 2];
            }
        }
        return init.length == 0 ? null : treeNodes[0];
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();

        // search(root,list);
        search1(root, list);
        List<Integer> list1 = new ArrayList<>();
        for (TreeNode t : list) {
            list1.add(t.val);
        }
        return list1;
    }

    //递归遍历
    public static void search(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        search(node.left, list);
        list.add(node.val);
        search(node.right, list);
    }

    //非递归遍历
    public static void search1(TreeNode root, List<TreeNode> list) {
        Stack<TreeNode> stack = new Stack();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            //节点不为空就入栈，指向左节点
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //当走到最左端叶子节点之后，把节点弹出，并指向右节点
            if (!stack.empty()) {
                node = stack.pop();
                list.add(node);
                node = node.right;
            }
        }
    }
}
