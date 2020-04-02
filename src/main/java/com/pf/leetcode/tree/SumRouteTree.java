package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 路径总和
 *
 * @author pf
 * @date 2020-04-01 19:38
 **/
public class SumRouteTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(8);
        TreeNode treeNode3 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(13);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(2);
        TreeNode treeNode8 = new TreeNode(5);
        TreeNode treeNode9 = new TreeNode(1);

        treeNode.left=treeNode1;
        treeNode.right=treeNode2;

        treeNode1.left=treeNode3;

        treeNode2.left=treeNode4;
        treeNode2.right=treeNode5;

        treeNode3.left=treeNode6;
        treeNode3.right=treeNode7;

        treeNode5.left=treeNode8;
        treeNode5.right=treeNode9;

        List<List<Integer>> lists = pathSum(treeNode, 22);
        System.out.println("aaa");
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null){
            return new ArrayList();
        }
        List<List<Integer>>  sumList = new ArrayList<>();
        bianli(root.left, root.val, sum, sumList, ""+root.val);
        bianli(root.right, root.val, sum, sumList, ""+root.val);
        return sumList;
    }

    public static void bianli(TreeNode node, int sum, int target,List<List<Integer>>  sumList,String str){
        if(node == null){
            if(sum==target){
                sumList.add(Arrays.stream(str.split(",")).map(e->new Integer(e)).collect(Collectors.toList()));
            }
            return;
        }
        bianli(node.left, sum + node.val, target, sumList, str+","+node.val);
        bianli(node.right, sum + node.val, target, sumList, str+","+node.val);
    }
}
