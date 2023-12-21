package com.pf.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * author：panf
 * date：11/28/2023
 * Description:
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        Arrays.stream(nextGreaterElement.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2})).forEach(System.out::println);
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int[] ans = new int[l1];
        Map<Integer, Integer> map1 = new HashMap();
        // Map<Integer, Integer> map2 = new HashMap();
        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], i);
        }
        // for (int i = 0; i < nums2.length; i++) {
        //     map2.put(nums2[i], i);
        // }
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                if (map1.get(nums2[stack.peek()]) != null) {
                    ans[map1.get(nums2[stack.peek()])] = nums2[i];
                }
                stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            if (map1.get(nums2[stack.peek()]) != null) {
                ans[(map1.get(nums2[stack.peek()]))] = -1;
            }
            stack.pop();
        }
        return ans;
    }
}
