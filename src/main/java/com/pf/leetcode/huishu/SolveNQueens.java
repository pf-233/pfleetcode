package com.pf.leetcode.huishu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SolveNQueens {
    List<List<String>> res;

    public static void main(String[] args) {
//        List<List<String>> res = new SolveNQueens().solveNQueens(4);
//        System.out.println("a");
        System.out.println(new SolveNQueens().getPermutation(3,3));
    }


    int count;
    int k;
    public String getPermutation(int n, int k) {
        int[] vis = new int[n + 1];
        count = 0;
        this.k = k;
        return helper(vis, "", n);
    }

    String helper(int[] vis, String pre, int deep) {
        if(count > k) {
            return null;
        }
        if(deep == 0 && count == k) {
            return pre;
        }
        for(int i = 1; i < vis.length; i++) {
            if(vis[i] == 1) {
                continue;
            }
            vis[i] = 1;
            String tmp = helper(vis, pre + i, deep - 1);
            if(tmp != null) {
                return tmp;
            }
            vis[i] = 0;
        }
        return null;
    }

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        int[] visCol = new int[n];
//        helper(n, new ArrayList<>(), visCol);
        new HashMap<>(20);
        return res;
    }

    public void helper(int deep, List<String> nowList, int[] visCol){
        int n = visCol.length;
        if (deep == 0) {
            res.add(nowList.stream().collect(Collectors.toList()));
            return;
        }
        char[] chs = new char[n];
        Arrays.fill(chs, '.');
        for (int i = 0; i < visCol.length; i++) {
            if (visCol[i] == 1) {
                continue;
            }
            if (vaild(nowList, i, n)) {
                visCol[i] = 1;
                chs[i] = 'Q';
                nowList.add(new String(chs));
                chs[i] = '.';
                helper(deep - 1, nowList, visCol);
                nowList.remove(nowList.size() - 1);
                visCol[i] = 0;
            }
        }
    }

    private boolean vaild(List<String> nowList, int col, int n) {
        int row = nowList.size();
        if (row == 2) {
            System.out.println("a");
        }
        for (int i = 0; i < row; i++) {
            String tmp = nowList.get(row - 1 - i);
            int left = col - i - 1;
            int riht = col + i + 1;
            if (left >= 0 && tmp.charAt(left) == 'Q') {
                return false;
            }
            if (riht < n && tmp.charAt(riht) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
