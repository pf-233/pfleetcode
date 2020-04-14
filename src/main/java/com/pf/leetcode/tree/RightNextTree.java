package com.pf.leetcode.tree;

import java.util.LinkedList;

/**
 * @author pf
 * @date 2020-04-02 19:37
 **/
public class RightNextTree {

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        t.left=t2;
        t.right=t3;

        t2.left=t4;
        t2.right=t5;

        t3.left=t6;
        t3.right=t7;

        connect(t);
    }

    public static TreeNode connect(TreeNode root) {
        LinkedList<TreeNode> sk = new LinkedList();
        LinkedList<TreeNode> skChild = new LinkedList();
        sk.add(root);
        while(sk.size()>0){
            TreeNode pre = null;
            while(sk.size() > 0){
                TreeNode temp = sk.getFirst();
                sk.removeFirst();
                if(pre!=null){
                    pre.next=temp;
                }
                pre = temp;
                if(temp.left != null){
                    skChild.add(temp.left);
                }
                if(temp.right != null){
                    skChild.add(temp.right);
                }
            }



        }
        return root;
    }
}
