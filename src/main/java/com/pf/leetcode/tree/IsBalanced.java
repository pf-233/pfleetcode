package com.pf.leetcode.tree;

import java.util.HashMap;
import java.util.Hashtable;

public class IsBalanced {

    public static void main(String[] args) {
        IsBalanced isBalanced = new IsBalanced();
        char a= (char) Integer.MAX_VALUE;
        System.out.println((int)a);
        System.out.println(isBalanced.isBalanced(TreeNode.createPreTree(new Integer[]{1,2,2,3,3,null,null,4,4})));
    }
    public boolean isBalanced(TreeNode root) {
        boolean flag = true;
        isBalanced(root, flag);
        return flag;
    }

    private int isBalanced(TreeNode root, boolean flag) {
        if(root.left == null && root.right == null) {
            return 1;
        }
        int leftHight = root.left == null ? 0 : isBalanced(root.left, flag);
        int rightHight = root.right == null ? 0 : isBalanced(root.right, flag);
        if(Math.abs(leftHight - rightHight) > 1) {
            flag = false;
        }
        return Math.max(leftHight, rightHight) + 1;
    }
}
