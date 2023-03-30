package com.pf.leetcode.dp;

public class FindIntegers {
    int[] arr;
    // int[][] memo;

    public static void main(String[] args) {
        FindIntegers integers = new FindIntegers();
        System.out.println(integers.findIntegers(5));
    }
    public int findIntegers(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n & 1);
            n >>= 1;
        }
        arr = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            arr[sb.length() - 1 - i] = sb.charAt(i) - '0';
        }

        return dfs(0, true, false);
    }

    private int dfs(int deep,  boolean isLimit, boolean beforeIsOne) {
        if (deep == arr.length) {
            System.out.println();
            return 1;
        }

        // 选0
        int up = isLimit ? arr[deep] : 1;
        int ans = 0;
        for (int i = 0; i <= up; i++) {
            System.out.print(i);
            ans += dfs(deep + 1, isLimit && up == i, i == 1);
        }
        return ans;
    }
}
