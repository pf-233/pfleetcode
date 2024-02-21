package com.pf.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pf
 * @date 2020-05-22 20:21
 **/
public class BuildTreePostAndInOrder {
    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        BuildTreePostAndInOrder buildTree = new BuildTreePostAndInOrder();
        TreeNode treeNode = buildTree.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    int[] inorder;
    int[] postorder;
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int inStart, int inEnd, int postStart, int postEnd) {
        if (postEnd < 0 || postStart > postEnd || postEnd >= postorder.length || postStart >= postorder.length) return null;
        TreeNode now = new TreeNode(postorder[postEnd]);
        if (postStart == postEnd) return now;

        int midInd = map.get(now.val);
        int leftCnt = midInd - inStart;
        int postLeftInd = postStart + leftCnt - 1;
        now.left = buildTree(inStart, midInd - 1, postStart, postLeftInd);
        now.right = buildTree(midInd + 1, inEnd, postLeftInd + 1, postEnd - 1);
        return now;
    }
}
