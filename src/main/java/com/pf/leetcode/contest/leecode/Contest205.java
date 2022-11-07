package com.pf.leetcode.contest.leecode;


import java.util.*;

public class Contest205 {


    public static void main(String[] args) {
        Contest205 co = new Contest205();
//        System.out.println(co.modifyString("?zs"));
        System.out.println(co.numTriplets(new int[]{7,4}, new int[]{5,2,8,9}));
        System.out.println(
                co.numTriplets(
                new int[]{100000,10,10,10,10},
                new int[]{10,10,10,10,10}
                )
        );
    }

    public int minCost(String s, int[] cost) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < s.length(); i++) {
            int tmp = stack.peek();
            if (s.charAt(i) == s.charAt(tmp)) {
                if (cost[i] > cost[tmp]) {
                    count += cost[i];
                } else {
                    count += cost[tmp];
                    stack.pop();
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        return count;
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            List<Integer> list = map1.getOrDefault(nums1[i], new ArrayList<>());
            list.add(i);
            map1.put(nums1[i], list);
        }
        Map<Integer, List<Integer>> map2 = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            List<Integer> list = map2.getOrDefault(nums2[i], new ArrayList<>());
            list.add(i);
            map2.put(nums2[i], list);
        }

        int count = 0;
        count += trip(nums1, nums2, map2);
        count += trip(nums2, nums1, map1);
        return count;
    }

    public int trip(int[] nums1, int[] nums2, Map<Integer, List<Integer>> map) {
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            long sum = 1L * nums1[i] * nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                if (sum % nums2[j] == 0) {
                    int tmp = (int) (sum / nums2[j]);
                    List<Integer> list = map.getOrDefault(tmp, new ArrayList<>());
                    count += list.size() - midFind(list, j);
                }
            }
        }
        return count;
    }

    public int midFind(List<Integer> list, int tar) {
        int low = 0;
        int hi = list.size();
        while (low < hi) {
            int mid = (low + hi) / 2;
            if (list.get(mid) == tar) {
                return mid + 1;
            } else if (list.get(mid) < tar){
                low = mid + 1;
            } else {
                hi = mid;
            }
        }
        return low;
    }

    public String modifyString(String s) {
        StringBuilder sb = new StringBuilder();
        char pre = '*';
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (tmp != '?') {
                if (count > 0){
                    modify(sb, count, pre, tmp);
                    count = 0;
                    pre = tmp;
                }
                pre = tmp;
                sb.append(tmp);
            } else {
                count++;
            }
        }
        modify(sb, count, pre, '*');
        return sb.toString();
    }

    private void modify(StringBuilder sb, int count, char pre, char tmp) {
        char[] add = new char[2];
        int ind = 0;
        for(int j = 0; j < 26 && ind < 2; j++) {
            if ('a' + j != pre && 'a' + j != tmp) {
                add[ind++] = (char)('a' + j);
            }
        }
        while (count-- > 0) {
            sb.append(add[ind++ % 2]);
        }
    }
}
