package com.pf.leetcode.tree;

import java.util.Stack;

/**
 * @author pf
 * @date 2020-05-24 11:02
 **/
public class PseudoPalindromicPaths {
    public static void main(String[] args) {
        // [2,3,1,3,1,null,1]
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(1);

        root.left = n1;
        root.right = n2;

        n1.left = n3;
        n1.right = n4;

        n2.right = n5;

        System.out.println(pseudoPalindromicPaths(root));
    }

    public static int pseudoPalindromicPaths (TreeNode root) {
        if(root == null){
            return 0;
        }
        Stack<TreeNode> st = new Stack();
        st.push(root);
        int[] count = new int[10];
        count[root.val]++;
        int res = 0;
        while(!st.empty()){
            TreeNode tempRoot = st.peek();
            if(tempRoot.left != null){
                st.push(tempRoot.left);
                count[tempRoot.left.val]++;
                continue;
            }
            if (tempRoot.left == null && tempRoot.right == null){
                int jiCount = 0;
                for(int i = 1; i < 10; i++){
                    if((count[i] & 1) == 1){
                        jiCount++;
                    }
                }
                if(jiCount <= 1){
                    res++;
                }
                count[tempRoot.val]--;
                st.pop();
                //排除已经遍历完子树的节点 上一个节点是右节点或者这个节点没有右节点
                while(!st.empty() &&(st.peek().right == tempRoot || st.peek().right == null)){
                    tempRoot = st.pop();
                    count[tempRoot.val]--;
                }
            }
            if(!st.empty()){
                st.push(st.peek().right);
                count[st.peek().val]++;
            }
        }
        return res;
    }
}
