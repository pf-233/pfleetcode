package com.pf.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CheckPalindromeFormation {
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,6,8};
        int k = 2;
        Map<Integer, TreeMap<Integer, Integer>> groups = new HashMap<Integer, TreeMap<Integer, Integer>>();
        for (int x : nums)
            groups.computeIfAbsent((x % k), key -> new TreeMap<>()).merge(x, 1, Integer::sum);

        CheckPalindromeFormation cp = new CheckPalindromeFormation();
        String a = "abdef";
        String b = "fecab";  
        a = "abda";
        b = "acmc";
        System.out.println(cp.checkPalindromeFormation(a, b));
    }
    public boolean checkPalindromeFormation(String a, String b) {
        // 任一是回文就是回文
        if (isPalindrome(a) || isPalindrome(b)) {
            return true;
        }

        return isPalindrome(a, b) || isPalindrome(b, a);
    }

    private boolean isPalindrome(String s1, String s2) {
        String revs2 = new StringBuilder(s2).reverse().toString();
        int lensame = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != revs2.charAt(i)) {
                break;
            }
            lensame++;
        }
        // lensame - 1 >= len - lensame - 1   ===> 2 * lensame >= len;
        return (2 * lensame >= len) || isPalindrome(s1.substring(lensame, len - lensame - 1)) || isPalindrome(s2.substring(lensame, len - lensame - 1));
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}
