package com.pf.leetcode.tree;


import java.util.*;

/**
 * 恢复二叉树
 *
 * @author pf
 * @date 2020-03-25 09:59
 **/
public class RecoverTree {
    TreeNode pre;
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        t1.left=t2;
        t1.right = t;
        RecoverTree recoverTree = new RecoverTree();
        recoverTree.recoverTree(t1);
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

    public void recoverTree(TreeNode root) {
        List<TreeNode> replaceList = new ArrayList(2);
        pre = null;
        recoverTree(root, replaceList);
        TreeNode tmp1 = replaceList.get(0);
        TreeNode tmp2 = replaceList.get(1);
        int val = tmp1.val;
        tmp1.val = tmp2.val;
        tmp2.val = val;
    }

    private void recoverTree(TreeNode node, List<TreeNode> replaceList) {
        if(node == null ) {
            return;
        }
        recoverTree(node.left, replaceList);
        if(pre != null) {
            if(pre.val > node.val) {
                if(replaceList.size() == 0) {
                    replaceList.add(pre);
                    replaceList.add(node);
                } else {
                    replaceList.add(1,node);
                }
            }
        }
        pre = node;
        recoverTree(node.right, replaceList);
    }

//    public static void recoverTree(TreeNode root) {
//        Stack<TreeNode> stack = new Stack();
//        TreeNode node = root;
//        LinkedList<TreeNode> list = new LinkedList<>();
//        //中序遍历排序
//        TreeNode first = null;
//        TreeNode second = null;
//        while(node!=null || !stack.empty()){
//            while(node!=null){
//                stack.push(node);
//                node=node.left;
//            }
//            if(!stack.empty()){
//                node = stack.pop();
//                if (list.size() > 0 && list.getLast().val > node.val){
//                    if (first == null){
//                        first = list.getLast();
//                    } else {
//                        second = node;
//                        break;
//                    }
//                }
//                list.add(node);
//                node=node.right;
//            }
//        }
//
//        int val = first.val;
//        first.val=second.val;
//        second.val=val;
//    }


}
