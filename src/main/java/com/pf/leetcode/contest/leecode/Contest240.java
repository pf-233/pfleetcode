package com.pf.leetcode.contest.leecode;

import org.omg.CORBA.MARSHAL;

import java.util.*;

public class Contest240 {
    public static void main(String[] args) {
        int[][] tasks = new int[][]{
                {1,2},{2,4},{3,2},{4,1}
        };
        Contest240 contest237 = new Contest240();
        int [][] ma = new int[][] {
                {5,2},
                {1,6}
        };

        contest237.maxUncrossedLines(new int[] {
1,4,2
        }, new int[]{
1,2,4
        });
    }

 
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        // i j   i 和 j 的时候可以选择多少条边
        // 0 是i 和 j 不连接
        // 1 是连接
        int[][][] dp = new int[l1 + 1][l2 + 1][2];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 第i个对应j - 1 的时候的边数
                dp[i][j][0] = Math.max(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]);
                int tmp = dp[i][j][0];
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 相等的时候可以 + 1;
                    tmp++;
                }
                // i 连接过
                dp[i][j][1] = Math.max(tmp, dp[i][j - 1][1]);
                // j 连接过
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][1]);
            }
        }
        return Math.max(dp[l1 - 1][l2 - 1][0], dp[l1 - 1][l2 - 1][1]);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Node> map = new HashMap();
        Arrays.stream(words).forEach(e -> {
            Node tmp = map.getOrDefault(e, new Node(e));
            tmp.count++;
            map.put(e, tmp);
        });

        PriorityQueue<Node> queue = new PriorityQueue<Node>((a, b) -> b.count == a.count ? a.words.compareTo(b.words) : b.count - a.count);
        map.values().stream().forEach(e -> queue.offer(e));
        List<String> res = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            res.add(queue.poll().words);
        }
        return  res;
    }

    static class Node {
        private String words;
        private int count;

        Node(String w) {
            words = w;
            count = 0;
        }
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int len = row * col;
        int[] arr = new int[len];
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] ^= matrix[i][j - 1];
            }
        }

        int top = 0;
        for (int i = 0; i < col; i++) {
            arr[top++] = matrix[0][i];
            for (int j = 1; j < row; j++) {
                matrix[j][i] ^= matrix[j - 1][i];
                arr[top++] = matrix[j][i];

            }
        }
        Arrays.sort(arr);
        return arr[len - k];

    }

    private int mod = 1000000007;
    public int maxSumMinProduct(int[] nums) {
        long max = 0;
        int left = 0;
        int ri = nums.length - 1;
        int sum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i <= ri; i++) {
            sum += nums[i];
            int count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++count);
        }
        while (left <= ri) {
            max = Math.max(max, 1L * sum * map.firstKey());
            int tmp = 0;
            if (nums[left] < nums[ri]) {
                sum -= nums[left];
                tmp = nums[left];
                left++;
            } else {
                sum -= nums[ri];
                tmp = nums[ri];
                ri--;
            }
            int count = map.getOrDefault(tmp, 0);
            count--;
            if (count > 0) {
                map.put(tmp, count);
            } else {
                map.remove(tmp);
            }
        }
        return (int) (max % mod);
    }


    public int maximumPopulation(int[][] logs) {
        int[] res = new int[101];
        for (int i = 0; i < logs.length; i++) {
            for (int j = logs[i][0]; j < logs[i][1]; j++) {
                res[j - 1950]++;
            }
        }
        int year = 0;
        int max = 0;
        for (int i = 0; i < res.length; i++) {
            if (max < res[i]) {
                max = res[i];
                year = i + 1950;
            }
        }
        return year;
    }
}
