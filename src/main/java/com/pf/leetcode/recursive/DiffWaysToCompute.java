package com.pf.leetcode.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * author：11413
 * date：10/9/2023
 * Description:
 */
public class DiffWaysToCompute {

    public static void main(String[] args) {
        String expression = "2*3-4*5";
        DiffWaysToCompute diffWaysToCompute = new DiffWaysToCompute();
        diffWaysToCompute.diffWaysToCompute(expression).stream().forEach(System.out::println);
    }
    List<Integer> nums;
    List<Character> chs;
    List<Integer>[][] memo;
    public List<Integer> diffWaysToCompute(String expression) {
        nums = new ArrayList();
        chs = new ArrayList();
        int tempnum = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                tempnum = tempnum * 10 + (ch - '0');
            } else {
                nums.add(tempnum);
                tempnum = 0;
                chs.add(ch);
            }
        }
        nums.add(tempnum);
        memo = new LinkedList[nums.size()][nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            memo[i][i] = new LinkedList<>();
            memo[i][i].add(nums.get(i));
        }
        return recursive(0, nums.size() - 1);
    }

    private List<Integer> recursive(int start, int end) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        if (start + 1 == end) {
            memo[start][end] = new LinkedList<>();
            memo[start][end].add(calculate(nums.get(start), nums.get(end), chs.get(start)));
            return memo[start][end];
        }
        List<Integer> ans = new LinkedList();
        for (int i = start; i < end; i++) {
            List<Integer> before = recursive(start, i);
            List<Integer> after = recursive(i + 1, end);
            for (Integer a : before) {
                for (Integer b : after) {
                    ans.add(calculate(a, b, chs.get(i)));
                }
            }
        }
        return memo[start][end] = ans;
    }

    private Integer calculate(int num1, int num2, char ch) {
        int ans = 0;
        switch (ch) {
            case '+': ans = num1 + num2; break;
            case '-': ans = num1 - num2; break;
            case '*': ans = num1 * num2; break;
        }
        return ans;
    }
}
