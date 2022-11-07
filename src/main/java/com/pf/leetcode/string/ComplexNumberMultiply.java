package com.pf.leetcode.string;

import com.pf.leetcode.other.CompareVersion;

public class ComplexNumberMultiply {
    public static void main(String[] args) {
        ComplexNumberMultiply complexNumberMultiply = new ComplexNumberMultiply();
        System.out.println(complexNumberMultiply.complexNumberMultiply("1+-1i", "1+-1i"));
    }
    public String complexNumberMultiply(String num1, String num2) {
        int[] n1 = getNums(num1);
        int[] n2 = getNums(num2);
        int first = n1[0] * n2[0];
        int second = -1 * n1[1] * n2[1];
        int thiry = n1[0] * n2[1] + n2[0] * n1[1];
        int tmp = first + second;
        String str = "";
        str += tmp;
        str += thiry >= 0 ? ( "+" + thiry + "i") :  thiry + "i";
        return str;
    }

    int[] getNums(String str) {
        int[] tmp = new int[2];
        String[] ss = str.split("\\+");
        tmp[0] = Integer.valueOf(ss[0]);
        tmp[1] = Integer.valueOf(ss[1].substring(0, ss[1].length() - 1));
        return tmp;
    }
}
