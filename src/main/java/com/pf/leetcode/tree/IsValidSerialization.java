package com.pf.leetcode.tree;

import java.util.Stack;

/**
 * author：panf
 * date：3/31/2024
 * Description:
 */
public class IsValidSerialization {

    public static void main(String[] args) {
        IsValidSerialization isValidSerialization = new IsValidSerialization();
        String preorder;
//        preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        preorder = "9,#,92,#,#";
        System.out.println(isValidSerialization.isValidSerialization(preorder));
    }


    // 一个叶子节点是两个连续的# 就可以返回父节点
    // 我们可以记录每一个节点是否已经遍历了左子树和右子树。通过一个cnt计数
    // 0 是都没遍历完， 1是左子树遍历了， 2是右子树遍历了。 当得到2时说明该子树已经遍历完成了
    // 我们就继续找他的节点，并将其的cnt++
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack();
        String[] strs = preorder.split(",");
        int i = 0;
        for (; i < strs.length; i++) {
            if (!strs[i].equals("#")) stack.push(0);
            else {
                while (!stack.isEmpty() && stack.peek() == 1) stack.pop();
                // 这个空节点# 没有对应的父节点 那就跳出看是否满足i == len - 1
                // 最后一个节点，可以把栈清空
                if (stack.isEmpty()) return i == strs.length - 1;
                // 不是1,又在里面其实只可能是0 可以直接push(1)
                stack.push(stack.pop() + 1);
            }
        }
        return false;
    }
}
