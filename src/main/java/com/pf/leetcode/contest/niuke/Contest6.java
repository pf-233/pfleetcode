package com.pf.leetcode.contest.niuke;

import java.util.Arrays;

public class Contest6 {
    public static void main(String[] args) {
        Contest6 contest6 = new Contest6();
//        System.out.println(contest6.rotateRight("10110",2));;
//        System.out.println(contest6.solve(5, new int[]{2,1,1,3,2}));
//        System.out.println(contest6.numSplits("aacaba"));
//        System.out.println(contest6.numSplits("abcd"));
//        System.out.println(contest6.numSplits("aaaaa"));
//        System.out.println(contest6.numSplits("acbadbaada"));
//        System.out.println(contest6.minNumberOperations(new int[] {3,1,1,2}));
//        System.out.println(contest6.minNumberOperations(new int[] {3,1,5,4,2}));
        System.out.println(contest6.numOfSubarrays(new int[]{97,10,8,74,51,1,14,84,2,63,33,29,28}));
    }

    public int numOfSubarrays(int[] arr) {
        int mode = (int)Math.pow(10, 9) + 7;
        int count = 0;
        int tmp = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & 1) == 1) {
                count++;
            }
        }
        if(count == 0) {
            return 0;
        }
        int[] ou = new int[count + 1];
        count = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & 1) == 1) {
                ou[count++] = tmp + 1;
                tmp = 0;
            } else {
                tmp++;
            }
        }
        if (arr[arr.length - 1] % 2 == 1) {
            ou[count] = 1;
        } else {
            ou[count] = tmp + 1;
        }
        int ans = 0;
        for(int i = 0; i < count; i++) {
            int nowCount = ou[i];
            for(int j = i; j < count; j += 2) {
                ans += nowCount * ou[j + 1];
                ans %= mode;
            }
        }
        return ans;
    }
    public int minNumberOperations(int[] target) {
        int len = target.length;
        int nowMax = target[0];
        int tmpMax = 0;
        int res = 0;
        for(int i = 0; i < len; i++) {
            while (i < len && nowMax <= target[i]) {
                nowMax = target[i];
                i++;
            }
            res += nowMax - tmpMax;
            while (i < len && nowMax >= target[i]) {
                nowMax = target[i];
                i++;
            }
            tmpMax  = target[i - 1];
            i--;
        }
        return res;
    }

    public int numSplits(String s) {
        int len = s.length();
        int[] start = new int[len];
        int[] end = new int[len];
        int[] ch = new int[26];
        int count = 0;
        for (int i = 0; i < len; i++) {
            int tmp = s.charAt(i) - 'a';
            start[i] = count;
            if(ch[tmp] == 0) {
                ch[tmp]++;
                count++;
            }
        }
        Arrays.fill(ch, 0);
        count = 0;
        for (int i = len - 1; i >= 0; i--) {
            int tmp = s.charAt(i) - 'a';
            end[i] = count;
            if(ch[tmp] == 0) {
                ch[tmp]++;
                count++;
            }

        }

        int res = 0;
        for (int i = 1; i < len ; i++) {
            if(start[i] == end[i - 1]) {
                res++;
            }
        }
        return res;
    }

    public int solve (int n, int[] array) {
        // write code here
        Arrays.sort(array);
        int max = 0;
        if (n == 2) {
            return array[1] - array[0];
        }
        for (int i = n - 1; i >= 2; i--) {
            max = Math.max(array[i] - array[i - 2], max);
        }
        return max;
    }
    public long rotateRight (String str, int k) {
        // write code here
        long res = 0;
        long tmp = 1;
        for(int i = str.length() - 1 - k; i >= 0; i--) {
            res += tmp * (str.charAt(i) - '0');
            tmp <<= 1;
        }
        for (int i = str.length() - 1; i > str.length() - 1 - k; i--) {
            res += tmp * (str.charAt(i) - '0');
            tmp <<= 1;
        }
        return  res;
    }
}
