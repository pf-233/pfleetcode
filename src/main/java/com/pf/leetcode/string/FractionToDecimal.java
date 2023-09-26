package com.pf.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panf
 * @date 9/26/2023 9:57 AM
 */
public class FractionToDecimal {
    public static void main(String[] args) {
        FractionToDecimal fractionToDecimal = new FractionToDecimal();
//        System.out.println(fractionToDecimal.fractionToDecimal(1, 2));
//        System.out.println(fractionToDecimal.fractionToDecimal(4, 333));
//        System.out.println(fractionToDecimal.fractionToDecimal(-50, 8));
        System.out.println(fractionToDecimal.fractionToDecimal(-1, -2147483648));
    }
    public String fractionToDecimal(int numerator, int denominator) {
        int sign = 1L * numerator * denominator < 0 ? -1 : 1;
        long numerator1 = Math.abs(numerator * 1L);
        long denominator1 = Math.abs(denominator * 1L);
        String ans = (sign == 1 ? "" : "-") + (numerator1 >= denominator1 ? numerator1 / denominator1 : 0) + "";
        numerator1 %= denominator1;
        if (numerator1 == 0) {
            return ans;
        }
        ans += ".";
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap();
        long numer = numerator1 * 10L;
        boolean mode = false;
        while (map.get(numer) == null) {
            map.put(numer, sb.length());
            if (numer < denominator1) {
                numer *= 10;
                sb.append('0');
            } else if (numer % denominator1 == 0){
                sb.append(numer / denominator1);
                mode = true;
                break;
            } else {
                sb.append(numer / denominator1);
                numer %= denominator1;
                numer *= 10;
            }
        }
        if (mode) {
            return ans + sb.toString();
        } else {
            return ans + sb.substring(0, map.get(numer)) + '(' + sb.substring(map.get(numer), sb.length()) + ')';
        }
    }
}
