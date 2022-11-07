package com.pf.leetcode.mid;

import java.util.Arrays;

public class FindRadius {
    public static void main(String[] args) {
        FindRadius radius = new FindRadius();
        int[] house = new int[] {
            1,2,3,4
        };
        int[] heaters = new int[] {
            1,4
        };
        System.out.println(radius.findRadius(house, heaters));
    }
    public int findRadius(int[] houses, int[] heaters) {
        int r = 0;
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++) {
            r = Math.max(r, midFind(heaters, houses[i]));
        }
        return r;
    }

    public int midFind(int[] heaters, int now) {
        int left = 0;
        int right = heaters.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (heaters[mid] == now) {
                return 0;
            } else if (heaters[mid] < now) {
                left = mid + 1;
            } else if (heaters[mid] > now) {
                right = mid;
            }
        }

        if (left == heaters.length) {
            return now - heaters[left - 1];
        } else if (left == 0) {
            return heaters[0] - now;
        } else {
            return Math.min(heaters[left] - now, now - heaters[left - 1]);
        }
    }
}
