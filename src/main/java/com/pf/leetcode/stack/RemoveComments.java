package com.pf.leetcode.stack;

import java.util.LinkedList;
import java.util.List;

public class RemoveComments {

    public static void main(String[] args) {
        RemoveComments removeComments = new RemoveComments();
        String[] source = null;
        source = new String[] {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        for (String s : removeComments.removeComments(source)) {
            System.out.println(s);
        }
    }
    public List<String> removeComments(String[] source) {
        List<String> list = new LinkedList();
        boolean isBlock = false;
        LinkedList<Character> stack = new LinkedList();
        for (String str : source) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (isBlock) {
                    if (i + 1 < str.length() && "*/".equals(str.substring(i, i + 2))) {
                        isBlock = false;
                    }
                    continue;
                }
                if (ch == '/' && stack.size() > 0 && stack.getLast() == '/') {
                    stack.removeLast();
                    break;
                }
                if (ch == '*' && stack.size() > 0 && stack.getLast() == '/') {
                    stack.removeLast();
                    isBlock = true;
                    continue;
                }
                stack.add(ch);
            }
            if (stack.size() > 0 && !isBlock) {
                list.add(getStr(stack));
            }
        }
        return list;
    }

    private String getStr(LinkedList<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            sb.append(stack.removeFirst());
        }
        return sb.toString();
    }
}
