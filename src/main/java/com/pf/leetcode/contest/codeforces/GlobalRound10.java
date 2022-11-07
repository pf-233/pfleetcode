package com.pf.leetcode.contest.codeforces;

import java.util.Scanner;
import java.util.Stack;

public class GlobalRound10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int pre = Integer.MIN_VALUE / 2;
            int count = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(pre);
            int allMax = Integer.MIN_VALUE / 2;
            while (n-- > 0) {
                int tmp = scanner.nextInt();
                int max = 0;
                if (!stack.empty() && stack.peek() < tmp) {
                    max = Math.max(max, Math.min(allMax, tmp) - stack.pop());
                    stack.clear();
                }

                allMax = Math.max(allMax, tmp);
                count += max;
                stack.push(tmp);
            }
            while (!stack.empty()) {
                count += allMax - stack.pop();
                stack.clear();
            }
            System.out.println(count);
        }
    }
}
