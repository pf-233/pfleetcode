package com.pf.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindLexSmallestString {
    public static void main(String[] args) {
        System.out.println(-1 % 7);
        System.out.println(-8 % 7);
        FindLexSmallestString fin = new FindLexSmallestString();
        System.out.println(fin.findLexSmallestString("5525", 9, 2));
    }
    public String findLexSmallestString(String s, int a, int b) {
        int len = s.length();
        int[] odd = new int[len / 2];
        int[] even = new int[len / 2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("9");
            if ((i & 1)== 1) {
                odd[i / 2] = s.charAt(i) - '0';
            } else {
                even[i / 2] = s.charAt(i) - '0';
            }
        }
        String minStr = sb.toString();

        // 只有奇数可以换数字
        Set<int[]> oddSet = new HashSet();
        oddSet.add(odd);
        add(oddSet, odd, a);
        Set<int[]> evenSet = new HashSet();
        new HashMap<>();
        evenSet.add(even);
        // 移动是奇数的才可以换偶数
        if ((b & 1) == 1) {
            add(evenSet, even, a);
        }

        for (int[] tmpOdd : oddSet) {
            for (int[] tmpEven : evenSet) {
                String tmpStr = merge(tmpEven, tmpOdd);
                minStr = tmpStr.compareTo(minStr) < 0 ? tmpStr : minStr;
                String moveStr = tmpStr;
                for (int i = 1; i < len; i++) {
                    moveStr = moveStr.substring(len - b, len) + moveStr.substring(0, len - b);
                    if (moveStr.equals(tmpStr)) {
                        break;
                    }
                    minStr = moveStr.compareTo(minStr) < 0 ? moveStr : minStr;
                }
            }
        }
        return minStr;
    }

    private String merge(int[] even, int[] odd) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < even.length; i++) {
            sb.append(even[i]).append(odd[i]);
        }
        return sb.toString();
    }

    private void add(Set<int[]> set, int[] nums, int value) {
        // 所以10 的倍数取余和原来一样
        for (int i = 1; i < 10; i++) {
            int[] tmp = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                tmp[j] = (nums[j] + value * i) % 10;
            }

            set.add(tmp);
        }
        return;
    }
}
