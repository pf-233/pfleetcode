package com.pf.leetcode.contest.leecode;


import java.util.*;

public class Contest204 {

    public static void main(String[] args) {
        Contest204 co = new Contest204();
//        System.out.println(co.getMaxLen(new int[]{0,1,-2,-3,-4}));
//        System.out.println(co.getMaxLen(new int[]{-1,-2,-3,0,1}));
//        System.out.println(co.numOfWays(new int[]{3,1,2,5,4,6}));
        System.out.println(co.PredictTheWinner(new int[]{1,5,2}));
    }

    public boolean isNumber(String s) {
        if(s == null || s == "") {
            return false;
        }
        int i = 0;
        char tmp = s.charAt(i);
        int fucount = 0;
        while(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            i++;
        }
        if (i > 1) {
            return false;
        }
        fucount = i;
        while (i < s.length() && (s.charAt(i) >= '0' || s.charAt(i) <= '9')) {
            i++;
        }
        if (i == s.length()) {
            return true;
        }
        tmp = s.charAt(i);
//        if (tmp == 'e' || tmp == 'E') {
//
//        } else if (tmp == '.') {
//
//        } else {
//            return false;
//        }
        return false;
    }

    int[] nums;
    public boolean PredictTheWinner(int[] nums) {
        this.nums = nums;
        return pre(0, nums.length - 1, 1) >= 0;
    }

    int pre(int start, int end, int fu) {
        if (start == end) {
            return fu * nums[start];
        }
        int tmpS = pre(start + 1, end, -fu) + fu * nums[start];
        int tmpE = pre(start, end - 1, -fu) + fu * nums[end];
        if (fu == 1) {
            return Math.max(tmpS, tmpE);
        } else {
            return Math.min(tmpS, tmpE);
        }
    }

    static int mode = (int) Math.pow(10, 9) + 7;
    Map<String, Integer> visMap = null;
    public int numOfWays(int[] nums) {
        Node leftRoot = new Node(-1);
        Node rihtRoot = new Node(-1);
        Node tmpLeft = leftRoot;
        Node tmpRiht = rihtRoot;
        int root = nums[0];
        visMap = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < root) {
                tmpLeft.next = new Node(nums[i]);
                tmpLeft = tmpLeft.next;
            } else {
                tmpRiht.next = new Node(nums[i]);
                tmpRiht = tmpRiht.next;
            }
        }
        leftRoot = leftRoot.next;
        rihtRoot = rihtRoot.next;
        if (leftRoot == null || rihtRoot == null) {
            return 0;
        }
        int res = find(leftRoot.next, rihtRoot) + find(leftRoot, rihtRoot.next) - 1;
        return res % mode;
    }

    int find(Node left, Node riht) {
        if (left == null || riht == null) {
            return 1;
        }
        Integer tmp = visMap.get(left.val + "," + riht.val);
        if (tmp != null) {
            return tmp;
        }
        tmp = find(left, riht.next) + find(left.next, riht);
        tmp = tmp % mode;
        visMap.put(left.val + "," + riht.val, tmp);
        return tmp;
    }

    public static class Node {
        private int val;
        private Node left;
        private Node riht;
        private Node next;
        Node(int val) {
            this.val = val;
        }
    }

    
    public int getMaxLen(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            int fcount = 0;
            int ffirst = Integer.MAX_VALUE;
            int fend = -1;
            int tmpstart = i;
            while (i < nums.length && nums[i] != 0) {
                if (nums[i] < 0) {
                    fcount++;
                    if (ffirst == Integer.MAX_VALUE) {
                        ffirst = i;
                        fend = i;
                    } else {
                        fend = i;
                    }
                }
                i++;
            }
            if (fcount % 2 == 0) {
                max = Math.max(max, i - tmpstart);
            } else {
                int tmpmax = Math.max(i - (ffirst + 1), fend - tmpstart);
                max = Math.max(max, tmpmax);
            }
        }
        return  max;
    }
    public boolean containsPattern(int[] arr, int m, int k) {
        for(int i = 0; i < arr.length - m; i++) {
            int count = 0;
            int j = 0;
            int tmpind = i;
            while (tmpind < arr.length && j < m && arr[tmpind] == arr[i + j]) {
                tmpind++;
                j++;
                if (j == m) {
                    count++;
                }
                j %= m;
            }
            if (count >= k) {
                return true;
            }
        }
        return false;
    }


}
