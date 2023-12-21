package com.pf.leetcode.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * author：panf
 * date：2023/12/21
 * Description:
 */
public class MaximumSumOfHeights {

    public static void main(String[] args) {
        MaximumSumOfHeights maximumSumOfHeights = new MaximumSumOfHeights();

        System.out.println(maximumSumOfHeights.maximumSumOfHeights(Arrays.asList(5,3,4,1,1)));
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] left = new long[n];
        //[val, cnt, sum]
        Stack<long[]> stack = new Stack();
        for (int i = 0; i < n; i++) {
            long cnt = 1L;
            long val = maxHeights.get(i);
            while (!stack.isEmpty() && stack.peek()[0] > val) {
                long[] temp = stack.pop();
                cnt += temp[1];
            }
            long sum = val * cnt;
            sum += stack.isEmpty() ? 0 : stack.peek()[2];
            stack.push(new long[]{val, cnt, sum});
            left[i] = sum;
        }

        stack.clear();
        long max = 0L;
        for (int i = n - 1; i >= 0; i--) {
            long cnt = 1L;
            long val = maxHeights.get(i);
            while (!stack.isEmpty() && stack.peek()[0] > val) {
                long[] temp = stack.pop();
                cnt += temp[1];
            }
            long sum = val * cnt;
            sum += stack.isEmpty() ? 0 : stack.peek()[2];
            stack.push(new long[]{val, cnt, sum});
            max = Math.max(max, sum + left[i] - val);
        }
        return max;
    }
}
