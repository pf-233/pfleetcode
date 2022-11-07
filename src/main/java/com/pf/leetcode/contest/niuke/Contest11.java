package com.pf.leetcode.contest.niuke;

import java.util.Arrays;
import java.util.Stack;

public class Contest11 {
    public static void main(String[] args) {
        Contest11 contest11 = new Contest11();
//        System.out.println(contest11.solve("00110001"));
//        System.out.println(contest11.solve("000"));
        System.out.println(contest11.solve(3,2,new Point[]{
                new Point(1,2),
                new Point(2,3)
        }));
    }

     static class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
  }
    public int solve (int n, int m, Point[] limit) {
        // write code here
        int all = (1<<(n)) - 1;
        int[] arr = new int[all + 1];
        int[] limitBit = new int[n + 1];
        Arrays.fill(limitBit, all);
        for(int i = 0; i < limit.length; i++) {
            limitBit[limit[i].x] &= (all ^ (1 << (limit[i].y - 1)));
            limitBit[limit[i].y] &= (all ^ (1 << (limit[i].x - 1)));
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            int j = 1;
            for(; j <= n; j++) {
                if((i & (1 << (j - 1))) == 0) {
                    continue;
                }
                if((i & limitBit[j]) != i) {
                    break;
                }
            }
            if(j > n) {
                count++;
                System.out.println(i);
            }
        }
        return count;
    }

    /**
     *
     * @param str string字符串 初始字符串
     * @return string字符串
     */
    public String solve (String str) {
        // write code here
        Stack<Character> stack = new Stack();
        for(int i = 0; i < str.length(); i++){
            char tmpch = str.charAt(i);

            if(stack.isEmpty()) {
                stack.push(tmpch);
            } else {
                while(!stack.isEmpty()) {
                    char top = stack.peek();
                    if(top == tmpch) {
                        stack.pop();
                        if(top == '0') {
                            tmpch = '1';
                            if(stack.isEmpty()) {
                                stack.push(tmpch);
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        stack.push(tmpch);
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
