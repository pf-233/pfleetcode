package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 是否是另一个的子树
 *
 * @author pf
 * @date 2020-05-07 12:56
 **/
public class IsSubtree {

    public static void main(String[] args) {
//        []
//[]
        TreeNode s = TreeNode.createPreTree(new Integer[]{3,4,5,1,2});
        TreeNode t = TreeNode.createPreTree(new Integer[]{4,1,2});;
        System.out.println(isSubtree(s, t));
    }
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(s);
        TreeNode temp;
        while(!queue.isEmpty()){
            temp = queue.remove();
            if (temp.left != null){
                queue.add(temp.left );
            }
            if (temp.right != null){
                queue.add(temp.right );
            }
            if(isSubtreeDg(temp, t)){
                return true;
            }
        }
        return false;
    }

    static boolean isSubtreeDg(TreeNode s, TreeNode t){
        if(t == null && s == null){
            return true;
        } else if(t == null || s == null){
            return false;
        }
        return s.val == t.val ? isSubtreeDg(s.left, t.left) && isSubtreeDg(s.right, t.right) : false;
    }
}
