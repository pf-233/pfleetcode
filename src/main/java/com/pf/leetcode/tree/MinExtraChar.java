package com.pf.leetcode.tree;

/**
 * author：panf
 * date：1/9/2024
 * Description:
 */
public class MinExtraChar {

    public static void main(String[] args) {
        MinExtraChar minExtraChar = new MinExtraChar();
        String s;
        String[] dictionary;
        s = "leetscode";
        dictionary = new String[] {"leet","code","leetcode"};
        System.out.println(minExtraChar.minExtraChar(s, dictionary));
    }

    public int minExtraChar(String s, String[] dictionary) {
        // 以一个字符为结尾最多有多少。 枚举末尾，一次最多匹配 n次, 在逆字典树里的能到末尾就+长度和之前的值，不能就获取之前的最大值  O(n^2)
        int n = s.length();
        int[] dp = new int[n + 1];
        // 总长度L
        TreeNode root = buildTree(dictionary);
        // s的长度 n
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i], find(dp, s, i, root));
        }

        return n - dp[n];
    }

    /** 最多进行s的长度n次
     */
    private int find(int[]dp, String s, int ind, TreeNode head) {
        int temp = ind;
        while (temp >= 0 && head.next[s.charAt(temp) - 'a'] != null) {
            head = head.next[s.charAt(temp) - 'a'];
            if (head.end) {
                dp[ind + 1] = Math.max(dp[ind + 1], dp[temp] + ind - temp + 1);
            }
            temp--;
        }
        return dp[ind + 1];
    }

    private TreeNode buildTree(String[] dictionary) {
        TreeNode root = new TreeNode('a');
        for (int i = 0; i < dictionary.length; i++) {
            TreeNode head = root;
            for (int j = dictionary[i].length() - 1; j >= 0; j--) {
                char chs = dictionary[i].charAt(j);
                int ind = chs - 'a';
                if (head.next[ind] != null) {
                    head = head.next[ind];
                } else {
                    head.next[ind] = new TreeNode(chs);
                    head = head.next[ind];
                }
                if (j == 0) head.end = true;
            }
        }
        return root;
    }

    static class TreeNode {
        private char cs;
        private TreeNode[] next;
        private boolean end;

        private TreeNode(char cs) {
            this.cs = cs;
            next = new TreeNode[26];
            end = false;
        }
    }
}
