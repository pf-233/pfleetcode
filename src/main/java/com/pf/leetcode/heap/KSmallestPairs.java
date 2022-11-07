package com.pf.leetcode.heap;

import java.util.*;


public class KSmallestPairs {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> res = new LinkedList();
            int[] tmp = new int[]{0, 0, nums1[0] + nums2[0]};
            PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[2] - b[2] == 0 ? b[0] - a[0] : a[2] - b[2]);
            Set<String> set = new HashSet<>();
            set.add("0,0");
            que.offer(tmp);
            List<Integer> tmpList = null;
            while (k > 0 && !que.isEmpty()) {
                tmp = que.poll();
                k--;
                res.add(Arrays.asList(nums1[tmp[0]], nums2[tmp[1]]));
                int nextInd = tmp[0] + 1;
                if (nextInd < nums1.length && !set.contains(nextInd + "," + tmp[1])) {
                    set.add(nextInd + "," + tmp[1]);
                    que.offer(new int[]{nextInd, tmp[1], nums1[nextInd] + nums2[tmp[1]]});
                }
                nextInd = tmp[1] + 1;
                if (nextInd < nums2.length && !set.contains(tmp[0] + "," + nextInd)) {
                    set.add(tmp[0] + "," + nextInd);
                    que.offer(new int[]{tmp[0], nextInd, nums2[nextInd] + nums1[tmp[0]]});
                }
            }
            return res;
        }
}
