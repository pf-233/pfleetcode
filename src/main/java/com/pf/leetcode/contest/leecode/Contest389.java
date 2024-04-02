package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author：panf
 * date：3/10/2024
 * Description:
 */
public class Contest389 {

    public static void main(String[] args) {
        Contest389 contest = new Contest389();
        System.out.println(contest.countSubstrings2("zzz",'z'));
        System.out.println(contest.countSubstrings2("abada", 'a'));
    }

    // 开始选择一个位置index变为0， 之后step2 就可以把远方的1 带到index 需要的步骤是cnt = |j - index| 中间的需要全部为0
    // 本身也是需要从离index最近的1开始收取
    // 选择step1 肯定是选择index邻接的点，然后用step2 去获取 所以需要cnt = 2步
    // 当|j - index| > 1 时先把maxChange 全部用掉 ===> [1,index,1] 2个本身然后+2 * maxchanges [1,index,0] 1个本身然后+2 * maxchanges, 然后看扩展的k个
//    public long minimumMoves(int[] nums, int k, int maxChanges) {
//        int n = nums.length;
//        long[][] prefix = new long[n][2];
//        long[][] suffix = new long[n][2];
//        prefix[0] = nums[0] == 1 ? new long[]{0L, 1L} : new long[]{0L, 0L};
//        int cnt = nums[0] == 1 ? 1 : 0;
//        int maxContinueCnt = cnt;
//        int continueCnt = cnt;
//        for (int i = 1; i < n; i++) {
//            if (nums[i] == 1) {
//                cnt++;
//                continueCnt++;
//                maxContinueCnt = Math.max(maxContinueCnt, continueCnt);
//            } else {
//                continueCnt = 0;
//            }
//            prefix[i][0] = prefix[i - 1][0] + prefix[i - 1][1];
//            prefix[i][1] = prefix[i - 1][1] + nums[i] == 1 ? 1 : 0;
//        }
//
//        suffix[n - 1] = nums[n - 1] == 1 ? new long[]{0L, 1L} : new long[]{0L, 0L};
//        for (int i = n - 2; i >= 0; i--) {
//            suffix[i][0] = suffix[i + 1][0] + suffix[i + 1][1];
//            suffix[i][1] = suffix[i + 1][1] + nums[i] == 1 ? 1 : 0;
//        }
//
//        if (maxChanges + maxContinueCnt >= k) {
//            int min = Math.min(maxContinueCnt, 3);
//            return 0L;
//        }
//
//    }

    public int minimumDeletions(String word, int k) {
        int[] cnt = new int[26];
        for (int i = 0; i < word.length(); i++) cnt[word.charAt(i) - 'a']++;

        int min = word.length();
        Arrays.sort(cnt);
        return dfs(cnt, 0, k);
    }

    private int dfs(int[] cnt, int ind, int k) {
        int len = cnt.length;
        if (ind == len || cnt[len - 1] - cnt[ind] <= k) return 0;
        // 删除全部当前最小的
        int min = dfs(cnt, ind + 1, k) + cnt[ind];
        // 保留当前最小的，后面的全部删除到当前区间为k过
        int sum = 0;
        for (int i = len - 1; i > ind; i--) {
            if (cnt[i] - cnt[ind] < k) break;
            sum += cnt[i] - k - cnt[ind];
        }
        return Math.min(sum, min);
    }

    public long countSubstrings2(String s, char c) {
        long ans = 0L;
        int n = s.length();
        int[] suffixCnt = new int[n];


        for (int i = n - 1; i >= 0; i--) {
            suffixCnt[i] = i < n - 1 ? suffixCnt[i + 1] : 0;
            if (s.charAt(i) != c) continue;
            suffixCnt[i]++;
            ans += suffixCnt[i];
        }


        return ans;
    }
    public long countSubstrings(String s, char c) {
        long ans = 0L;
        int n = s.length();
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = i > 0 ? cnt[i - 1] : 0;
            if (s.charAt(i) != c) continue;
            // i 开始到结尾位置为[i, n - 1]
            ans += n - i;
            // 以i为结尾  开始位置为[0, i]
            ans += i + 1;
            // i的位置重复算了，并且之前的以 preind 开始的重复计算了以当前i 为结尾的所以要减去之前ch出现的个数
            ans -= 1 + cnt[i];
            // 出现次数+1
            cnt[i]++;
        }
        return ans;
    }
}
