package com.pf.leetcode.contest.leecode;

/**
 * author：panf
 * date：11/19/2023
 * Description:
 */
public class Contest372 {

    public static void main(String[] args) {
        Contest372 contest372 = new Contest372();
        System.out.println(contest372.maximumXorProduct(12, 5, 4));
    }
    int mode = (int)1e9 + 7;
    public int maximumXorProduct(long a, long b, int n) {
        long[] digita = new long[60];
        long[] digitb = new long[60];
        for (int i = 0; i < n; i++) {
            long nowa = a & 1;
            long nowb = b & 1;
            a >>= 1;
            b >>= 1;
            // nowa != nowb 时只能一个为1一个为0 ，相等时 都可以为1
            // digit 为1 则两个都可以为1  为0则一个0一个1
            digita[i] = digitb[i] = (nowa ^ nowb) == 0 ? 1 : 0;
        }
        for (int i = n; i < 60 && (a > 0 || b > 0); i++) {
            digita[i] = a & 1;
            digitb[i] = b & 1;
            a >>= 1;
            b >>= 1;
        }
        long newa1 = 0L;
        long newb1 = 0L;
        long newa2 = 0L;
        long newb2 = 0L;
        for (int i = 0; i < 60; i++) {
            if (i < n && digita[i] == 0) {
                newa1 += 1 << i;
                newb2 += 1 << i;
            } else {
                newa1 += digita[i] << i;
                newb1 += digitb[i] << i;
                newa2 += digita[i] << i;
                newb2 += digitb[i] << i;
            }
        }
        return Math.max((int)(newa1 * newb1 % mode), (int)(newa2 * newb2 % mode) ) ;
    }
}
