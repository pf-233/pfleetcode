package com.pf.leetcode.stack;

public class IncreasingTriplet {
    public static void main(String[] args) {
        IncreasingTriplet increasingTriplet = new IncreasingTriplet();
        System.out.println(increasingTriplet.increasingTriplet(new int[] {1,2,1,2,1,2,1,2,1,2}));
    }
    public boolean increasingTriplet(int[] nums) {
        int[] res = new int[3];
        int top = 0;
        res[top] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (res[top] < nums[i]) {
                res[++top] = nums[i];
                if (top == 2) {
                    return true;
                }
            } else if (res[top] == nums[i]) {
                continue;
            } else {
                int tmp = top;
                while (tmp >= 0 && res[tmp] > nums[i]) {
                    tmp--;
                }
                res[++tmp] = nums[i];
            }
        }
        return false;
    }
}
