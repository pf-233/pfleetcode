package com.pf.leetcode.contest.leecode;

import java.util.*;

/**
 * author：panf
 * date：11/12/2023
 * Description:
 */
public class Contest371 {

    public static void main(String[] args) {
        Contest371 contest371 = new Contest371();
        int[] nums1 = null;
        int[] nums2 = null;
        nums1 = new int[]{1,5,4};
        nums2 = new int[]{2,5,3};
        System.out.println(contest371.minOperations(nums1, nums2));
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int max1 = Arrays.stream(nums1).max().getAsInt();
        int max2 = Arrays.stream(nums2).max().getAsInt();
        int n = nums1.length;
        if (max1 == nums1[n - 1] && max2 == nums2[n - 1]) {
            return 0;
        }
        // 不交换1和2
        int cnt = getMinOperations(nums1, nums2);
        //交换1和2
        int temp = nums1[n - 1];
        nums1[n - 1] = nums2[n - 1];
        nums2[n - 1] = temp;
        int cnt2 = getMinOperations(nums1, nums2);
        if (cnt2 > -1) cnt2++;

        return Math.min(cnt, cnt2) == -1 ? Math.max(cnt, cnt2) : Math.min(cnt, cnt2);
    }

    private int getMinOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] t1 = Arrays.copyOf(nums1, n);
        int[] t2 = Arrays.copyOf(nums2, n);
        int max1 = t1[n - 1];
        int max2 = t2[n - 1];
        int cnt = 0;
        for (int i = n - 2; i >= 0; i--) {
            // 必须交换
            if (t1[i] > max1) {
                // 如果t2 也大于max1 就不可交换
                // t1[i] > max2 也不可以交换
                if (t2[i] > max1 || t1[i] > max2) {
                    return -1;
                }
                cnt++;
            } else if (t2[i] > max2) {
                // 如果t1 也大于max2 就不可交换
                // t2[i] > max1 也不可以交换
                if (t1[i] > max2 || t2[i] > max1) {
                    return -1;
                }
                cnt++;
            }
        }
        return cnt;
    }

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        int len = access_times.size();
        Map<String, List<Integer>> map = new HashMap();
        List<Integer> temp = null;
        for (List<String> val : access_times) {
            temp = map.getOrDefault(val.get(0), new ArrayList());
            temp.add(toTime(val.get(1)));
            map.put(val.get(0), temp);
        }

        List<String> ans = new LinkedList();
        for (String key : map.keySet()) {
            temp = map.get(key);
            Collections.sort(temp);
            if (temp.size() < 3) continue;
            for (int i = 0; i < temp.size(); i++) {
                int cnt = 0;
                for (int j = i; j < temp.size() && temp.get(j) - temp.get(i) < 60; j++) {
                    cnt++;
                }
                if (cnt >= 3) {
                    ans.add(key);
                    break;
                }
            }
        }

        return ans;
    }

    private int toTime(String t1) {
        int hour1 = new Integer(t1.substring(0, 2));
        int minute1 = new Integer(t1.substring(2, 4));
        return hour1 * 60 + minute1;

    }
}
