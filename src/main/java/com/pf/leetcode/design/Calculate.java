package com.pf.leetcode.design;

import java.util.LinkedList;
import java.util.Stack;

public class Calculate {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        String s = "2-4-(8+2-6+(8+4-(1)+8-10))";
        System.out.println(s.lastIndexOf(')', 0));
        System.out.println(s.lastIndexOf('0', 0));
        System.out.println(s.lastIndexOf('4', 0));
        System.out.println(calculate.calculate(s));
    }
    public int calculate(String s) {
        LinkedList<Integer> stnums = new LinkedList();
        LinkedList<Character> stoperation = new LinkedList();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                stnums.add(num);
            } else if (ch == '+' || ch == '-') {
                stoperation.add(ch);
            } else if (ch == '(') {
                stnums.add(calculate(s.substring(i + 1, s.lastIndexOf(')'))));
                i = s.lastIndexOf(')');
            }
        }
        
        return calculate(stnums, stoperation);
    }

    private int calculate(LinkedList<Integer> stnums, LinkedList<Character> stoperation) {
        if (stoperation.size() == stnums.size()) {
            char ch = stoperation.removeFirst();
            stnums.addFirst(-stnums.removeFirst());
        }
        while (stoperation.size() > 0) {
            int num1 = stnums.removeFirst();
            int num2 = stnums.removeFirst();
            char ch = stoperation.removeFirst();
            if (ch == '+') {
                stnums.addFirst(num1 + num2);
            } else {
                stnums.addFirst(num1 - num2);
            }
        }
        return stnums.removeFirst();
    }
}
