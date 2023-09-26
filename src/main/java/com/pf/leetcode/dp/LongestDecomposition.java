package com.pf.leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;

public class LongestDecomposition {
    int[][] memo;

    public static void main(String[] args) {
        LongestDecomposition ld = new LongestDecomposition();
        System.out.println(ld.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    public int longestDecomposition(String text) {
        int len = text.length();
        memo = new int[len][len];
        // 一个字符就是一个
        for (int i = 0; i < len; i++) {
            memo[i][i] = 1;
        }
        return longestDecomposition(text, 0, len - 1);
    }

    private int longestDecomposition(String text, int start, int end) {
        // start == end 已经初始化了
        if (memo[start][end]!= 0) {
            return memo[start][end];
        }
        // 大于是不合法的
        if (start > end) {
            return 0;
        }

        int left = start;
        int right = end;
        int max = 0;
        //[start, left] [right, end]
        while (left < right) {
            if (text.charAt(start) == text.charAt(right) && text.charAt(left) == text.charAt(end) && text.substring(start, left + 1).equals(text.substring(right, end + 1))) {
                max = Math.max(max, longestDecomposition(text, left + 1, right - 1) + 1);
            }
            left++;
            right--;
        }
        // 无论如何本身都可以作为一个
        return memo[start][end] = Math.max(max, 1);
    
    }
}
