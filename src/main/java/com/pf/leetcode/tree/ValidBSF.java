package com.pf.leetcode.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉搜索树
 *
 * @author pf
 * @date 2020-03-24 19:24
 **/
public class ValidBSF {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode.left=treeNode1;
        treeNode.right=treeNode2;
        isValidBST(treeNode);
    }
    public static boolean isValidBST(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        while(node!=null || !stack.empty()){
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
            if (!stack.empty()){
                node = stack.pop();
                linkedList.add(node);
                node=node.right;
            }
        }

        Iterator<TreeNode> iterator = linkedList.iterator();
        TreeNode pre = iterator.next();
        while (iterator.hasNext()){
            TreeNode after = iterator.next();
            if (pre.val<after.val){
                pre=after;
            } else {
                return false;
            }
        }
        return true;
    }

}
