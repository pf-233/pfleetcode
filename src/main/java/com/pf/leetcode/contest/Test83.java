package com.pf.leetcode.contest;

public class Test83 {
    public static void main(String[] args) {
        System.out.println(maskPII("1(234)567-890"));
    }
    public static String maskPII(String S) {
        String s = S;
        char first = s.charAt(0);
        boolean email = (first >= 'a' && first <= 'z') || (first >= 'A' && first <= 'Z');

        if(email) {
            int diff = 'A' - 'a';
            StringBuffer sb = new StringBuffer();

            char tmp = s.charAt(0);
            if(tmp >= 'A' && tmp <= 'Z') {
                tmp += diff;
            }
            sb.append(tmp).append("*****");
            int tmpind = 0;
            while(tmpind + 1< s.length()){
                if(s.charAt(tmpind + 1) == '@') {
                    break;
                }
                tmpind++;
            }
            for(int i = tmpind; i < s.length(); i++) {
                tmp = s.charAt(i);
                if(tmp >= 'A' && tmp <= 'Z') {
                    tmp += diff;
                }
                sb.append(tmp);
            }

            return sb.toString();

        } else {
            char[] str = new char[13];
            int top = 0;
            for(int i = 0; i < s.length(); i++) {
                char tmp = s.charAt(i);
                if(tmp >= '0' && tmp <= '9') {
                    str[top++] = tmp;
                }
            }
            StringBuffer sb = new StringBuffer();

            if(top > 10) {
                int i = 0;
                sb.append("+");
                while(top - i> 10) {
                    sb.append(str[i++]);
                }
                sb.append("-***-***-");
            }
            for(int i = top - 4; i < top; i++) {
                sb.append(i);
            }
            return sb.toString();
        }
    }
}
