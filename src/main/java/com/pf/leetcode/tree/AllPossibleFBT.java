package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author：panf
 * date：4/2/2024
 * Description:
 */
public class AllPossibleFBT {

    public static void main(String[] args) {
        AllPossibleFBT allPossibleFBT = new AllPossibleFBT();
        System.out.println(allPossibleFBT.allPossibleFBT(1));
    }

    List<TreeNode>[] memo;
    int n;
    // 设编号为1...n
    // 枚举[2, n - 1] 为根节点， 因为1为根节点的话就没有左孩子，就不是完全二叉树，n同理       O(n)
    // 一个函数入参是区间返回的是子树的可能性列表。
    // 如果节点只有一个那么就是该节点为叶子节点不可能有其他结果
    // 如果子树有偶数个节点有就不可能存在返回空列表
    // 1 个父节点， 0个2个孩子节点，一开始是一个根节点。  1+1*2... 第二层中的任意个有2个节点才会有后续 1+1*2+[1,2]*2...+[1,k]*2 必然是奇数
    // 然后两个循环分别构造左子树情况 * 右子树情况 的节点并返回
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ans = new LinkedList();
        if (n % 2 == 0) return ans;

        if (n == 1) {
            ans.add(new TreeNode(0));
            return ans;
        }
        this.n = n;
        int len = n * (n + 2) + 5;
        memo = new LinkedList[len];

        for (int i = 2; i < n; i++) {
            if ((i - 1) % 2 == 0) continue;

            List<TreeNode> leftTrees = allPossibleFBT(1, i - 1);
            List<TreeNode> rightTrees = allPossibleFBT(i + 1, n);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    private List<TreeNode> allPossibleFBT(int start, int end) {
        int state = start * (this.n + 1) + end;
        if (memo[state] != null) {
            return memo[state];
        }
        List<TreeNode> ansList = new LinkedList();
        if (start == end) {
            ansList.add(new TreeNode(0));
            return memo[state] = ansList;
        }

        for (int i = start + 1; i < end; i++) {
            if ((i - start) % 2 == 0) continue;
            List<TreeNode> leftTrees = allPossibleFBT(start, i - 1);
            List<TreeNode> rightTrees = allPossibleFBT(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    ansList.add(root);
                }
            }
        }
        return memo[state] = ansList;
    }
}
