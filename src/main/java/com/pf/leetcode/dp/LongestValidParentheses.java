package com.pf.leetcode.dp;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"


输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
*/
public class LongestValidParentheses {
    public static void main(String[] args) {
        
    }
    
    /**
     * https://leetcode-cn.com/problems/longest-valid-parentheses/
     * 
     * max和当前比
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int maxans=0;        
        for (int i = 1; i < dp.length; i++) {
            if(s.charAt(i)=='('){
                continue;
            }
            //*****()的情况 dp[i-2]加上现在的两个
            if(s.charAt(i-1)=='('){
                dp[i]=(i>=2 ? dp[i-2]:0)+2; 
            } else if (i - dp[i - 1] > 0 && s.charAt(i-dp[i-1]-1)=='('){
                //****()) 的情况前面一个也是)这样前面一个肯定有dp[i-1]+2 然后 加上 i - 2(i本身和与他匹配的'(') - 上一个有效字符串的长度 之后的那个位置如果是有效字符串就加上他的长度
                dp[i]=dp[i-1] + ((i-dp[i-1]) >=2 ? dp[i-dp[i-1] -2]:0) + 2;
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

}