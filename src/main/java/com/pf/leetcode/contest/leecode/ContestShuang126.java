package com.pf.leetcode.contest.leecode;

import java.util.*;

public class ContestShuang126 {
    public static void main(String[] args) {
        ContestShuang126 contest = new ContestShuang126();
        System.out.println(contest.minimizeStringValue("???"));
        System.out.println(contest.minimizeStringValue("a?a?"));
    }

    // ? 选择为一个数 会对sum 贡献前面出现多少次  并且对每个后面出现的同字符的和都+1 所以对后面总的贡献是ch 出现多少次
    // 那么就是对全部的贡献pre[ind] + suffix[ind] 就是除了这个之外整个字符串里面改字符出现次数
    // 设字符串已经确定cnt[26]
    // sum = cnt[i] * (cnt[i] - 1) ...[0, 25];
    public String minimizeStringValue(String s) {
        int[] cnt = new int[26];
        StringBuilder sb = new StringBuilder();
        int changeCnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch != '?') {
                cnt[ch - 'a']++;
            } else {
                changeCnt++;
            }
        }

        int[] changeCnts = new int[26];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < cnt.length; i++) {
            queue.offer(new int[]{cnt[i], i});
        }
        while (changeCnt-- > 0) {
            int[] tmp = queue.poll();
            tmp[0]++;
            changeCnts[tmp[1]]++;
            queue.offer(tmp);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '?') {
                for (int j = 0; j < changeCnts.length; j++) {
                    if (changeCnts[j] > 0) {
                        ch = (char) (j + 'a');
                        changeCnts[j]--;
                        break;
                    }
                }

            }
            sb.append(ch);
        }
        return sb.toString();
    }


    int[] nums;

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = queries.length;
        this.nums = nums;
        long[] ans = new long[n];
        boolean[] vis = new boolean[nums.length];
        long sum = 0L;
        PriorityQueue<Integer> que = new PriorityQueue();
        Map<Integer, TreeSet<Integer>> cntMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            TreeSet<Integer> indexSet = cntMap.getOrDefault(nums[i], new TreeSet<>());
            if (indexSet.size() == 0) {
                que.offer(nums[i]);
            }
            indexSet.add(i);
            cntMap.put(nums[i], indexSet);
        }

        for (int i = 0; i < n; i++) {
            int ind = queries[i][0];
            int k = queries[i][1];
            sum -= mark(vis, cntMap, que, ind);
            sum -= markKNums(vis, cntMap, que, k);
            ans[i] = sum;
        }
        return ans;
    }

    private long mark(boolean[] vis, Map<Integer, TreeSet<Integer>> cntMap, PriorityQueue<Integer> que, int ind) {
        if (vis[ind]) return 0L;
        vis[ind] = true;
        Set<Integer> indexSet = cntMap.get(nums[ind]);
        indexSet.remove(ind);
        if (indexSet.size() == 0) {
            que.remove(nums[ind]);
        }
        return nums[ind];
    }

    private long markKNums(boolean[] vis, Map<Integer, TreeSet<Integer>> cntMap, PriorityQueue<Integer> que, int k) {
        long sum = 0L;
        while (k > 0) {
            int min = que.peek();
            TreeSet<Integer> indexSet = cntMap.get(min);
            Iterator<Integer> iterator = indexSet.iterator();
            while (k > 0 && iterator.hasNext()) {
                vis[iterator.next()] = true;
                iterator.remove();
                sum += min;
                k--;
            }
            if (indexSet.isEmpty()) que.poll();
        }
        return sum;
    }
}
