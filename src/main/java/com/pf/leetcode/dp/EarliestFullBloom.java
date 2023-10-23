package com.pf.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * author：11413
 * date：9/30/2023
 * Description:
 */
public class EarliestFullBloom {
    public static void main(String[] args) {
        EarliestFullBloom earliestFullBloom = new EarliestFullBloom();
        int[] plantTime;
        int[] growTime;
        plantTime = new int[] {1, 4, 3};
        growTime = new int[] {2, 3, 1};
//        System.out.println(earliestFullBloom.earliestFullBloom(plantTime, growTime));

        System.out.println(earliestFullBloom.calculate("3+2*2"));
    }
    Map<Character, Integer> map = new HashMap(){{
        put('-', 0);
        put('+', 0);
        put('*', 1);
        put('/', 1);
    }};

    public int calculate(String s) {
        Stack<Integer> nums = new Stack();
        Stack<Character> ops = new Stack();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;
            int num = 0;
            boolean isnum = map.get(ch) == null;
            while (i < n && ch >= '0' && ch <= '9') {
                num *= 10;
                num += ch - '0';
                if (++i < n) {
                    ch = s.charAt(i);
                }
            }
            if (isnum) {
                nums.push(num);
                i--;
            } else if (map.get(ch) != null){
                calculate(nums, ops, ch);
                ops.push(ch);
            }
        }
        calculate(nums, ops, '+');
        return nums.pop();
    }

    private void calculate(Stack<Integer> nums, Stack<Character> ops, char ch) {

        while (!ops.isEmpty() && map.get(ch) <= map.get(ops.peek())) {
            Integer num1 = nums.pop();
            Integer num2 = nums.pop();
            char temp = ops.pop();
            switch(temp) {
                case '-' : num2 -= num1; break;
                case '+' : num2 += num1; break;
                case '/' : num2 /= num1; break;
                case '*' : num2 *= num1; break;
            }
            nums.push(num2);
        }
    }
}
