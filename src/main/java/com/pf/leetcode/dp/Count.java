package com.pf.leetcode.dp;

import java.util.Arrays;

/**
 * author：panf
 * date：1/16/2024
 * Description:
 */
public class Count {
    int mode = (int)1e9 + 7;
    // 位数,是否限制，限制的数字和
    long[][][] memo;

    public static void main(String[] args) {
        Count count = new Count();
        String num1;
        String num2;
        int min_sum;
        int max_sum;
//        num1 = "1";
//        num2 = "5";
//        min_sum = 1;
//        max_sum = 5;
        num1 = "2991881296";
        num2 = "8678809677";
        min_sum = 46;
        max_sum = 326;
        System.out.println(count.count(num1, num2, min_sum, max_sum));
    }
    public int count(String num1, String num2, int min_sum, int max_sum) {
        int temp = digitSum(num1);
        int count = temp >= min_sum && temp <= max_sum ? 1 : 0;
        //(min_sum-1, max_sum] <= num2
        long cnt2 = (digitalDp(num2, max_sum) - digitalDp(num2, min_sum - 1) + mode) % mode;
        //(min_sum-1, max_sum] <= num1
        long cnt1 = (digitalDp(num1, max_sum) - digitalDp(num1, min_sum - 1) + mode) % mode;
        return (int)((cnt2 - cnt1 + count + mode) % mode);
    }

    private long digitalDp(String num, int max_sum) {
        int len = num.length();
        memo = new long[len][2][401];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i][0], -1);
            Arrays.fill(memo[i][1], -1);
        }
        return digitalDp(num, len - 1, max_sum, false, 1);
    }

    private long digitalDp(String num, int dig, int max_sum, boolean isNum, int needLimit){
        if (dig < 0) {
            return isNum ? 1L : 0L;
        }
        if (memo[dig][needLimit][max_sum] != -1) {
            return memo[dig][needLimit][max_sum];
        }

        int maxDig = needLimit == 1 ? num.charAt(num.length() - 1 - dig) - '0' : 9;
        long ans = 0;
        for (int i = 0; i <= maxDig; i++) {
            if (max_sum >= i) {
                ans += digitalDp(num, dig - 1, max_sum - i, isNum | i > 0, (needLimit == 1 && i == maxDig) ? 1 : 0);
                ans %= mode;
            }
        }
        return memo[dig][needLimit][max_sum] = ans;
    }

    private int digitSum(String num) {
        int ans = 0;
        for (int i = 0; i < num.length(); i++) {
            ans += num.charAt(i) - '0';
        }
        return ans;
    }
}
