package com.pf.leetcode.heap;

import java.util.TreeMap;

public class StockPrice {
    TreeMap<Integer, Integer> priceCounts;
    TreeMap<Integer, Integer> pair;

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
//        ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
//[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]

        stockPrice.update(1,10);
        stockPrice.update(2,5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1,3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4,2);
        System.out.println(stockPrice.minimum());
    }
    public StockPrice() {
        priceCounts = new TreeMap();
        pair = new TreeMap();
    }

    public void update(int timestamp, int price) {
        Integer beforePrice = pair.get(timestamp);
        pair.put(timestamp, price);
        if (beforePrice == null) {
            beforePrice = priceCounts.getOrDefault(price, 0);
            priceCounts.put(price, beforePrice + 1);
        } else {
            int tmp = priceCounts.get(beforePrice);
            if (tmp == 1) {
                priceCounts.remove(beforePrice);
            } else {
                priceCounts.put(beforePrice, tmp - 1);
            }
        }

    }

    public int current() {
        return pair.lastEntry().getValue();
    }

    public int maximum() {
        return pair.lastEntry().getValue();
    }

    public int minimum() {
        return pair.firstEntry().getValue();
    }
}
