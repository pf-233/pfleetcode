package com.pf.leetcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author：panf
 * date：2023/12/8
 * Description:
 */
public class InputOutputUtil {

    public static void main(String[] args) {
        String s = "[[2,3,6],[8,9,8],[5,9,7],[8,9,1],[2,9,2],[9,10,6],[7,10,10],[6,7,9],[4,9,7],[2,3,1]]";
        int[][] a = get2DimensionArray(s);
        System.out.println("aa");
    }

    public static int[] getArray(String string) {
        string = string.substring(1, string.length() - 1);
        return Arrays.stream(string.split(",")).map(String::trim).mapToInt(Integer::valueOf).toArray();
    }
    public static int[][] get2DimensionArray(String string) {
        String s = string.substring(1, string.length() - 1);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int start = s.indexOf('[', i);
            int end = s.indexOf(']', i);
            list.add(getArray(s.substring(start, end + 1)));
            i = end;
        }
        return list.toArray(new int[0][]);
    }
}
