package com.pf.leetcode.sort;

import java.util.LinkedList;
import java.util.List;

public class ReorderLogFiles {
    public static void main(String[] args) {
        String[] logs = new String[] {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(new ReorderLogFiles().reorderLogFiles(logs));
    }
    public String[] reorderLogFiles(String[] logs) {
        LinkedList<String> shuList = new LinkedList();
        LinkedList<String[]> zfList = new LinkedList();
        for (int i = 0; i < logs.length; i++) {
            String[] str = logs[i].split(" ");
            if (Character.isDigit(str[1].charAt(0))) {
                shuList.add(logs[i]);
            } else {
                String[] tmp = new String[2];
                tmp[0] = str[0];
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j < str.length; j++) {
                    sb.append(str[j]);
                }
                tmp[1] = sb.toString();
                zfList.add(tmp);
            }
        }
        zfList.sort((a, b) -> a[1] == b[1] ? a[0].compareTo(b[0]) : a[1].compareTo(b[1]));

        for (int i = zfList.size(); i < logs.length; i++) {
            logs[i] = shuList.removeFirst();
        }

        for(int i = 0; !zfList.isEmpty(); i++) {
            String[] tmp = zfList.removeFirst();
            logs[i] = tmp[0] + tmp[1];
        }

        return logs;
    }
}
