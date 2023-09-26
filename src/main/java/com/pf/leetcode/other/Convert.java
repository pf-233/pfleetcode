package com.pf.leetcode.other;

public class Convert {
    public static void main(String[] args) {
        Convert convert = new Convert();
        // "PINALSIGYAHRPI"
//        System.out.println(convert.convert("PAYPALISHIRING", 4));
        System.out.println(convert.convert("AB", 1));
    }

    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows < 2) return s;
        char[] chs = s.toCharArray();
        int row = 0;
        StringBuilder sb = new StringBuilder();
        while (row < numRows) {
            int start = row;
            // 改位置到底部多少步，然后底部回来就要多少步 所以*2
            int downStep = (numRows - 1 - start) << 1;
            int upStep = start << 1;
            int ind = 0;
            int[] add = new int[]{downStep, upStep};
            while (start < s.length()) {
                sb.append(chs[start]);
                if (add[ind % 2] == 0) {
                    ind++;
                }
                start += add[ind++ % 2];
            }
            row++;
        }
        return sb.toString();
    }
}
