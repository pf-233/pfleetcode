package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Contest356 {
    public static void main(String[] args) {
        Contest356 contest356 = new Contest356();
        System.out.println(contest356.countSteppingNumbers("1", "11"));
    }

    long[][][][] memo;
    int mode = (int)1e9 + 7;
    int[] lowa;
    int[] higha;
    public int countSteppingNumbers(String low, String high) {
        lowa = new int[105];
        higha = new int[105];
        strToArr(low, lowa);
        strToArr(high, higha);
        // 位数， 该位置的数字， limit hight 0 是 true 1 是false，limit low 0 是 true 1 是false
        memo = new long[105][10][2][2];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                for (int k = 0; k < memo[i][j].length; k++) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }
        return (int) dp(0, 0, true, true, false);
    }

    // 这一层， 上一层选了什么， 高位是否限制， 地位是否限制， 是否是数字
    private long dp(int pos, int num, boolean limitHigh, boolean limitLow, boolean isNum) {
        if (pos >= memo.length) {
            return 1;
        }
        int thisHigh = limitHigh ? 1 : 0;
        int thisLow = limitLow ? 1 : 0;
        if (memo[pos][num][thisHigh][thisLow] != -1) {
            return memo[pos][num][thisHigh][thisLow];
        }
        int[] next = isNum ?  new int[]{num - 1, num + 1} : new int[]{0,1,2,3,4,5,6,7,8,9};
        long ans = 0L;
        for (int i : next) {
            // i>9 和<0 的时候不可以 或者 高位限制但不符合 或者 低位限制但不符合  就继续
            if (i > 9 || i < 0 || (limitHigh && i > higha[pos]) || (limitLow && i < lowa[pos])) {
                continue;
            }
            // 其他都是可以选择的
            // 下一层选i
            // 当高位限制且值和高位一样的时候继续限制
            // 当低位限制且值和低位一样的时候继续限制
            // 之前是数字或者 不是数字这次不为0;
            ans += dp(pos + 1, i, limitHigh && i == higha[pos], limitLow && i == lowa[pos], isNum || i != 0);
            ans %= mode;
        }
        return memo[pos][num][thisHigh][thisLow] = ans;
    }

    private void strToArr(String num, int[] arr) {
        for (int i = num.length() - 1; i >= 0; i--) {
            arr[arr.length - num.length() + i] = num.charAt(i) - '0';
        }
    }


    public String minimumString(String a, String b, String c) {
//         String[] strs = new String[]{a, b, c};
//         Arrays.sort(strs, (a, b) -> b.length() - a.length());
//         if (strs[0].contains(strs[1]) && strs[0].contains(strs[2])) {
//             return strs[0];
//         } else if (strs[0].contains(strs[1]) || strs[0].contains(strs[2])) {
//             return strs[0].contains(strs[1]) ? minimumString(strs[0], strs[2]) : minimumString(strs[0], strs[1]);
//         } else {

//         }
        List<String> ans = new LinkedList();
        for (String str : minimumString(a, b)) {
            ans.addAll(minimumString(str, c));
        }
        for (String str : minimumString(a, c)) {
            ans.addAll(minimumString(str, b));
        }
        for (String str : minimumString(c, b)) {
            ans.addAll(minimumString(str, a));
        }
        ans.sort((x, y) -> x.compareTo(y));
        return ans.get(0);
    }

    private List<String> minimumString(String a, String b) {
        List<String> ans = new LinkedList();
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        if (a.contains(b)) {
            ans.add(a);
            return ans;
        }
        int endc = 0;
        for (int i = 1; i <= b.length(); i++) {
            if (a.endsWith(b.substring(0, i))) {
                endc = i;
            }
        }
        ans.add(a + b.substring(endc));
        int prec = b.length();
        for (int i = 1; i < b.length(); i++) {
            if (a.startsWith (b.substring(b.length() - i))) {
                prec = b.length() - i;
            }
        }
        ans.add(b.substring(0, prec) + a);
        return ans;
    }
}
