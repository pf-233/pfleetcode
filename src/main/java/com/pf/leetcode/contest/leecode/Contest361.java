package com.pf.leetcode.contest.leecode;

public class Contest361 {

    public static void main(String[] args) {
        Contest361 contest361 = new Contest361();
        System.out.println(contest361.minimumOperations("2245047"));
    }

    // 00 或者 25 或者 50 或者 75 结尾
    // 或者 所有数字 = 0
    String[] str = new String[]{"00", "25", "50", "75"};
    public int minimumOperations(String num) {
        int min = num.length();
        for (String s : str) {
            min = Math.min(min, getMin(s, num));
        }
        // 变成0的判断
        int nonZeroCnt = 0;
        for (int i = 0; i < num.length(); i++) {
            nonZeroCnt += num.charAt(i) == '0' ? 0 : 1;
        }
        min = Math.min(min, nonZeroCnt);
        return min;
    }

    private int getMin(String target, String num) {
        int cnt = 0;
        int ind = 1;
        for (int i = num.length() - 1; i >= 0 && ind >= 0; i--) {
            if (num.charAt(i) == target.charAt(ind)) {
                ind--;
            } else {
                cnt++;
            }
        }
        return ind >= 0 ? num.length() : cnt;
    }
}
