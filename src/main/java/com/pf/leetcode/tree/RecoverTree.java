package com.pf.leetcode.tree;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 恢复二叉树
 *
 * @author pf
 * @date 2020-03-25 09:59
 **/
public class RecoverTree {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        t.left=t2;
        t2.right = t1;
        recoverTree(t);
        System.out.println(t);
//        TreeNode t = new TreeNode(1);
//        TreeNode t1 = new TreeNode(2);
//        TreeNode t2 = new TreeNode(3);
//        TreeNode t3 = new TreeNode(4);
//
//        t2.left=t;
//        t2.right=t3;
//        t3.left=t1;
//        recoverTree(t2);
//        System.out.println(t2);
    }
    public static void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode node = root;
        LinkedList<TreeNode> list = new LinkedList<>();
        //中序遍历排序
        TreeNode first = null;
        TreeNode second = null;
        while(node!=null || !stack.empty()){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            if(!stack.empty()){
                node = stack.pop();
                if (list.size() > 0 && list.getLast().val > node.val){
                    if (first == null){
                        first = list.getLast();
                    } else {
                        second = node;
                        break;
                    }
                }
                list.add(node);
                node=node.right;
            }
        }

        int val = first.val;
        first.val=second.val;
        second.val=val;
    }


}
