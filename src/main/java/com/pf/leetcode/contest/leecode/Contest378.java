package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：1/7/2024
 * Description:
 */
public class Contest378 {
    public static void main(String[] args) {
        Contest378 contest = new Contest378();
        System.out.println(contest.minMovesToCaptureTheQueen(4,3, 3,4,2,5));
    }

    /**
     * 全部放1先， 统计可替换个数， Math.min(重复个数, half)
     * map 2 看2在1中的个数 越少的放越前面
     * 吧2的拿出来去替换1中的重复的个数。当
     * @param nums1
     * @param nums2
     * @return
     */
    public int maximumSetSize(int[] nums1, int[] nums2) {
        // 0 总的 1 nums1个数 2 nums2个数
        int n = nums1.length;
        Map<Integer, Integer> map1 = new HashMap();
        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }
        int duplicate1 = n - map1.size();
        int half = n / 2;
        // 只有1个但是被移除了的数字个数
        int removed = Math.max(0, half - duplicate1);
        Set<Integer> set2 = new HashSet();
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        int ans = Math.min(map1.size(), half);
        int k2fill = half;
        // 在nums2 不在nums1里的 都放一个进去先
        for (Integer k2 : set2) {
            if (!map1.containsKey(k2) && k2fill > 0) {
                ans++;
                k2fill--;
            }
        }
        // 如果nums2还能放
        if (k2fill != 0) {
            for (Integer k2 : set2) {
                // 在nums2 也 在nums1 的但是  nums1 如果有只有一个
                if (map1.containsKey(k2) && removed > 0 && k2fill > 0) {
                    ans++;
                    removed--;
                    k2fill--;
                }
            }
        }
        return ans;
    }
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
//         (a, b) 表示白色车的位置。
// (c, d) 表示白色象的位置。
// (e, f) 表示黑皇后的位置。
        // 不同行不同列车肯定要两次，可以绕过象
        int min = 2;
        if (a == e) {
            min = Math.min(min, 1 + (c == e ? 1 : 0));
        }
        if (b == f) {
            min = Math.min(min, 1 + (d == f ? 1 : 0));
        }
        // 在对角线上
        if (Math.abs(c - e) == Math.abs(d - f)) {
            // a b c 在一条线上，且 b比a远的时候是2
            int disa = a - e;
            int disc = c - e;
            if (Math.abs(a - e) == Math.abs(b - f) && disa * disc > 0 && Math.abs(disc) > Math.abs(disa)) {
                min = Math.min(min, 2);
            } else {
                min = Math.min(min, 1);
            }
        }
        return min;
    }
}
