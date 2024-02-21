package com.pf.leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pf
 * @date 2020-05-22 20:21
 **/
public class BuildTree {
    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
//        int[] preorder = new int[]{1,2,3};
//        int[] inorder = new int[]{2,3,1};
//        int[] inorder = new int[]{2,3,1};
//        int[] postorder = new int[]{3,2,1};
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree(preorder, inorder);
        System.out.println(treeNode);
    }

    Map<Integer, Integer> map;
    int[] preorders;
    int[] inorders;
    TreeNode root;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap();
        int len = inorder.length;
        preorders = preorder;
        inorders = inorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(0, len - 1, 0, len - 1);
    }

    private TreeNode buildTree(int leftPre, int rightPre, int leftIn, int rightIn) {
        if (leftPre >= preorders.length || leftPre > rightPre) return null;
        TreeNode now = new TreeNode(preorders[leftPre]);
        if (root == null) root = now;
        if (leftPre == rightPre) {
            return now;
        }
        int indexIn = map.get(preorders[leftPre]);
        // 中序遍历可以判断有几个node是在左子树的， 之前pre的左边索引加上数字就是新的左子树的边界
        int newPreRight = indexIn - leftIn + leftPre;
        now.left = buildTree(leftPre + 1, newPreRight, leftIn, indexIn - 1);
        //
        now.right = buildTree(newPreRight + 1, rightPre, indexIn + 1, rightIn);
        return now;
    }
//    int[] postorder;
//    int[] inorder;
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        this.inorder = inorder;
//        this.postorder = postorder;
//        int len = postorder.length;
//        return builder( 0, len - 1, 0, len - 1);
//    }
////
//    private TreeNode builder(int pstart, int pend, int istart, int iend) {
//        if (pstart > pend || istart > iend) {
//            return null;
//        }
//        TreeNode root = new TreeNode(postorder[pend]);
//        if (pstart == pend) {
//            return root;
//        }
//        int i = istart;
//        for (; i <= iend; i++) {
//            if (inorder[i] == postorder[pend]) {
//                break;
//            }
//        }
//        root.right = builder(pstart, pstart - 1, i + 1, iend);
//        return root;
//    }
//
//    public List<List<Integer>> pathSum(TreeNode root, int sum) {
//        List<List<Integer>> res = new LinkedList();
//        pathSum(root, res, new LinkedList(), sum);
//        return res;
//    }
//
//    public void pathSum(TreeNode node, List<List<Integer>> res, LinkedList<Integer> list, int val) {
//        if(node == null) {
//            return;
//        }
//        if(node.left == null && node.right == null) {
//            if(val - node.val == 0) {
//                list.add(node.val);
//                res.add(list.stream().collect(Collectors.toList()));
//                list.removeLast();
//            }
//            return;
//        }
//        list.add(node.val);
//        pathSum(node.left, res, list, val - node.val);
//        pathSum(node.right, res, list, val - node.val);
//        list.removeLast();
//    }

}
