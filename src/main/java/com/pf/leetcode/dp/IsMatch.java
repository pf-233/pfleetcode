package com.pf.leetcode.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author pf
 * @date 2020-05-25 17:35
 **/
public class IsMatch {
    static char c1 = '*';
    static char c2 = '.';
    int[][] vis;
    public static void main(String[] args) {
        IsMatch mm = new IsMatch();
        System.out.println(mm.isMatch("","c*c*"));
        System.out.println(mm.isMatch("", "a*"));
        System.out.println(mm.isMatch("a","ab*"));
        System.out.println(mm.isMatch("aa", "a*"));
        System.out.println(mm.isMatch("ab", ".*"));
        System.out.println(mm.isMatch("aab", "c*a*b"));
        System.out.println(mm.isMatch("mississippi", "mis*is*p*."));


    }

//    public boolean isMatch(String s, String p) {
//        int lens = s.length();
//        p = getNewP(p);
//        int lenp = p.length();
//
//        vis = new int[lens][lenp];
//        for (int i = 0; i < lens; i++){
//            Arrays.fill(vis[i], -1);
//        }
//        return dp(s, p, 0, 0);
//    }
//
////    public boolean dp(String s, String p, int index1, int index2){
////        if (index1 == s.length() && index2 == p.length()){
////          return true;
////        } else if(index1 == s.length()){
////            return p.length() > index2 + 1 && p.charAt(index2 + 1) =='*' ? dp(s, p, index1, index2 + 2) : false;
////        } else if (index2 == p.length()){
////            return false;
////        }
////        if (vis[index1][index2] > -1){
////            return vis[index1][index2] == 1;
////        }
////
////        boolean flag = false;
////        if (p.substring(index2).startsWith(".*")){
////            //匹配一个， 匹配1个后不匹配， 不匹配
////            flag = dp(s, p, index1 + 1, index2) || dp(s, p, index1 + 1, index2 + 2) || dp(s, p, index1, index2 + 2);
////        } else if (p.length() > index2 + 1 && p.charAt(index2 + 1) == '*'){
////            char ch1 = s.charAt(index1);
////            char ch2 = p.charAt(index2);
////            flag = ch1 == ch2 ? dp(s, p, index1 + 1, index2) || dp(s, p, index1 + 1, index2 + 2) || dp(s, p, index1 , index2 + 2) : dp(s, p, index1, index2 + 2);
////        } else {
////            //都下一个
////            char ch1 = s.charAt(index1);
////            char ch2 = p.charAt(index2);
////            ch2 = ch2 == '.' ? ch1 : ch2;
////            flag = ch1 == ch2 ?  dp(s, p, index1 + 1, index2 + 1) : false;
////        }
////        vis[index1][index2] = flag ? 1 : 0;
////        return flag;
////    }

    static String getNewP(String p){
        StringBuffer sb = new StringBuffer();
        int count = 0;
        //压缩*
        for (int i = 0; i < p.length(); i++){
            char ch = p.charAt(i);
            if (c1 == ch){
                count++;
            } else {
                count = 0;
            }
            if (count < 2){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        int lens = s.length();
        p = getNewP(p);
        int lenp = p.length();

        if (lenp == 0 && lens == 0){
            return true;
        }

        boolean[][] dp = new boolean[lenp + 1][lens + 1];
        dp[0][0] = true;
//        for (int i = 1; i <= lenp; i++){
//            for (int j = 1; j <= lens; j++){
//                if (p.charAt(i - 1) == '.'){
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else if (p.charAt())
//            }
//        }

        return dp[lenp][lens];
    }


}
