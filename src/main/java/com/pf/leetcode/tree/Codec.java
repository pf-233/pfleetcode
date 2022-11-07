package com.pf.leetcode.tree;

import java.util.PriorityQueue;

public class Codec {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        Codec codec = new Codec();
//        String tmp = codec.serialize(t1);
//        System.out.println(tmp);
//        TreeNode root = codec.deserialize("1#3(2)(3#11(4#3(6)(7))(5))");
        codec.halveArray(new int[]{5,19,8,1});
        codec.halveArray(new int[]{3,8,20});
    }

    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> que = new PriorityQueue<Double>((a, b) -> b == a ? 0 : b > a ? 1 : -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] * 1.0;
            que.offer(nums[i] * 1.0);
        }
        int ans = 0;
        double mul = sum / 2;
        while (mul >= 0) {
            double tmp = que.poll();
            tmp /= 2;
            mul -= tmp;
            que.offer(tmp);
            ans++;
        }
        return ans;
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String strLeft = serialize(root.left);
        String strRight = serialize(root.right);
        if (strLeft == "" && strRight == "") {
            return sb.toString();
        }
        sb.append("#").append(strLeft.length() + 2);
        sb.append("(").append(strLeft).append(")");
        if (strRight != "") {
            sb.append("(").append(strRight).append(")");
        }
        System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data == "") {
            return null;
        }
        int ind = data.indexOf("#");
        if (ind == 0) {
            return null;
        } else if (ind == -1) {
            return new TreeNode(Integer.valueOf(data));
        }
        int val = Integer.valueOf(data.substring(0, ind));
        TreeNode root = new TreeNode(val);
        int indSplit = data.indexOf("(");
        int len = Integer.valueOf(data.substring(ind + 1, indSplit));
        String left = len == 2 ? "" : data.substring(indSplit + 1, indSplit + len - 1);
        String right = len == 2 ? data.substring(indSplit + 3, data.length() - 1) : data.substring(indSplit + len + 1, data.length() - 1);
        root.left = deserialize(left);
        root.right = deserialize(right);
        return root;
    }
}
