package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 所有树
 *
 * @author pf
 * @date 2020-03-24 17:14
 **/
public class AllTree {

    public static void main(String[] args) {

    }



    public int numTrees(int n) {
        if (n==0){
            return 1;
        }
        return  countTrees(1, n);
    }

    public static int countTrees(int start, int end){
        if (start>end){
            return 0;
        } else if (start==end){
            return 1;
        }
        int sum = 0;
        for (int i=start;i<=end;i++){
            //当i为这个子树当根节点时  左子树的个数和右子树的个数
            int countLeft = countTrees(start, i-1);
            int countRight = countTrees(i+1, end);
            //当一个为空子树的时候种类为另一个子树的数量
            if (countLeft==0 || countRight==0){
                sum+=countLeft+countRight;
            } else {
                //都不为0时就相乘
                sum+=countLeft*countRight;
            }
        }
        return sum;
    }

    public static List<TreeNode> generateTrees(int n) {
        return createTree(1,n);
    }

    public static LinkedList<TreeNode> createTree(int start, int end){
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end){
            all_trees.add(null);
            return all_trees;
        }
        //创建左子树
        for (int i=start;i<=end;i++){
            //左边的全部
            LinkedList<TreeNode> leftList = createTree(start, i-1);
            //右边的全部
            LinkedList<TreeNode> rightList = createTree(i+1, end);
            for (TreeNode l:leftList){
                for (TreeNode r:rightList){
                    TreeNode node = new TreeNode(i);
                    node.left=l;
                    node.right=r;
                    all_trees.add(node);
                }
            }
        }
        return all_trees;
    }
}
