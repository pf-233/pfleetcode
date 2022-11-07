package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 右侧视图的树
 *
 * @author pf
 * @date 2020-04-22 10:07
 **/
public class RightSideView {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.right =  t5;
        t3.right = t4;
        System.out.println(new RightSideView().rightSideView(t1));
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList();
        LinkedList<TreeNode> que = new LinkedList();
        LinkedList<TreeNode> next = new LinkedList();
        que.offer(root);
        while (!que.isEmpty()) {
            System.out.println(que.getLast());
            res.add(que.getLast().val);
            while (!que.isEmpty()) {
                TreeNode tmp =  que.removeFirst();
                if (tmp.left != null) {
                    System.out.println("tmp.left" +  tmp.left);
                    next.add(tmp.left);
                }
                if (tmp.right != null) {
                    System.out.println("tmp.right" +  tmp.right);
                    next.add(tmp.right);
                }
            }
            que = next;
            next = new LinkedList();
        }
        return res;
    }


//    public List<Integer> rightSideView(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        if (root == null){
//            return list;
//        }
//        LinkedList<TreeNode> linkedList = new LinkedList();
//        TreeNode splitNode = new TreeNode(Integer.MIN_VALUE);
//        linkedList.add(root);
//        linkedList.add(splitNode);
//
//        TreeNode node = null;
//        TreeNode pre = linkedList.getFirst();
//        while(!linkedList.isEmpty()){
//            node = linkedList.pop();
//            if(node == splitNode){
//                list.add(pre.val);
//                if (linkedList.isEmpty()){
//                    break;
//                }
//                linkedList.add(node);
//            } else {
//                if(node.left != null){
//                    linkedList.add(node.left);
//                }
//                if(node.right != null){
//                    linkedList.add(node.right);
//                }
//                pre = node;
//            }
//        }
//
//        return list;
//    }
}
