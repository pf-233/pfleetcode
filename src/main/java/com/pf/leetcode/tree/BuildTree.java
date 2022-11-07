package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author pf
 * @date 2020-05-22 20:21
 **/
public class BuildTree {
    public static void main(String[] args) {
//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
//        int[] preorder = new int[]{1,2,3};
//        int[] inorder = new int[]{2,3,1};
        int[] inorder = new int[]{2,3,1};
        int[] postorder = new int[]{3,2,1};
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    int[] postorder;
    int[] inorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        int len = postorder.length;
        return builder( 0, len - 1, 0, len - 1);
    }

    private TreeNode builder(int pstart, int pend, int istart, int iend) {
        if (pstart > pend || istart > iend) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pend]);
        if (pstart == pend) {
            return root;
        }
        int i = istart;
        for (; i <= iend; i++) {
            if (inorder[i] == postorder[pend]) {
                break;
            }
        }
        root.right = builder(pstart, pstart - 1, i + 1, iend);
        return root;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList();
        pathSum(root, res, new LinkedList(), sum);
        return res;
    }

    public void pathSum(TreeNode node, List<List<Integer>> res, LinkedList<Integer> list, int val) {
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null) {
            if(val - node.val == 0) {
                list.add(node.val);
                res.add(list.stream().collect(Collectors.toList()));
                list.removeLast();
            }
            return;
        }
        list.add(node.val);
        pathSum(node.left, res, list, val - node.val);
        pathSum(node.right, res, list, val - node.val);
        list.removeLast();
    }



//    int[] preorder;
//    int[] inorder;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int len = preorder.length;
//        if(len == 0){
//            return null;
//        }
//        this.preorder = preorder;
//        this.inorder = inorder;
//        TreeNode root = new TreeNode(preorder[0]);
//        int i = 0;
//        for(; i < inorder.length; i++){
//            if(inorder[i] == preorder[0]){
//                break;
//            }
//        }
//        root.left = buildTree(1, 0, i - 1);
//
//        root.right = buildTree(i + 1, i + 1, len - 1);
//        return root;
//    }
//
//    TreeNode buildTree(int subRootIndex, int start, int end){
//        System.out.println(subRootIndex + "    " + start + "    "+end);
//        if(start > end || end < 0){
//            return null;
//        }
//        TreeNode subRoot = new TreeNode(preorder[subRootIndex]);
//        System.out.println(preorder[subRootIndex]);
//        int i = start;
//        for(; i <= end; i++){
//            if(inorder[i] == preorder[subRootIndex]){
//                break;
//            }
//        }
//        subRoot.left = buildTree(subRootIndex + 1, start, i - 1);
//        subRoot.right = buildTree(end, i + 1, end);
//        return subRoot;
//    }
}
