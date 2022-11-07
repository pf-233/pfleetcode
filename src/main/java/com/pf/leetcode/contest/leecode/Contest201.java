package com.pf.leetcode.contest.leecode;

import java.util.HashMap;
import java.util.Map;

public class Contest201 {
    public static void main(String[] args) {
        Contest201 con = new Contest201();
        //System.out.println(con.findKthBit(3,1));
//        System.out.println(con.maxNonOverlapping(new int[]{1,1,1,1,1}, 2));
        System.out.println(con.maxNonOverlapping(new int[]{0,0,0}, 0));
    }


    public int maxNonOverlapping(int[] nums, int target) {
        Map<Integer, Integer> countMap = new HashMap();
        countMap.put(0, -1);
        int maxIndex = -1;
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int tmp = sum - target;
            int preIndex = countMap.getOrDefault(tmp, -2);
            if(preIndex >= maxIndex) {
                count++;
                maxIndex = i;
            }
            countMap.put(sum, i);
        }
        return count;
    }


    public char findKthBit(int n, int k) {
        String[] sarr = new String[n];
        sarr[0] = "0";
        for(int i = 1; i < n; i++) {
            sarr[i] = sarr[i - 1] + "i" + reverseInvert(sarr[i - 1]);
            if(sarr[i].length() >= k) {
                return sarr[i].charAt(k - 1);
            } else if(sarr[i].length() == k - 1) {
                return '1';
            }
        }
        return sarr[n - 1].charAt(k - 1);
    }

    private String reverseInvert(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }
}
