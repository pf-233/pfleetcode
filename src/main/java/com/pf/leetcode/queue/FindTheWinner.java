package com.pf.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

public class FindTheWinner {

    public static void main(String[] args) {
        System.out.println(new FindTheWinner().findTheWinner(5, 2));
    }

    public int findTheWinner(int n, int k) {
        int[] arr = new int[n + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        int start = 0;
        while (n > 1) {
            int tmp = (start + k) % n;
            if (tmp == 0) {
                n--;
                continue;
            }
            for (int i = tmp; i < n; i++) {
                arr[i] = arr[i + 1];
            }
            start = tmp;
            n--;
        }
        return arr[1];
    }
}
