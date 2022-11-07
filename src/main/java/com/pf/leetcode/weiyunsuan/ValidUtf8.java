package com.pf.leetcode.weiyunsuan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ValidUtf8 {

    // 11111111
    int max = (int) Math.pow(2, 8) - 1;
    // 10000000
    int next = (int) Math.pow(2, 7);
    // 11000000
    int next2 = (int) Math.pow(2, 7) + (int) Math.pow(2, 6);

    public static void main(String[] args) {
        ValidUtf8 validUtf8 = new ValidUtf8();
        System.out.println(validUtf8.validUtf8(new int[] {250,145,145,145,145}));
    }
    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            // 开头为0一个字节
            if ((data[i] & next) == 0) {
                continue;
            } else {
                int count = 0;
                while ((data[i] & next) > 0) {
                    count++;
                    data[i] = data[i] << 1;
                }
                // 10开头 和剩下的个数不够就直接false
                if (count == 1 || i + count - 1 >= data.length) {
                    return false;
                }
                for (int j = 1; j < count; j++) {
                    if ((data[i + j] & next2) != next) {
                        return false;
                    }
                }
                i += count - 1;
            }
        }
        return true;
    }

}
