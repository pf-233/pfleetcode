package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ContestShuang119 {
    public static void main(String[] args) {
        ContestShuang119 shuang119 = new ContestShuang119();
        int[] nums = new int[] {1,2,3,1,2,3,1,2};
        int k = 2;
        System.out.println(shuang119.maxSubarrayLength(nums, k));
    }

    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int left = 0;
        int right = 0;
        int len = nums.length;
        int max = 0;
        while (right < len && left <= right) {
            int now = map.getOrDefault(nums[right], 0);
            if (now < k) {
                max = Math.max(max, right - left + 1);
                map.put(nums[right], now + 1);
            } else {
                while (nums[left] != nums[right]) {
                    map.put(nums[left], map.get(nums[left]) - 1);
                    left++;
                }
                map.put(nums[left++], k);
            }
            right++;
        }
        return max;
    }

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] distance = new int[n][n];
        Arrays.stream(distance).forEach(arr -> Arrays.fill(arr, 1000005));
        for (int i = 0; i < n; i++) {
            distance[i][i] = 0;
        }
        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            int c = roads[i][2];
            distance[a][b] = Math.min(distance[a][b], c);
            distance[b][a] = Math.min(distance[b][a], c);
        }
        // 全部关闭
        int ans = 1;
        for (int i = 1; i < (1 << n); i++) {
            ans += numberPlans(distance, i, n, maxDistance);
        }
        return ans;
    }
    
    private int numberPlans(int[][] distance, int plan, int n, int maxDistance) {
        int[] arr = new int[n];
        int[] vis = new int[n];
        int temp = 1;
        int startNode = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if ((temp & plan) > 0) {
                arr[i] = 1;
                startNode = i;
                cnt++;
            }
            temp <<= 1;
        }
        // 关闭到只有一个肯定可以到达
        if (cnt == 1) return 1;
        
        int unableDistance = maxDistance + 1;
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 两个都在才是可达的
                dis[i][j] = (arr[i] + arr[j] == 2) ? distance[i][j] : unableDistance;
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dis[j][i] >= unableDistance || dis[k][i] >= unableDistance) {
                        continue;
                    }
                    dis[j][k] = Math.min(dis[j][k], dis[j][i] + dis[k][i]);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] + arr[j] == 2 && dis[i][j] > maxDistance) {
                    return 0;
                }
            }
        }
        return 1;
    }


    public int removeAlmostEqualCharacters(String word) {
        int len = word.length();
        int[][] dp = new int[len][2];
        // 0 不变，1变
        dp[0] = new int[]{0,1};
        for (int i = 1; i < len; i++) {
            char before = word.charAt(i - 1);
            char now = word.charAt(i);
            // 如果本来是相邻的就必须有一个变
            if (before == now || Math.abs(before - now) == 1) {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            }
        }
        return Arrays.stream(dp[len - 1]).min().getAsInt();
    }

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> s1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> s2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        int[] ans = new int[2];
        for (int i = 0; i < nums1.length; i++) {
            ans[0] += s2.contains(nums1[i]) ? 1 : 0;
        }
        for (int i = 0; i < nums2.length; i++) {
            ans[1] += s1.contains(nums2[i]) ? 1 : 0;
        }
        return ans;
    }
}
