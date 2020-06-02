package com.pf.leetcode.easy;

import java.util.*;

/**
 * @author pf
 * @date 2020-05-19 12:46
 **/
public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println("http://yuntu-img.oss-cn-hangzhou.aliyuncs.com/tax/banner/d137bb815fab443c97e07146ae78beff.png".substring(57));
//        System.out.println(validPalindrome("aba"));
//        System.out.println(validPalindrome("abca"));
//        System.out.println(validPalindrome("aabca"));
//        System.out.println(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
//    aguokepatgbnvfqmgmlu
//    ulmgmqfvnbgtapekouga
//ulmgmqfvnbgtapekouga
public static boolean validPalindrome(String s) {
    int end = s.length() - 1;
    int start = 0;
    int changeCount = 0;
    return validPalindrome(s, start, end, changeCount);
}

    static boolean validPalindrome(String s, int start, int end, int count){
        if(start >= end){
            return true;
        }
        if(s.charAt(start) == s.charAt(end)){
            return validPalindrome(s, start + 1, end - 1, count);
        } else if(count > 0){
            return false;
        } else {
            return validPalindrome(s, start + 1, end, count + 1) || validPalindrome(s, start, end - 1, count + 1);
        }
    }
}
