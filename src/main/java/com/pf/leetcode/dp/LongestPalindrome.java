package com.pf.leetcode.dp;

/**
 * @author pf
 * @date 2020-05-21 13:01
 **/
public class LongestPalindrome {
    public static void main(String[] args) {
//        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("aaaa"));
        System.out.println(longestPalindrome("baaaaab"));
        System.out.println(longestPalindrome("abcaaacba"));
    }
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0){
            return "";
        }
        int maxLen = 1;
        int maxIndex = 0;
        for (int i = 1; i < len; i++){
            int tempLen = getLongest(i, i, len - 1, s);
            int tempLen2 = getLongest(i, i - 1, len - 1, s);
            System.out.println("index:" + i + " tempLen:"+tempLen+"  tempLen2:"+tempLen2);
            tempLen = tempLen > tempLen2 ? tempLen : tempLen2;
            if (tempLen > maxLen){
                maxLen = tempLen;
                maxIndex = i;
                System.out.println("maxLen:"+maxLen+"   maxIndex:"+maxIndex);
            }

        }
        //maxLen 是奇数   中间的 - 长度就是开始的索引   偶数 中间的 - 长度就是开始的索引
//        int start = (maxLen & 1) == 1 ? maxIndex - (maxLen - 1) / 2 : maxIndex - maxLen / 2;
        //两者等价
        int start = maxIndex - maxLen / 2;
        return s.substring(start, start + maxLen);
    }

    static int getLongest(int left, int right, int len, String s){
        while (left >=0 && right <= len && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }
}
