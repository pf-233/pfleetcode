package com.pf.leetcode.string;

public class Convert {

    public static void main(String[] args) {
        Convert convert = new Convert();
        System.out.println(convert.convert("PAYPALISHIRING", 3));;
    }

    public String convert(String s, int numRows) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        char[] res = new char[len];
        int top = 0;
        for (int i = 1; i <= numRows; i++) {
            top = convert(res, chs, i, numRows, top);
        }
        return new String(res);
    }

    private int convert(char[] res, char[] chs, int nowRow, int numRows, int ind) {
        // 该行下面还有几行
        int downRow = numRows - nowRow;
        // 该行上面还有几行
        int upRow = nowRow - 1;
        int nowInd = 0;
        int min = Math.min(upRow, downRow);
        int max = Math.max(upRow, downRow);
        if (min == 0) {
            while (nowInd < chs.length) {
                res[ind++] = chs[nowInd];
                nowInd += max * 2;
            }
        } else {
            while (nowInd < chs.length) {
                nowInd += min * 2;
                if (nowInd < chs.length) {
                    res[ind++] = chs[nowInd];
                }

                nowInd += max * 2;
                if (nowInd < chs.length) {
                    res[ind++] = chs[nowInd];
                }
            }
        }
        return ind;
    }
}
