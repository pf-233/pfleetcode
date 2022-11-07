package com.pf.leetcode.stack;

import java.util.Stack;

public class LengthLongestPath {
    int ind = 0;

    public static void main(String[] args) {
        LengthLongestPath lengthLongestPath = new LengthLongestPath();
        System.out.println(lengthLongestPath.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
    public int lengthLongestPath(String input) {
        if (input.indexOf('.') == -1) {
            return 0;
        }
        String[] dirs = input.split("\n");
        Stack<String> st = new Stack();
        int len = 0;
        int max = 0;
        for (int i = 0; i < dirs.length; i++) {
            int deep = getDeep(dirs[i]);
            while (deep < st.size()) {
                String tmp = st.pop();
                System.out.println("pop: " + tmp);
                len -= tmp.length();
            }
            dirs[i] = dirs[i].replaceAll("\t", "");
            String tmp = st.isEmpty() ? dirs[i] : "/" + dirs[i];
            System.out.println("push: " + tmp);
            st.push(tmp);
            len += tmp.length();
            if (tmp.indexOf(".") > 0 && tmp.indexOf(".") < tmp.length() - 1) {
                max = Math.max(max, len);
            }
        }
        System.out.println("max: " + max);
        return max;
    }
    private int getDeep(String input) {
        int count = 0;
        for (int i = 0; i < input.length() - 2; i++) {
            if (input.charAt(i) == '\t') {
                count++;
            } else {
                break;
            }
        }
        System.out.println("count: " + count);
        return count;
    }
}
