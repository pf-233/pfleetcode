package com.pf.leetcode.other;

public class FindNthDigit {
    public static void main(String[] args) {
        FindNthDigit findNthDigit = new FindNthDigit();
//        findNthDigit.findNthDigit(3);
        findNthDigit.findNthDigit(11);
        findNthDigit.findNthDigit(2147483647);
        findNthDigit.findNthDigit1(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }
    public int findNthDigit(int n) {
        long[] nums = new long[11];
        long sum = 0;
        int i = 1;
        for (; i < 11; i++) {
            nums[i] = (int) Math.pow(10, i) - 1 - sum;
            sum += nums[i];
            if (n > i * nums[i]) {
                n -= i * nums[i];
            } else {
                break;
            }
        }

        // 这个位置上开始的数字
        int start = (int) Math.pow(10, i - 1);
        // n / i 是多出来的数字
        int num = n / i + start - 1;
        int mod = n % i;
        int res = 0;
        int digit = (num / (int)(Math.pow(10, i - mod - 1))) % 10;
        return digit;
//        if (mod == 0) {
//            return num % 10;
//        } else {
//            for (int j = 0; j < i - mod; j++) {
//                res = num % 10;
//                num /= 10;
//            }
//        }
//        return res;
    }

     public int findNthDigit1(int n) {
         int d = 1, count = 9;
         while (n > (long) d * count) {
             n -= d * count;
             d++;
             count *= 10;
         }
         int index = n - 1;
         int start = (int) Math.pow(10, d - 1);
         int num = start + index / d;
         int digitIndex = index % d;
         int digit = (num / (int)(Math.pow(10, d - digitIndex - 1))) % 10;
         return digit;
     }
}
