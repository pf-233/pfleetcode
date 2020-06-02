package com.pf.leetcode.tree;

import java.util.Set;

/**
 * @author pf
 * @date 2020-05-22 20:21
 **/
public class BuildTree {
    public static void main(String[] args) {
//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
        int[] preorder = new int[]{1,2,3};
        int[] inorder = new int[]{2,3,1};
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree(preorder, inorder);
        System.out.println(treeNode);
    }
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0){
            return null;
        }
        this.preorder = preorder;
        this.inorder = inorder;
        TreeNode root = new TreeNode(preorder[0]);
        int i = 0;
        for(; i < inorder.length; i++){
            if(inorder[i] == preorder[0]){
                break;
            }
        }
        root.left = buildTree(1, 0, i - 1);

        root.right = buildTree(i + 1, i + 1, len - 1);
        return root;
    }

    TreeNode buildTree(int subRootIndex, int start, int end){
        System.out.println(subRootIndex + "    " + start + "    "+end);
        if(start > end || end < 0){
            return null;
        }
        TreeNode subRoot = new TreeNode(preorder[subRootIndex]);
        System.out.println(preorder[subRootIndex]);
        int i = start;
        for(; i <= end; i++){
            if(inorder[i] == preorder[subRootIndex]){
                break;
            }
        }
        subRoot.left = buildTree(subRootIndex + 1, start, i - 1);
        subRoot.right = buildTree(end, i + 1, end);
        return subRoot;
    }
}
