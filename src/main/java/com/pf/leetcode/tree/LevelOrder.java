package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pf
 * @date 2020-05-13 12:49
 **/
public class LevelOrder {

    public static void main(String[] args) {
//        TreeNode treeNode = TreeNode.createPreTree(new Integer[]{3,9,20,null,null,15,7});
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(7);
        root.left =t1;
        root.right = t2;
        t2.left =t3;
        t2.right = t4;
        System.out.println(levelOrder(root).toString());
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        List<Integer> tempList =  new ArrayList();
        LinkedList<TreeNode> nodeList = new LinkedList();
        nodeList.add(root);
        nodeList.add(null);
        TreeNode temp;
        while(nodeList.size() > 0){
            temp = nodeList.removeFirst();
            if(temp == null){
                //当list里还有其他元素的时候继续遍历
                if(nodeList.size() > 0){
                    list.add(tempList);
                    tempList = new LinkedList();
                    nodeList.addLast(null);
                    continue;
                } else {
                    //是最后一个null就不用了
                    break;
                }
            }

            tempList.add(temp.val);
            if(temp.left != null){
                nodeList.addLast(temp.left);
            }
            if(temp.right != null){
                nodeList.addLast(temp.right);
            }
        }
        return list;
    }
}
