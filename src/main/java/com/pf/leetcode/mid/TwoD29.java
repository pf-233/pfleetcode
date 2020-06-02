package com.pf.leetcode.mid;

/**
 * @author pf
 * @date 2020-04-28 16:50
 **/
public class TwoD29 {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-2147483648));
        System.out.println(divide(2147483647, 3));
        System.out.println(divide(10, 11));
        System.out.println(divide(10, 3));
        System.out.println(divide(7, -3));
        System.out.println(divide(-2147483648,1));
        System.out.println(divide(-2147483648,2));
    }
    public static int divide(int dividend, int divisor) {
        int min = Integer.MIN_VALUE;
        if (dividend == min && divisor == -1){
            return Integer.MAX_VALUE;
        }
        if (dividend == 0){
            return  0;
        }
        boolean isComm = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        int top = 1;
        int[] nums = new int[33];
        int[] times = new int[33];
        nums[top] = divisor;
        while(top <= 31 && nums[top] > dividend && Integer.MIN_VALUE - nums[top] < nums[top]){
            nums[top + 1] = nums[top] + nums[top];
            top++;
        }
        if (nums[top] < dividend){
            top--;
        }
        int count = 1;
        times[count] = -1;
        while (count < top){
            times[count + 1] = times[count] + times[count];
            count++;
        }

        count = 0;
        while (top > 0 && dividend < 0){
            if (dividend - nums[top] <= 0){
                count+= times[top];
                dividend-= nums[top];
            } else {
                top--;
            }
        }
        return isComm ? -count : count;
    }
}
