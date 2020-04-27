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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        LinkedList<TreeNode> linkedList = new LinkedList();
        TreeNode splitNode = new TreeNode(Integer.MIN_VALUE);
        linkedList.add(root);
        linkedList.add(splitNode);

        TreeNode node = null;
        TreeNode pre = linkedList.getFirst();
        while(!linkedList.isEmpty()){
            node = linkedList.pop();
            if(node == splitNode){
                list.add(pre.val);
                if (linkedList.isEmpty()){
                    break;
                }
                linkedList.add(node);
            } else {
                if(node.left != null){
                    linkedList.add(node.left);
                }
                if(node.right != null){
                    linkedList.add(node.right);
                }
                pre = node;
            }
        }

        return list;
    }
}
