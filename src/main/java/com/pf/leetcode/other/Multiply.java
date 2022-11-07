package com.pf.leetcode.other;

public class Multiply {

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        System.out.println(multiply.multiply("123","456"));
    }
    public String multiply(String num1, String num2) {
        if(num1 == "0" || num2 == "0") {
            return num1 == "0" ? num2 : num1;
        }
        String pre = "0";
        String tmp = null;
        for(int i = 0; i < num2.length(); i++) {
            tmp = multiply(num1, num2.charAt(num2.length() - 1 - i), i);
            pre = add(tmp, pre);
            System.out.println("pre:    " + pre + "tmp : " + tmp);
        }
        return pre;
    }

    public String multiply(String num1, char now, int zero) {
        if(now == '0') {
            return "0";
        }
        int nowNum = now - '0';
        StringBuilder sb = new StringBuilder();
        int len = num1.length();
        int tmpIndex = 1;
        int inCount = 0;
        while(zero-- > 0) {
            sb.append('0');
        }
        while(tmpIndex <= len || inCount != 0) {
            int tmpa = tmpIndex <= len ? num1.charAt(len - tmpIndex) - '0' : 0;
            tmpIndex++;
            int tmpSum = tmpa * nowNum + inCount;
            sb.append(tmpSum % 10);
            inCount = tmpSum / 10;
        }
        return sb.reverse().toString();
    }
    public String add(String pre, String now) {
        if(pre == "0" || now == "0") {
            return pre == "0" ? now : pre;
        }
        StringBuilder sb = new StringBuilder();
        int lenpre = pre.length();
        int lennow = now.length();
        int tmpIndex = 1;
        int inCount = 0;
        while(tmpIndex <= lenpre || tmpIndex <= lennow || inCount != 0) {
            int tmpa = tmpIndex <= lenpre ? pre.charAt(lenpre - tmpIndex) - '0' : 0;
            int tmpb = tmpIndex <= lennow ? now.charAt(lennow - tmpIndex) - '0' : 0;
            tmpIndex++;
            int tmpSum = tmpa + tmpb + inCount;
            sb.append(tmpSum % 10);
            inCount = tmpSum / 10;
        }
        return sb.reverse().toString();
    }
}
