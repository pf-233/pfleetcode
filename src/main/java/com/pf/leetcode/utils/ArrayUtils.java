package com.pf.leetcode.utils;

public class ArrayUtils {

    public static int[] parseArr(String arr) {
        arr = getMaxStr(arr);
        String[] str = arr.split(",");
        int[] res = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            res[i] = Integer.valueOf(str[i]);
        }
        return res;
    }

//    public static int[][] parseArr2(String arr) {
//        arr = getMaxStr(arr);
//
//        int[] res = new int[str.length];
//        for (int i = 0; i < str.length; i++) {
//            res[i] = Integer.valueOf(str[i]);
//        }
//        return res;
//    }

    private static String getMaxStr(String arr) {
        int start = arr.indexOf('[');
        int end = arr.lastIndexOf(']');
        return arr.substring(start + 1, end);
    }

    private static String getMStr(String arr) {
        int start = arr.indexOf('[');
        int end = arr.indexOf(']');
        return arr.substring(start + 1, end);
    }
}
