package com.pf.leetcode.heap;

import java.util.TreeMap;

public class MyCalendar {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(47, 50);
        myCalendar.book(33, 41);
    }
    TreeMap<Integer, Integer> treemap;
    public MyCalendar() {
        treemap = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer hkey = treemap.ceilingKey(start);
        Integer lkey = treemap.floorKey(start);
        if (hkey != null && hkey >= end) {
            return false;
        }
        if (lkey != null &&  treemap.get(lkey) > start) {
            return false;
        }
        treemap.put(start, end);
        return true;
    }
}
