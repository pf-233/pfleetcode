package com.pf.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pf
 * @date 2020-03-28 16:42
 **/
public class MidAndAfterToBeforeTree {
    private int[] postorder;
    private int[] inorder;
    private int posIndex;
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.indexMap = new HashMap();
        for(int i=0;i<inorder.length;i++){
            indexMap.put(inorder[i], i);
        }
        double
        posIndex=postorder.length-1;
        return build(0, inorder.length-1);
    }

    TreeNode build(int i_left, int i_right){
        if(i_left > i_right){
            return null;
        }
        TreeNode node = new TreeNode(postorder[posIndex]);
        posIndex--;
        int inorderIndex = indexMap.get(node.val);
        node.right = build(inorderIndex+1, i_right);
        node.left = build(i_left, inorderIndex-1);
        return node;
    }

    public static void main(String[] args) {
        MidAndAfterToBeforeTree a = new MidAndAfterToBeforeTree();
        TreeNode aa = a.buildTree(new int[]{9,3,15,20,7},
                new  int[]{9,15,7,20,3});
    }
}
