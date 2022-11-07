package com.pf.leetcode.stack;

import java.util.Stack;

public class MinRemoveToMakeValid {
    public static void main(String[] args) {
        System.out.println(new MinRemoveToMakeValid().minRemoveToMakeValid("lee(t(c)o)de)"));
    }
    public String minRemoveToMakeValid(String s) {
        char[] chs = s.toCharArray();
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                stack.push(i);
            } else if (chs[i] == ')') {
                if (!stack.isEmpty() && chs[stack.peek()] == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if (stack.size() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = chs.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                sb.append(chs[i]);
            } else if (stack.peek() == i) {
                stack.pop();
            } else {
                sb.append(chs[i]);
            }
        }
        return sb.reverse().toString();
    }
}
