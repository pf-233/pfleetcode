package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：11413
 * date：10/14/2023
 * Description:
 */
public class ContestShuang115 {

    public static void main(String[] args) {
        ContestShuang115 contestShuang115 = new ContestShuang115();
//        int n;
//        String[] words;
//        int[] groups;
//        n = 3;
//        words = new String[]{"bab","dab","cab"};
//        groups = new int[]{1,2,2};
//        for (String s : contestShuang115.getWordsInLongestSubsequence2(n, words, groups)) {
//            System.out.println(s);
//        }

        List<Integer> nums;
        int l;
        int r;

    }


    int mode = (int)1e9 + 7;
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] arr = new int[n];
        int top = 0;
        for (Integer num : nums) {
            arr[top++] = num;
        }
        Arrays.sort(arr);

        long[] memo = new long[20006];
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            int val = arr[i];
            while (i < n && arr[i] == val) {
                i++;
                cnt++;
            }
            Set<Integer> newVal = new HashSet();
            int add = 0;
            for (int j = 1; j <= cnt; j++) {
                add += val;
                for (Integer num : set) {
                    int next = num + add;
                    if (num <= r) {
                        newVal.add(next);
                        memo[next] += memo[num];
                        memo[next] %= mode;
                    }
                }
            }
            set.addAll(newVal);
        }
        long ans = 0L;
        for (int i = l; i <= r; i++) {
            ans += memo[i];
            ans %= mode;
        }
        return (int)ans;
    }


    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        List<String>[] ans = new ArrayList[2];
        ans[0] = new ArrayList<>();
        ans[1] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (groups[i] == 0) {
                if (ans[0].size() < ans[1].size() + 1) {
                    ans[0].clear();
                    ans[0].addAll(ans[1]);
                    ans[0].add(words[i]);
                }
            } else {
                if (ans[1].size() < ans[0].size() + 1) {
                    ans[1].clear();
                    ans[1].addAll(ans[0]);
                    ans[1].add(words[i]);
                }
            }
        }
        return ans[0].size() >= ans[1].size() ? ans[0] : ans[1];
    }


    public List<String> getWordsInLongestSubsequence2(int n, String[] words, int[] groups) {
        List<String>[] ans = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList();
            int next = i;
            for (int j = 0; j < i; j++) {
                if (ans[next].size() <= ans[j].size() && canChoose(words, groups, i, j)) {
                    next = j;
                }
            }
            ans[i].addAll(ans[next]);
            ans[i].add(words[i]);
        }

        int ind = 0;
        for (int i = 1; i < n; i++) {
            if (ans[i].size() > ans[ind].size()) {
                ind = i;
            }
        }
        return ans[ind];
    }

    private boolean canChoose(String[] words, int[] groups, int i1, int i2) {
        if (words[i1].length() != words[i2].length() || groups[i1] == groups[i2]) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < words[i1].length(); i++) {
            cnt += words[i1].charAt(i) != words[i2].charAt(i) ? 1 : 0;
        }
        return cnt == 1;
    }
}