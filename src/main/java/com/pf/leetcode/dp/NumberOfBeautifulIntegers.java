package com.pf.leetcode.dp;

public class NumberOfBeautifulIntegers {
    public static void main(String[] args) {
        int low;
        int high;
        int k;
        low = 5604;
        high = 42522;
        k = 19;
        NumberOfBeautifulIntegers numberOfBeautifulIntegers = new NumberOfBeautifulIntegers();
//        System.out.println(numberOfBeautifulIntegers.numberOfBeautifulIntegers(low, high, k));
        numberOfBeautifulIntegers.k = k;
        System.out.println(numberOfBeautifulIntegers.numberOfBeautifulIntegers(low));
    }
    int k;
    int[] memo;
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        return numberOfBeautifulIntegers(high) - numberOfBeautifulIntegers(low - 1);
    }

    private int numberOfBeautifulIntegers(int limit) {
        memo = new int[10];
        int num = limit;
        int top = 0;
        while (num > 0) {
            memo[top++] = num % 10;
            num /= 10;
        }
        return numeberOfBeautiful(0, 0, 0, true, top);
    }

    private int numeberOfBeautiful(int pre, int oddcount, int evencount, boolean limit, int deep) {
        int diff = Math.abs(evencount - oddcount);
        if (pre == 560) {
            System.out.println(pre);
        }
        if (limit) {
            System.out.println(limit);
        }
        // 剩下的数字不足奇偶差距
        if (diff > deep) {
            return 0;
        }
        if (deep == 0) {
            if (pre % k == 0) {
                System.out.println(pre);
            }
            return pre % k == 0 ? 1 : 0;
        }
        int high = limit ? memo[deep - 1] : 9;
        int ans = 0;
        // 如果之前就是0就不限制，并且奇偶不变    如果不是0就不限制并偶数+1
        ans += numeberOfBeautiful(pre * 10, oddcount, evencount + (pre == 0 ? 0 : 1), false, deep - 1);
        for (int i = 1; i <= high; i++) {
            if (limit) {
                System.out.println(limit);
            }
            int odd = 0;
            int even = 0;
            if ((i & 1) == 1) {
                odd++;
            } else {
                even++;
            }
            // 选了0
            ans += numeberOfBeautiful(pre * 10 + i, oddcount + odd, evencount + even, limit && i == high, deep - 1);
        }
        return ans;

    }
}
