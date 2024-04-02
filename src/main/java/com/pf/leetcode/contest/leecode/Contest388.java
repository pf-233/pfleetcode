package com.pf.leetcode.contest.leecode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author：panf
 * date：3/10/2024
 * Description:
 */
public class Contest388 {

    public static void main(String[] args) {
        Contest388 contest = new Contest388();

        int[] nums;
        int k;
//        nums = new int[]{1,2,3,-1,2};
//        k =3;
        nums = new int[]{-1,-2,-3};
        k = 3;
        System.out.println(contest.maximumStrength(nums, k));

//        String[] arr;
////        arr = new String[] {"cab","ad","bad","c"};
//        arr = new String[] {"gfnt","xn","mdz","yfmr","fi","wwncn","hkdy"};  //["g","x","z","r","i","c","h"]
//        Arrays.stream(contest.shortestSubstrings(arr)).forEach(System.out::println);



//        int[] happiness;
//        int k;
//        happiness = new int[] {12,1,42};
//        k = 3;
//        System.out.println(contest.maximumHappinessSum(happiness, k));
    }

    // sum全部*k   n * 10^9 * x(就是k) <=  10^(6+9)
    // 1 <= n <= 10^4
    // 1 <= k <= n
    // 1 <= n * k <= 10^6
    // n越大k越小，   n = 1000 ， k = 1000
    long MAX = Long.MAX_VALUE;
    long[] prefix;
    long[][] dp;
    int n;
    int k;
    public long maximumStrength(int[] nums, int k) {
        n = nums.length;
        this.k = k;
        dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], MAX);
        dp[0][0] = 0;
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return dfs(n, k);
        // long ans = 0L;
        // for (int i = k; i <= n; i++) {
        //     if (dp[i][k] == MAX) continue;
        //     ans = Math.max(anx, dp[i][k]);
        // }
        // return ans;
    }

    private long dfs(int pos, int deep) {
        if (pos < deep) return Long.MIN_VALUE;
        if (deep == 0 || pos == 0) return 0;
        if (dp[pos][deep] != MAX) return dp[pos][deep];
        long max = 0L;
        // 这个不选
        max = dfs(pos - 1, deep);
        int symbol = (deep & 1) == 1 ? 1 : -1;
        int constant = k - deep + 1;
        int mul = symbol * constant;
        // 这个选
        for (int i = pos - 1; i >= deep - 1; i--) {
            long sum = prefix[pos] - prefix[i];
            long now = mul * sum;
            max = Math.max(max, dfs(i, deep - 1) + now);
        }
        return dp[pos][deep] = max;
    }

    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ans = new String[n];
        Map<String, Set> cntMap = new HashMap();
        List<String>[] subStrings = new ArrayList[n];
        Arrays.setAll(subStrings, e -> new ArrayList());
        for (int i = 0; i < n; i++) {
            int len = arr[i].length();
            for (int j = 0; j < len; j++) {
                for (int k = j + 1; k <= len; k++) {
                    String tempStr = arr[i].substring(j, k);
                    Set<Integer> sets = cntMap.getOrDefault(tempStr, new HashSet());
                    sets.add(i);
                    cntMap.put(tempStr, sets);
                    subStrings[i].add(tempStr);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            List<String> tempAns = new ArrayList();
            for (int j = 0; j < subStrings[i].size(); j++) {
                String subStr = subStrings[i].get(j);
                Set<Integer> sets = cntMap.get(subStr);
                if (sets.size() > 1) continue;
                tempAns.add(subStr);
            }
            tempAns.sort((a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));
            tempAns = tempAns.stream().sorted().collect(Collectors.toList());
            ans[i] = tempAns.size() == 0 ? "" : tempAns.get(0);
        }
        return ans;
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long ans = 0L;
        int times = 0;
        for (int i = happiness.length - 1; i >= 0 && times < k; i--) {
            ans += happiness[i] - times;
            times++;
        }
        return ans;
    }
}
