package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.stream.Collectors;

public class Contest203 {

    public static void main(String[] args) {
        Contest203 co = new Contest203();
//        System.out.println(co.mostVisited(4, new int[]{1,3,1,2}));
//        System.out.println(co.findLatestStep(new int[]{3,5,1,2,4}, 1));
//        System.out.println(co.repeatedSubstringPattern("aba"));
        System.out.println(co.findSubsequences(new int[]{4,6,7,7}));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null) {
            return res;
        }

        List<Integer> tmp = new ArrayList();
        dfs(res, tmp, nums, 0);
        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        if(nums.length == index) {
            if(tmp.size() >= 2) {
                res.add(tmp.stream().collect(Collectors.toList()));
            }
            return;
        }
        dfs(res, tmp, nums, index + 1);
        if(tmp.size() == 0 || tmp.get(tmp.size() - 1) < nums[index]) {
            tmp.add(nums[index]);
            dfs(res, tmp, nums, index + 1);
            tmp.remove(tmp.size() - 1);
        }
        return;
    }
    public boolean repeatedSubstringPattern(String s) {
        if(s == null || s == "" || s.length() == 1) {
            return false;
        }
        String tmp = "";
        for(int i = s.length() - 1; i >= s.length() / 2 - 1; i--) {
            tmp = s.charAt(i) + tmp;
            if(s.startsWith(tmp) && s.length() % tmp.length() == 0 && com(s, tmp)) {
                return true;
            }
        }
        return false;
    }

    boolean com(String s, String now) {
        int i = 0;
        while(i < s.length() && s.charAt(i) == now.charAt(i % now.length())) {
            i++;
        }
        return i == s.length();
    }

    public int findLatestStep(int[] arr, int m) {
        if(m <= 0) {
            return m == 0 ? 0 : -1;
        }
        int n = arr.length;
        int[] root = new int[]{1,n};
        TreeSet<int[]> treeSet = new TreeSet<int[]>((a, b)->(a[0] - b[0]));
        treeSet.add(root);
        for(int i = arr.length - 1; i >= 0; i--) {
            int[] tmp = new int[]{arr[i],arr[i]};
            int[] now = treeSet.floor(tmp);
            if (now[1] - now[0] + 1 == m) {
                return i + 1;
            }
            treeSet.remove(now);
            if (arr[i] - 1 >= now[0]) {
                int[] tmp1 = new int[]{now[0], arr[i] -1};
                treeSet.add(tmp1);
                if (tmp1[1] - tmp1[0] + 1 == m) {
                    return i;
                }
            }
            if (arr[i] + 1 <= now[1]) {
                int[] tmp1 = new int[]{arr[i] + 1, now[1]};
                treeSet.add(tmp1);
                if (tmp1[1] - tmp1[0] + 1 == m) {
                    return i;
                }
            }
        }
        return -1;
    }

//     static class Seeg {
//        private int start;
//        private int end;
//        Seeg(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//    }
//    public int findLatestStep(int[] arr, int m) {
//        if(m <= 0) {
//            return m == 0 ? 0 : -1;
//        }
//        int n = arr.length;
//        int[] c = new int[n + 1];
//        int max = -2;
//        for(int i = 0; i < n; i++) {
//            update(arr[i], c);
//            if(sumRange(arr[i] - m + 1, arr[i], c) == m) {
//                max = i;
//            }
//            if (sumRange(arr[i], arr[i] + m - 1, c) == m) {
//                max = i;
//            }
//        }
//        return max + 1;
//    }
//
//    public void update(int i, int[] c) {
//        int len = c.length;
//        if(i >= len) {
//            return;
//        }
//        c[i] += 1;
//        int next = i + lowBit(i);
//        while(next < len) {
//            c[next] += 1;
//            next = next + lowBit(next);
//        }
//    }
//
//    public int sumRange(int i, int j, int[] c) {
//        int sum = 0;
//        if(i <= 0 || j >= c.length) {
//            return -1;
//        }
//        while (j > 0) {
//            sum += c[j];
//            j -= lowBit(j);
//        }
//
//        i--;
//        while(i > 0) {
//            sum -= c[i];
//            i -= lowBit(i);
//        }
//        return sum;
//    }
//
//    public int lowBit(int x) {
//        return x & (-x);
//    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int sum = 0;
        int count = piles.length / 3;
        for(int i = 0; i < count; i++) {
            sum += piles[2 * i + 1];
        }
        return sum;
    }

    public List<Integer> mostVisited(int n, int[] rounds) {
        Point[] pt = new Point[n];
        for(int i = 1; i <= n; i++) {
            pt[i - 1] = new Point(i, 0);
        }
        for(int i = 1; i < rounds.length; i++) {
            int j = rounds[i - 1] - 1;
            while(j != rounds[i] - 1) {
                pt[j].count++;
                j = (j + 1) % n;
            }
        }
        pt[rounds[rounds.length - 1] - 1].count++;
        Arrays.sort(pt, (a, b)->(b.count - a.count));
        int max = pt[0].count;
        List<Integer> list = new ArrayList();
        for(int i = 0; i < n; i++) {
            if(pt[i].count == max) {
                list.add(pt[i].no);
            } else {
                break;
            }

        }
        return list;
    }

    class Point {
        int no;
        int count;
        Point(int no, int count) {
            this.no = no;
            this.count = count;
        }
    }
}
